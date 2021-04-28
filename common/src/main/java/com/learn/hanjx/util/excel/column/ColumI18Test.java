package com.learn.hanjx.util.excel.column;

import com.learn.hanjx.test.ABISHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 国际化列描述合并
 */
public class ColumI18Test
{
    /**
     * 合并excel
     * 将en中的  col_disp_name  写入cn
     * @param args
     * @throws IOException
     */
    public  static final String cn = "D:\\workspace\\PU\\trunk\\PU-AFIS-10\\trunk\\INSTALL-RES\\trunk\\INSTALL-FILES\\列描述\\cn\\col_tbl.xlsx";
    public  static final String en = "D:\\workspace\\PU\\trunk\\PU-AFIS-10\\trunk\\INSTALL-RES\\trunk\\INSTALL-FILES\\列描述\\en\\col_tbl.xlsx";
    public  static final String filePath = "test.xlsx";
    public  List<String> keyColums = new ArrayList<>();
    {
        keyColums.add("YYYY");
        keyColums.add("YYYYMM");
        keyColums.add("YYYYMMDD");
        keyColums.add("YYYYQ");
        keyColums.add("CODE_DISP_STYLE");
    }
    public  List<String> keyWords = new ArrayList<>();
    {
        keyWords.add("TT");
        keyWords.add("TL");
        keyWords.add("LL");
        keyWords.add("LT");
        keyWords.add("TP");
        keyWords.add("LP");
    }

    /**
     * 比较两个excel
     * @throws IOException
     */
    public void compare() throws IOException
    {
        //XLSB file-> sheet -> row  ->cell
        XSSFWorkbook workbook_cn = new XSSFWorkbook(cn);
        XSSFWorkbook workbook_en = new XSSFWorkbook(en);
        XSSFSheet sheet_cn= workbook_cn.getSheetAt(0);
        XSSFSheet sheet_en= workbook_en.getSheetAt(0);
        int rowNums_cn = sheet_cn.getLastRowNum();
        int rowNums_en = sheet_en.getLastRowNum();
        for (int i = 0; i < rowNums_cn; i++)
        {
            XSSFRow row_cn = sheet_cn.getRow(i);
        }
    }
    /**
     * 合并两个excel
     * @throws IOException
     */
    @Test
    public void merge() throws IOException
    {
        //XLSB file-> sheet -> row  ->cell
        XSSFWorkbook workbook_cn = new XSSFWorkbook(cn);
        XSSFWorkbook workbook_en = new XSSFWorkbook(en);
        XSSFSheet sheet= workbook_en.getSheetAt(0);
        int rowNums = sheet.getLastRowNum();
        for (int i = 0; i <= rowNums; i++)
        {
            XSSFRow row=sheet.getRow(i);
            if(ABISHelper.isEmpty(row)){
                continue;
            }
            XSSFCell tableNameCell= row.getCell(0);
            XSSFCell columnNameCell= row.getCell(1);
            XSSFCell columnDisplayNameCell= row.getCell(2);
            String tableName=tableNameCell.getStringCellValue();
            String columnName=columnNameCell.getStringCellValue();
            String columnDisplayName=columnDisplayNameCell.getStringCellValue();
            columnDisplayName = format(columnDisplayName);
            overWrite(tableName,columnName,columnDisplayName,workbook_cn);
        }
        File file = new File(filePath);
        file.delete();
        file.createNewFile();
        FileOutputStream des=new FileOutputStream(filePath,true);
        //输出合并后的文件
        workbook_cn.write(des);

    }

    /**
     * 合并逻辑，以表名+列名为主键，将文档中字段显示名 替换
     * @param _tableName 表名
     * @param _columnName 列名
     * @param _columnDisplayName  字段显示名（可能是英文/中文）
     * @param workbook 操作的文档
     * @throws IOException
     */
    private void overWrite(String _tableName,String _columnName,String _columnDisplayName,XSSFWorkbook workbook) throws IOException
    {
        XSSFSheet sheet= workbook.getSheetAt(0);
        int rowNums = sheet.getLastRowNum();
        for (int i = 0; i < rowNums; i++)
        {
            XSSFRow row=sheet.getRow(i);
            if(ABISHelper.isEmpty(row)){
                continue;
            }
            XSSFCell tableNameCell= row.getCell(0);
            XSSFCell columnNameCell= row.getCell(1);
            XSSFCell columnDisplayNameCell= row.getCell(2);
            String tableName=tableNameCell.getStringCellValue();
            String columnName=columnNameCell.getStringCellValue();
            if(_tableName.equals(tableName)&&_columnName.equals(columnName)){
                columnDisplayNameCell.setCellValue(_columnDisplayName);
                System.out.println(i+":"+_columnDisplayName);
                break;
            }
        }
    }

    /**
     * 讲单词 首字母大写
     * @param str
     * @return
     */
    private String format(String str){
        if(keyColums.contains(str)){
            return str;
        }
        StringBuffer stringbf = new StringBuffer();
        Matcher m = Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(str);
        while (m.find()) {
            String key = m.group(1) + m.group(2);
            if(keyWords.contains(key)){
                continue;
            }
            m.appendReplacement(stringbf, m.group(1).toUpperCase() + m.group(2).toLowerCase());
        }
        return m.appendTail(stringbf).toString();
    }
    @Test
    public void main() {
        String str = "TL/LT Query Hit Information";
        System.out.println(str);
        StringBuffer stringbf = new StringBuffer();
        Matcher m = Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(str);
        while (m.find()) {
            String key = m.group(1) + m.group(2);
            if(keyWords.contains(key)){
                continue;
            }
            m.appendReplacement(stringbf, m.group(1).toUpperCase() + m.group(2).toLowerCase());
        }
        System.out.println(m.appendTail(stringbf).toString());
    }
}
