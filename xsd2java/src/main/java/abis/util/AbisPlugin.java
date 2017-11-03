package abis.util;

import abis.base.util.AbisColumAnno;
import com.sun.codemodel.JAnnotationUse;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JPackage;
import com.sun.codemodel.fmt.JTextFile;
import com.sun.tools.xjc.Options;
import com.sun.tools.xjc.model.CCustomizations;
import com.sun.tools.xjc.model.CPluginCustomization;
import com.sun.tools.xjc.model.CPropertyInfo;
import com.sun.tools.xjc.outline.ClassOutline;
import com.sun.tools.xjc.outline.FieldOutline;
import com.sun.tools.xjc.outline.Outline;
import com.sun.xml.xsom.XSElementDecl;
import com.sun.xml.xsom.XSParticle;
import com.sun.xml.xsom.XSTerm;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.jvnet.jaxb2_commons.plugin.AbstractParameterizablePlugin;
import org.jvnet.jaxb2_commons.util.CustomizationUtils;
import org.jvnet.jaxb2_commons.util.FieldAccessorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import javax.persistence.*;
import javax.xml.namespace.QName;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * AbisPlugin : a JAXB2 plug-in used to add our customized annotations in generated classes from XML schema file.
 */
public class AbisPlugin extends AbstractParameterizablePlugin
{
    private static final Logger LOG = LoggerFactory.getLogger(AbisPlugin.class);

    // customized JPA annotations.
    public static final String NS = "http://hisign.com/pu/abis/plugin";
    public static final String TABLE_ANNOX = "table";
    public static final String FIELD_ANNOX = "field";
    public static final String LOB_ANNOX = "lob";
    public static final String ID_ANNOX = "id";
    public static final String ONE_TO_ONE_ANNOX = "OneToOne";
    public static final String ONE_TO_MANY_ANNOX = "OneToMany";
    public static final String MANY_TO_ONE_ANNOX = "ManyToOne";
    public static final String EMBEDDED_ANNOX = "embedded";
    public static final String EMBEDDABLE_ANNOX = "embeddable";
    // customized JSON annotations.
    public static final String JSON_OBJ_ANNOX = "JsonObj";
    public static final String ITEM_ANNOX = "item";

    public static final QName TABLE_QNAME = new QName(NS, TABLE_ANNOX);
    public static final QName FIELD_QNAME = new QName(NS, FIELD_ANNOX);
    public static final QName LOB_QNAME = new QName(NS, LOB_ANNOX);
    public static final QName ID_QNAME = new QName(NS, ID_ANNOX);
    public static final QName ONE_TO_ONE_QNAME = new QName(NS, ONE_TO_ONE_ANNOX);
    public static final QName ONE_TO_MANY_QNAME = new QName(NS, ONE_TO_MANY_ANNOX);
    public static final QName MANY_TO_ONE_QNAME = new QName(NS, MANY_TO_ONE_ANNOX);
    public static final QName EMBEDDABLE_QNAME = new QName(NS, EMBEDDABLE_ANNOX);
    public static final QName EMBEDDED_QNAME = new QName(NS, EMBEDDED_ANNOX);
    public static final QName JSON_OBJ_QNAME = new QName(NS, JSON_OBJ_ANNOX);
    public static final QName ITEM_QNAME = new QName(NS, ITEM_ANNOX);

    // columnClasses is used to generate auxiliary column names.
    private Map<String, List<String>> columnClasses = new HashMap<>();
    // keyClasses is used to generate auxiliary JSON key names.
    private Map<String, List<String>> keyClasses = new HashMap<>();
    private Map<String, ClassOutline> allClasses = new TreeMap<>();
    private Map<String, String> allMappedBy = new HashMap<>();

    @Override
    public String getOptionName()
    {
        return "Xpuabis";
    }

    @Override
    public String getUsage()
    {
        return "Xpuabis:add customized JPA annotation";
    }

    @Override
    public boolean run(Outline outline, Options opt, ErrorHandler errHandler) throws SAXException
    {
        for (ClassOutline co : outline.getClasses())
        {
            processClassOutline(co, opt, errHandler);
        }

        // In Java EE environments, <class> elements are not necessary in persistence.xml.
        // writePersistenceXml(outline.getCodeModel());
        writeColNameClass(outline.getCodeModel());
        writeJsonKeyNameClass(outline.getCodeModel());
        return false;
    }

    protected void processClassOutline(ClassOutline classOutline, Options options, ErrorHandler errorHandler)
    {
        final CCustomizations customizations = CustomizationUtils.getCustomizations(classOutline);
        annotateClassOutline(classOutline.ref.owner(), classOutline, customizations, errorHandler);

        for (final FieldOutline fieldOutline : classOutline.getDeclaredFields())
        {
            processFieldOutline(classOutline, fieldOutline, options, errorHandler);
        }

    }

