package com.learn.hanjx.util.file;

import com.learn.hanjx.test.ABISHelper;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jaxen.JaxenException;
import org.junit.Test;

public class SvnHelper
{
    /**
     * 创建svn目录 以及pom依赖文件
     *
     * @param args
     */
    public static final String PATH_NAME = "D:/workspace/habis-web/trunk";
    public static final String SVN_CFG = "C:/Users/han/Desktop/2018/work/09/svn-abisweb-201813-.xlsx";
    public static final String POM_TEMPLETE = "D:/workspace/NEW_WEB/pom.bak";
    public static void main(String[] args) throws Exception
    {
        XSSFWorkbook workbook = new XSSFWorkbook(SVN_CFG);
        XSSFSheet sheetAt = workbook.getSheetAt(1);
        int lastRowNum = sheetAt.getLastRowNum();
        for (int i = 1; i <=lastRowNum; i++)
        {
            XSSFRow row = sheetAt.getRow(i);
            createProject(row);
        }

    }
    @Test
    public void getDependencyStr() throws IOException
    {
        XSSFWorkbook workbook = new XSSFWorkbook(SVN_CFG);
        XSSFSheet sheetAt = workbook.getSheetAt(1);
        int lastRowNum = sheetAt.getLastRowNum();
        for (int i = 1; i <=lastRowNum; i++)
        {
            XSSFRow row = sheetAt.getRow(i);
            if (ABISHelper.isEmpty(row))
            {
                continue;
            }
            String  templete = "        <dependency>\n" +
                    "            <groupId>com.hisign.pu.abis</groupId>\n" +
                    "            <artifactId>{{artifactId}}</artifactId>\n" +
                    "            <version>0.0.1-SNAPSHOT</version>\n" +
                    "        </dependency>";
            XSSFCell prijectCell = row.getCell(1);
            String stringCellValue = prijectCell.getStringCellValue();
            templete=templete.replace("{{artifactId}}",stringCellValue);
            System.out.println(templete);
        }
    }

    private static void createProject(XSSFRow row) throws Exception
    {
        if (ABISHelper.isEmpty(row))
        {
            return;
        }
        List<String> dirs= new ArrayList<>();
        dirs.add("/src/main/resources");
        dirs.add("/src/test/java");
        dirs.add("\\src\\main\\resources\\META-INF\\resources\\js");
        dirs.add("\\src\\main\\resources\\META-INF\\resources\\jsp");
        dirs.add("\\src\\main\\resources\\META-INF\\resources\\css");
        dirs.add("\\src\\main\\resources\\META-INF\\resources\\image");
        XSSFCell prijectCell = row.getCell(1);
        String stringCellValue = prijectCell.getStringCellValue();
        String projectPath = PATH_NAME + "/" + stringCellValue;
        String[] packeges = stringCellValue.split("_");
        String thispackege= "";
        for (String packege : packeges)
        {
            if(!"web".equalsIgnoreCase(packege)){
                thispackege=thispackege+packege;
            }
        }
        dirs.add("\\src\\main\\java\\com\\hisign\\pu\\abis\\abisweb\\"+thispackege+"\\ctrl");
        dirs.add("\\src\\main\\java\\com\\hisign\\pu\\abis\\abisweb\\"+thispackege+"\\biz");
        dirs.add("\\src\\main\\java\\com\\hisign\\pu\\abis\\abisweb\\"+thispackege+"\\dao");
        dirs.add("\\src\\main\\java\\com\\hisign\\pu\\abis\\abisweb\\"+thispackege+"\\data");
        dirs.add("\\src\\main\\java\\com\\hisign\\pu\\abis\\abisweb\\"+thispackege+"\\entity");
        dirs.add("\\src\\main\\java\\com\\hisign\\pu\\abis\\abisweb\\"+thispackege+"\\util");
        if(new File(projectPath).isDirectory()){
            return;
        }
        for (String s : dirs)
        {
            new File(projectPath+ s).mkdirs();
        }
        //创建pom文件
        File pom = new File(POM_TEMPLETE);
        SAXReader reader = new SAXReader();
        Document doc = reader.read(pom);
        Element  artifactId =(Element) doc.selectSingleNode("/*[name()='project']/*[name()='artifactId']");
        //artifactId.setText(stringCellValue.replace("_","-"));
        artifactId.setText(stringCellValue);
        Element  version =(Element) doc.selectSingleNode("/*[name()='project']/*[name()='parent']/*[name()='version']");
        version.setText("0.0.1-SNAPSHOT");
        Element  parent =(Element) doc.selectSingleNode("/*[name()='project']/*[name()='parent']/*[name()='artifactId']");
        Element  packaging =(Element) doc.selectSingleNode("/*[name()='project']/*[name()='packaging']");
        if (stringCellValue.contains("parent"))
        {
            parent.setText("parent");
            packaging.setText("pom");
        }else{
            parent.setText("web_parent");
            packaging.setText("jar");
        }
        Element  dependencies =(Element) doc.selectSingleNode("/*[name()='project']/*[name()='dependencies']");
        if(stringCellValue.contains("parent")||stringCellValue.contains("common")||stringCellValue.contains("base")){
            dependencies.setText("");
        }else{
        }
        saveXMLToDisk(doc,projectPath+"/pom.xml");

    }
    public static void saveXMLToDisk(Document document, String path) throws Exception
    {
        OutputFormat outputFormat = new OutputFormat();
        outputFormat.setEncoding("UTF-8");
        FileOutputStream outputStream = new FileOutputStream(path);
        XMLWriter xmlWriter = new XMLWriter(outputStream, outputFormat);
        xmlWriter.write(document);
    }
}
