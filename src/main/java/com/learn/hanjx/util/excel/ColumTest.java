package com.learn.hanjx.util.excel;

import com.learn.hanjx.test.ABISHelper;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ColumTest
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
    public static void main(String[] args) throws IOException
    {
        //XLSB file-> sheet -> row  ->cell
        XSSFWorkbook workbook_cn = new XSSFWorkbook(cn);
        XSSFWorkbook workbook_en = new XSSFWorkbook(en);
        XSSFSheet sheet= workbook_en.getSheetAt(0);
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
            String columnDisplayName=columnDisplayNameCell.getStringCellValue();
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
    private static void overWrite(String _tableName,String _columnName,String _columnDisplayName,XSSFWorkbook workbook) throws IOException
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
}