    protected void annotateClassOutline(final JCodeModel codeModel, final ClassOutline classOutline,
            final CCustomizations customizations, ErrorHandler errorHandler)
    {
        for (final CPluginCustomization customization : customizations)
        {
            final Element element = customization.element;
            final QName name = new QName(element.getNamespaceURI(), element.getLocalName());
            LOG.info("QName is " + name);
            if (NS.equals(name.getNamespaceURI()))
            {
                customization.markAsAcknowledged();
                classOutline.implClass.annotate(JsonIgnoreProperties.class).param("ignoreUnknown", true);
                switch (element.getLocalName())
                {
                case TABLE_ANNOX:
                    classOutline.implClass.annotate(Entity.class);
                    classOutline.implClass.annotate(Table.class).param("name",
                            convertToDBName(classOutline.implClass.name()));
                    allClasses.put(classOutline.implClass.name(), classOutline);
                    break;
                case EMBEDDABLE_ANNOX:
                    classOutline.implClass.annotate(Embeddable.class);
                    break;
                case FIELD_ANNOX:
                    System.out.println("classoutline with field annotation:");
                    System.out.println(classOutline.implClass.name());
                    break;
                }
            }
        }
    }

    private String getElementName(FieldOutline fieldOutline)
    {
        String elementName = null;
        CPropertyInfo fieldInfo = fieldOutline.getPropertyInfo();
        if (fieldInfo.getSchemaComponent() instanceof XSParticle)
        {
            XSTerm term = ((XSParticle) fieldInfo.getSchemaComponent()).getTerm();
            if (term.isElementDecl())
            {
                XSElementDecl element = term.asElementDecl();
                elementName = element != null ? element.getName() : null;
            }
        }
        return removeCR(elementName);
    }

    private String removeCR(String str)
    {
        Pattern p = Pattern.compile("\\s*|\t|\r|\n");
        Matcher m = p.matcher(str);
        return m.replaceAll("");
    }

    protected void processFieldOutline(ClassOutline classOutline, FieldOutline fieldOutline, Options options,
            ErrorHandler errorHandler)
    {

        final CCustomizations customizations = CustomizationUtils.getCustomizations(fieldOutline);
        for (final CPluginCustomization customization : customizations)
        {
            String colName = null;
            final Element element = customization.element;
            // if has value, use it as column name.
            final NodeList elements = element.getChildNodes();
            for (int index = 0; index < elements.getLength(); index++)
            {
                final Node node = elements.item(index);
                if (node.getNodeType() == Node.TEXT_NODE)
                    colName = removeCR(node.getNodeValue());
            }
            final QName name = new QName(element.getNamespaceURI(), element.getLocalName());
            if (NS.equals(name.getNamespaceURI()))
            {
                switch (name.getLocalPart())
                {
                case LOB_ANNOX:
                    addColumnAnnotation(element, customization, fieldOutline, classOutline, colName);
                    addLobAnno(customization, fieldOutline);
                    break;
                case FIELD_ANNOX:
                    addColumnAnnotation(element, customization, fieldOutline, classOutline, colName);
                    break;
                case ITEM_ANNOX:
                    addItemAnnotation(element, customization, fieldOutline, classOutline, colName);
                    break;
                case ONE_TO_ONE_ANNOX:
                    addOneToOneAnno(element, customization, fieldOutline, classOutline, colName);
                    break;
                case ONE_TO_MANY_ANNOX:
                    addOneToManyAnno(element, customization, fieldOutline, classOutline, colName);
                    break;
                case MANY_TO_ONE_ANNOX:
                    addManyToOneAnno(customization, fieldOutline, classOutline);
                    break;
                case ID_ANNOX:
                    addIdAnno(element, customization, fieldOutline, classOutline);
                    addColumnAnnotation(element, customization, fieldOutline, classOutline, colName);
                    break;
                case EMBEDDED_ANNOX:
                    addEmbeddedAnno(customization, fieldOutline, classOutline, colName);
                    break;
                }
            }

        }
    }

    private void addLobAnno(CPluginCustomization customization, FieldOutline fieldOutline)
    {
        customization.markAsAcknowledged();
        final JFieldVar field = FieldAccessorUtils.field(fieldOutline);
        if (field != null)
        {
            field.annotate(Lob.class);
        }
    }

