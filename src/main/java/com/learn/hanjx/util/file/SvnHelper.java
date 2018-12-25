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
    public static final String PATH_NAME = "D:/workspace_svn/{{repository}}/trunk/{{project}}";
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

    //创建工程
    private static void createProject(XSSFRow row) throws Exception
    {
        if (ABISHelper.isEmpty(row))
        {
            return;
        }
        List<String> dirs= new ArrayList<>();
        dirs.add("/src/main/resources");
        dirs.add("/src/test/java");
        dirs.add("\\src\\main\\resources\\META-INF\\resources\\js\\abis");
        dirs.add("\\src\\main\\resources\\META-INF\\resources\\jsp\\abis");
        dirs.add("\\src\\main\\resources\\META-INF\\resources\\css\\abis");
        dirs.add("\\src\\main\\resources\\META-INF\\resources\\image\\abis");
        dirs.add("\\src\\main\\resources\\META-INF\\resources\\html\\abis");
        if(ABISHelper.isEmpty(row.getCell(0))||ABISHelper.isEmpty(row.getCell(1))){
            return;
        }
        String  repository = row.getCell(0).getStringCellValue();
        String  project = row.getCell(1).getStringCellValue();
        String projectPath =PATH_NAME.replace("{{repository}}",repository).replace("{{project}}",project);
        //web_base
        //habis_web_product_all
        //drj_web_product_all
        String[] packeges = project.split("_");
        String thispackege =project.replace("habis_web_","").replace("drj_web_","").replace("web_","").replace("_","");
        switch (repository){
            case "web-common":
                dirs.add("\\src\\main\\java\\com\\hisign\\pu\\abis\\web\\"+thispackege+"\\ctrl");
                dirs.add("\\src\\main\\java\\com\\hisign\\pu\\abis\\web\\"+thispackege+"\\biz");
                dirs.add("\\src\\main\\java\\com\\hisign\\pu\\abis\\web\\"+thispackege+"\\dao");
                dirs.add("\\src\\main\\java\\com\\hisign\\pu\\abis\\web\\"+thispackege+"\\data");
                dirs.add("\\src\\main\\java\\com\\hisign\\pu\\abis\\web\\"+thispackege+"\\entity");
                dirs.add("\\src\\main\\java\\com\\hisign\\pu\\abis\\web\\"+thispackege+"\\util");
                dirs.add("\\src\\main\\resources\\META-INF\\resources\\abis\\"+thispackege+"\\js");
                dirs.add("\\src\\main\\resources\\META-INF\\resources\\abis\\"+thispackege+"\\jsp");
                dirs.add("\\src\\main\\resources\\META-INF\\resources\\abis\\"+thispackege+"\\css");
                dirs.add("\\src\\main\\resources\\META-INF\\resources\\abis\\"+thispackege+"\\image");
                dirs.add("\\src\\main\\resources\\META-INF\\resources\\abis\\"+thispackege+"\\html");
                break;
            case "habis-web":
                dirs.add("\\src\\main\\java\\com\\hisign\\pu\\abis\\web\\abis\\"+thispackege+"\\ctrl");
                dirs.add("\\src\\main\\java\\com\\hisign\\pu\\abis\\web\\abis\\"+thispackege+"\\biz");
                dirs.add("\\src\\main\\java\\com\\hisign\\pu\\abis\\web\\abis\\"+thispackege+"\\dao");
                dirs.add("\\src\\main\\java\\com\\hisign\\pu\\abis\\web\\abis\\"+thispackege+"\\data");
                dirs.add("\\src\\main\\java\\com\\hisign\\pu\\abis\\web\\abis\\"+thispackege+"\\entity");
                dirs.add("\\src\\main\\java\\com\\hisign\\pu\\abis\\web\\abis\\"+thispackege+"\\util");
                dirs.add("\\src\\main\\resources\\META-INF\\resources\\abis\\"+thispackege+"\\js");
                dirs.add("\\src\\main\\resources\\META-INF\\resources\\abis\\"+thispackege+"\\jsp");
                dirs.add("\\src\\main\\resources\\META-INF\\resources\\abis\\"+thispackege+"\\css");
                dirs.add("\\src\\main\\resources\\META-INF\\resources\\abis\\"+thispackege+"\\image");
                dirs.add("\\src\\main\\resources\\META-INF\\resources\\abis\\"+thispackege+"\\html");
                break;
            case "drj-web":
                dirs.add("\\src\\main\\java\\com\\hisign\\pu\\drj\\web\\"+thispackege+"\\ctrl");
                dirs.add("\\src\\main\\java\\com\\hisign\\pu\\drj\\web\\"+thispackege+"\\biz");
                dirs.add("\\src\\main\\java\\com\\hisign\\pu\\drj\\web\\"+thispackege+"\\dao");
                dirs.add("\\src\\main\\java\\com\\hisign\\pu\\drj\\web\\"+thispackege+"\\data");
                dirs.add("\\src\\main\\java\\com\\hisign\\pu\\drj\\web\\"+thispackege+"\\entity");
                dirs.add("\\src\\main\\java\\com\\hisign\\pu\\drj\\web\\"+thispackege+"\\util");
                dirs.add("\\src\\main\\resources\\META-INF\\resources\\drj\\"+thispackege+"\\js");
                dirs.add("\\src\\main\\resources\\META-INF\\resources\\drj\\"+thispackege+"\\jsp");
                dirs.add("\\src\\main\\resources\\META-INF\\resources\\drj\\"+thispackege+"\\css");
                dirs.add("\\src\\main\\resources\\META-INF\\resources\\drj\\"+thispackege+"\\image");
                dirs.add("\\src\\main\\resources\\META-INF\\resources\\drj\\"+thispackege+"\\html");
                break;
        }
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
        artifactId.setText(project);
        Element  version =(Element) doc.selectSingleNode("/*[name()='project']/*[name()='parent']/*[name()='version']");
        version.setText("0.0.1-SNAPSHOT");
        Element  parent =(Element) doc.selectSingleNode("/*[name()='project']/*[name()='parent']/*[name()='artifactId']");
        Element  packaging =(Element) doc.selectSingleNode("/*[name()='project']/*[name()='packaging']");
        if (project.contains("parent"))
        {
            parent.setText("parent");
            packaging.setText("pom");
        }else{
            parent.setText("web_parent");
            packaging.setText("jar");
        }
        Element  dependencies =(Element) doc.selectSingleNode("/*[name()='project']/*[name()='dependencies']");
        if(project.contains("parent")||project.contains("common")||project.contains("base")){
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