    private void addIdAnno(Element element, CPluginCustomization customization, FieldOutline fieldOutline,
            ClassOutline classOutline)
    {

        customization.markAsAcknowledged();
        final JFieldVar field = FieldAccessorUtils.field(fieldOutline);
        if (field != null)
        {
            field.annotate(Id.class);
            String generator = element.getAttribute("generator");
            if (generator == null || generator.isEmpty())
            {
                System.out.println("---------------------------------------------------------------------");
                System.out.println("[WARNING]: cannot get generator attribute for id annotation in "
                        + classOutline.implClass.name());
                System.out.println("---------------------------------------------------------------------");
            }
            else
            {
                field.annotate(GeneratedValue.class).param("strategy", GenerationType.TABLE).param("generator",
                        generator);
            }
            String pkColumnValue = element.getAttribute("pkColumnValue");
            if (pkColumnValue == null || pkColumnValue.isEmpty())
            {
                System.out.println("---------------------------------------------------------------------");
                System.out.println("[WARNING]: cannot get pkColumnValue attribute for id annotation in "
                        + classOutline.implClass.name());
                System.out.println("---------------------------------------------------------------------");
            }
            else
            {
                field.annotate(TableGenerator.class).param("name", generator).param("pkColumnValue", pkColumnValue)
                        .param("table", IDGeneratorInfo.TABLE_NAME).param("pkColumnName", IDGeneratorInfo.PK_COL_NAME)
                        .param("valueColumnName", IDGeneratorInfo.VAL_COL_NAME)
                        .param("allocationSize", IDGeneratorInfo.ALLOCATION_SIZE);
            }
        }
    }

    private void addOneToManyAnno(Element element, CPluginCustomization customization, FieldOutline fieldOutline,
            ClassOutline classOutline, String colName)
    {

        final JFieldVar field = FieldAccessorUtils.field(fieldOutline);
        if (field != null)
        {
            String mappedBy = element.getAttribute("mappedBy");

            if (mappedBy == null || mappedBy.isEmpty())
            {
                System.out.println("---------------------------------------------------------------------");
                System.out.println("[ERROR]: cannot detect mappedBy attribute for OneToManty annotation in "
                        + classOutline.implClass.name());
                System.out.println("---------------------------------------------------------------------");
                return;
            }
            // add to allMappedBy
            System.out.println(field.type().name().replaceAll("List<", "").replaceAll(">", "") + "-->" + mappedBy);
            allMappedBy.put(field.type().name().replaceAll("List<", "").replaceAll(">", ""), mappedBy);
            // field.annotate(OneToMany.class).param("mappedBy", mappedBy).param("cascade",
            // CascadeType.ALL).param("fetch",
            // FetchType.EAGER);
            field.annotate(Convert.class).param("converter", JpaConverterJson.class);
            String columnName = colName != null ? colName : convertToDBName(field.name());
            field.annotate(Column.class).param("name", columnName);
            String elementName = getElementName(fieldOutline);
            field.annotate(JsonProperty.class).param("value", elementName);
            insertColumn(classOutline, columnName);
            insertJsonKey(classOutline, elementName);
        }
        customization.markAsAcknowledged();
    }

    private void addOneToOneAnno(Element element, CPluginCustomization customization, FieldOutline fieldOutline,
            ClassOutline classOutline, String colName)
    {
        customization.markAsAcknowledged();
        final JFieldVar field = FieldAccessorUtils.field(fieldOutline);
        if (field != null)
        {
            String joinColumn = element.getAttribute("joinColumn");
            if (joinColumn == null || joinColumn.isEmpty())
                joinColumn = field.name();

            field.annotate(OneToOne.class).param("cascade", CascadeType.ALL);
            field.annotate(JoinColumn.class).param("name", convertToDBName(joinColumn)).param("nullable", true);

            String columnName = colName != null ? colName : convertToDBName(field.name());
            insertColumn(classOutline, columnName);

            String elementName = getElementName(fieldOutline);
            field.annotate(JsonProperty.class).param("value", elementName);
            insertJsonKey(classOutline, elementName);
        }
    }

    private void addManyToOneAnno(CPluginCustomization customization, FieldOutline fieldOutline,
            ClassOutline classOutline)
    {
        customization.markAsAcknowledged();
        final JFieldVar field = FieldAccessorUtils.field(fieldOutline);
        if (field != null)
        {
            // field.annotate(ManyToOne.class).param("cascade", CascadeType.ALL);
            // field.annotate(JoinColumn.class).param("name", convertToDBName(field.name()));
            field.annotate(JsonIgnore.class);
            // Collection<JAnnotationUse> ans = field.annotations();
            // for (JAnnotationUse ja : ans)
            // {
            // if (ja.getAnnotationClass().name().equalsIgnoreCase("XmlElement"))
            // {
            // field.removeAnnotation(ja);
            // field.annotate(XmlTransient.class);
            // }
            // }
            System.out.println(classOutline.implClass.name());
            System.out.println(allMappedBy.size() + allMappedBy.get(classOutline.implClass.name()));
            // String colName = allMappedBy.get(classOutline.implClass.name());
            // if (colName != null)
            // insertColumn(classOutline, colName);
        }
    }

    private void addColumnAnnotation(Element element, CPluginCustomization customization, FieldOutline fieldOutline,
            ClassOutline classOutline, String colName)
    {
        String length = element.getAttribute("length");
        String precision = element.getAttribute("precision");
        String visible = element.getAttribute("visible");
        customization.markAsAcknowledged();
        final JFieldVar field = FieldAccessorUtils.field(fieldOutline);

        if (field != null)
        {
            String capitalColName = colName != null ? colName : convertToDBName(field.name());
            JAnnotationUse jauAbis = field.annotate(AbisColumAnno.class).param("name", capitalColName);
            JAnnotationUse jau = field.annotate(Column.class).param("name", capitalColName);
            if (length != null && !length.isEmpty())
            {
                jau.param("length", Integer.valueOf(length));
                jauAbis.param("length", Integer.valueOf(length));
            }
            if (precision != null && !precision.isEmpty())
            {
                jau.param("precision", Integer.valueOf(precision));
                jauAbis.param("precision", Integer.valueOf(precision));
            }
            if (visible != null && !visible.isEmpty())
                jauAbis.param("visible", visible.equalsIgnoreCase("true") ? true : false);

            String columnName = colName != null ? colName : capitalColName;
            insertColumn(classOutline, columnName);

            String elementName = getElementName(fieldOutline);
            field.annotate(JsonProperty.class).param("value", elementName);
            insertJsonKey(classOutline, elementName);
        }
    }

    private void addItemAnnotation(Element element, CPluginCustomization customization, FieldOutline fieldOutline,
            ClassOutline classOutline, String colName)
    {
        String length = element.getAttribute("length");
        String precision = element.getAttribute("precision");
        String visible = element.getAttribute("visible");
        customization.markAsAcknowledged();
        final JFieldVar field = FieldAccessorUtils.field(fieldOutline);
        if (field != null)
        {
            String capitalColName = colName != null ? colName : convertToDBName(field.name());
            JAnnotationUse jauAbis = field.annotate(AbisColumAnno.class).param("name", capitalColName);
            if (length != null && !length.isEmpty())
                jauAbis.param("length", Integer.valueOf(length));
            if (precision != null && !precision.isEmpty())
                jauAbis.param("precision", Integer.valueOf(precision));
            if (visible != null && !visible.isEmpty())
                jauAbis.param("visible", visible.equalsIgnoreCase("true") ? true : false);

            String columnName = colName != null ? colName : capitalColName;
            insertColumn(classOutline, columnName);

            String elementName = getElementName(fieldOutline);
            field.annotate(JsonProperty.class).param("value", elementName);
            insertJsonKey(classOutline, elementName);
        }
    }

    private void addEmbeddedAnno(CPluginCustomization customization, FieldOutline fieldOutline,
            ClassOutline classOutline, String colName)
    {
        customization.markAsAcknowledged();
        final JFieldVar field = FieldAccessorUtils.field(fieldOutline);
        if (field != null)
        {
            field.annotate(Embedded.class);
        }

        String columnName = colName != null ? colName : convertToDBName(field.name());
        insertColumn(classOutline, columnName);

        String elementName = getElementName(fieldOutline);
        field.annotate(JsonProperty.class).param("value", elementName);
        insertJsonKey(classOutline, elementName);
    }

    private String convertToDBName(String name)
    {
        /*
         * Processing some known abbreviation. This is not necessary. We can specify the column name explicitly in
         * schema file. To simplify the writing of schema, we do some pre-processing for these known abbreviation.
         */
        String prepStr = name.replace("UUID", "Uuid").replace("TP", "Tp").replace("LP", "Lp")
                .replace("PINYIN", "Pinyin").replace("MACS", "Macs").replace("IP", "Ip").replace("Code1", "Code_1")
                .replace("Code2", "Code_2").replace("Code3", "Code_3").replace("xtractor1", "xtractor_1")
                .replace("xtractor2", "xtractor_2").replace("xtractor3", "xtractor_3").replace("CE", "Ce");

        StringBuilder sb = new StringBuilder();
        char[] ca = prepStr.toCharArray();
        int idx = 0;
        for (char c : ca)
        {
            if (Character.isUpperCase(c) && idx != 0)
                sb.append("_");
            sb.append(String.format("%C", c));
            ++idx;
        }
        return sb.toString();
    }

    @Override
    public Collection<QName> getCustomizationElementNames()
    {
        return Arrays.asList(TABLE_QNAME, LOB_QNAME, FIELD_QNAME, ONE_TO_ONE_QNAME, ONE_TO_MANY_QNAME,
                MANY_TO_ONE_QNAME, ID_QNAME, EMBEDDABLE_QNAME, EMBEDDED_QNAME, JSON_OBJ_QNAME, ITEM_QNAME);
    }

    private void writePersistenceXml(JCodeModel model)
    {
        JPackage metaInfPackage = model._package("META-INF");
        JTextFile javaFile = new JTextFile("persistence.xml");
        metaInfPackage.addResourceFile(javaFile);
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
        sb.append(System.lineSeparator());
        sb.append("<persistence version=\"1.0\" xmlns=\"http://java.sun.com/xml/ns/persistence\">");
        sb.append(System.lineSeparator());
        sb.append("    <persistence-unit name=\"jpaUnit\" transaction-type=\"JTA\">");
        sb.append(System.lineSeparator());
        for (Map.Entry<String, ClassOutline> classEntry : allClasses.entrySet())
        {
            sb.append("        <class>");
            sb.append(classEntry.getValue().implClass.fullName());
            sb.append("</class>");
            sb.append(System.lineSeparator());
        }

        sb.append("    </persistence-unit>");
        sb.append(System.lineSeparator());
        sb.append("</persistence>");

        javaFile.setContents(sb.toString());
    }

    private void writeJsonKeyNameClass(JCodeModel model)
    {
        JPackage metaInfPackage = model._package("com.hisign.pu.abis.orm.constant.key");

        for (Map.Entry<String, List<String>> classEntry : keyClasses.entrySet())
        {
            JTextFile javaFile = new JTextFile(classEntry.getKey() + "Key" + ".java");
            metaInfPackage.addResourceFile(javaFile);
            StringBuilder sb = new StringBuilder();
            sb.append("package com.hisign.pu.abis.orm.constant.key;");
            sb.append(System.lineSeparator());
            sb.append(System.lineSeparator());
            sb.append("public final class " + classEntry.getKey() + "Key");
            sb.append(System.lineSeparator());
            sb.append("{");
            sb.append(System.lineSeparator());
            sb.append(System.lineSeparator());

            for (String col : classEntry.getValue())
            {
                sb.append("    public static final String " + convertToDBName(col) + " = \"" + col + "\";");
                sb.append(System.lineSeparator());
            }
            sb.append(System.lineSeparator());
            sb.append("}");
            javaFile.setContents(sb.toString());
        }
    }

    private void writeColNameClass(JCodeModel model)
    {
        JPackage metaInfPackage = model._package("com.hisign.pu.abis.orm.constant.col");

        for (Map.Entry<String, List<String>> classEntry : columnClasses.entrySet())
        {
            JTextFile javaFile = new JTextFile(classEntry.getKey() + "Col" + ".java");
            metaInfPackage.addResourceFile(javaFile);
            StringBuilder sb = new StringBuilder();
            sb.append("package com.hisign.pu.abis.orm.constant.col;");
            sb.append(System.lineSeparator());
            sb.append(System.lineSeparator());
            sb.append("public final class " + classEntry.getKey() + "Col");
            sb.append(System.lineSeparator());
            sb.append("{");
            sb.append(System.lineSeparator());
            sb.append(System.lineSeparator());

            for (String col : classEntry.getValue())
            {
                sb.append("    public static final String " + col + " = \"" + col + "\";");
                sb.append(System.lineSeparator());
            }
            sb.append(System.lineSeparator());
            sb.append("}");
            javaFile.setContents(sb.toString());
        }
    }

    private void insertColumn(ClassOutline classOutline, String columnName)
    {
        // insert into column name class
        String colClassName = classOutline.implClass.name();
        List<String> cols = columnClasses.get(colClassName);
        if (cols == null)
            columnClasses.put(colClassName, new ArrayList<String>());
        if (!columnClasses.get(colClassName).contains(columnName))
            columnClasses.get(colClassName).add(columnName);
    }

    private void insertJsonKey(ClassOutline classOutline, String keyName)
    {
        // insert into key name class
        String colClassName = classOutline.implClass.name();
        List<String> cols = keyClasses.get(colClassName);
        if (cols == null)
            keyClasses.put(colClassName, new ArrayList<String>());
        if (!keyClasses.get(colClassName).contains(keyName))
            keyClasses.get(colClassName).add(keyName);
    }

}
