package com.learn.hanjx.util.excel;

import com.learn.hanjx.test.ABISHelper;
import org.apache.poi.ss.usermodel.CellCopyPolicy;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 不同项目 列描述合并
 */
public class ColumAibbsTest
{
    /**
     * 合并excel
     * 将en中的  col_disp_name  写入cn
     *
     * @param args
     * @throws IOException
     */
    public static final String cn = "C:\\PU\\habis\\install-res\\INSTALL-FILES\\列描述\\cn\\col_tbl.xlsx";
    public static final String aibbs = "C:\\PU\\aibbs\\trunk\\install-res\\INSTALL-FILES\\列描述\\cn\\col_tbl.xlsx";
    public static final String filePath = "test.xlsx";

    private Set<String> columNames = new HashSet<>();

    /**
     * 合并两个excel
     * 将excle1的新增数据  合并到 excel2
     *
     * @throws IOException
     */
    @Test
    public void merge() throws IOException
    {
        //XLSB file-> sheet -> row  ->cell
        XSSFWorkbook workbook_cn = new XSSFWorkbook(cn);
        XSSFWorkbook workbook_aibbs = new XSSFWorkbook(aibbs);
        XSSFSheet sheet = workbook_cn.getSheetAt(0);
        int rowNums = sheet.getLastRowNum();
        for (int i = 0; i <= rowNums; i++)
        {
            XSSFRow row = sheet.getRow(i);
            appendWrite(row, workbook_aibbs);
        }
        File file = new File(filePath);
        file.delete();
        file.createNewFile();
        FileOutputStream des = new FileOutputStream(filePath, true);
        //输出合并后的文件
        workbook_aibbs.write(des);

    }

    /**
     * 合并逻辑，以表名+列名为主键，追加不存在的列
     *
     * @param row
     * @param workbook
     * @throws IOException
     */
    private void appendWrite(XSSFRow row, XSSFWorkbook workbook)
    {
        //列集合
        Set columNames = this.getColumNames(workbook);
        //当前列
        XSSFCell tableNameCell = row.getCell(0);
        XSSFCell columnNameCell = row.getCell(1);
        String tableName = tableNameCell.getStringCellValue();
        String columnName = columnNameCell.getStringCellValue();
        String key = tableName + "|" + columnName;
        if (columNames.contains(key))
        {
            return;
        }
        System.out.println("要新增的列："+key);
        XSSFSheet sheet = workbook.getSheetAt(0);
        int lastRowNum = sheet.getLastRowNum();
        XSSFRow rowAdd = sheet.createRow(lastRowNum + 1);
        short lastCellNum = row.getLastCellNum();
        for (int i = 0; i <= lastCellNum; i++)
        {
            XSSFCell cellAdd = rowAdd.createCell(i);
            XSSFCell cell = row.getCell(i);
            if (ABISHelper.isEmpty(cell))
            {
                continue;
            }
            CellType cellTypeEnum = cell.getCellTypeEnum();
            if (cellTypeEnum.equals(CellType.NUMERIC))
            {
                cellAdd.setCellValue(cell.getNumericCellValue());
            }
            else
            {
                cellAdd.setCellValue(cell.getStringCellValue());
            }

        }
    }

    /**
     * 获取列描述
     *
     * @param workbook
     * @return
     */
    private Set getColumNames(XSSFWorkbook workbook)
    {
        if (!ABISHelper.isEmpty(columNames))
        {
            return columNames;
        }
        if (ABISHelper.isEmpty(workbook))
        {
            System.out.println("workbook 缺失");
            return null;
        }
        XSSFSheet sheet = workbook.getSheetAt(0);
        int rowNums = sheet.getLastRowNum();
        for (int i = 0; i < rowNums; i++)
        {
            XSSFRow row = sheet.getRow(i);
            if (ABISHelper.isEmpty(row))
            {
                continue;
            }
            XSSFCell tableNameCell = row.getCell(0);
            XSSFCell columnNameCell = row.getCell(1);
            XSSFCell columnDisplayNameCell = row.getCell(2);
            String tableName = tableNameCell.getStringCellValue();
            String columnName = columnNameCell.getStringCellValue();
            columNames.add(tableName + "|" + columnName);
        }
        return columNames;
    }

    @Test
    public void main() throws IOException
    {
        ColumAibbsTest.assertExcel(aibbs);
    }

    /**
     * 校验列描述
     *
     * @return
     */
    public static void assertExcel(String path) throws IOException
    {
        XSSFWorkbook workbook_aibbs = new XSSFWorkbook(aibbs);
        XSSFSheet sheet = workbook_aibbs.getSheetAt(0);
        Set<String> set  = new HashSet<>();
        int rowNums = sheet.getLastRowNum();
        for (int i = 0; i <= rowNums; i++)
        {
            XSSFRow row = sheet.getRow(i);
            XSSFCell tableNameCell = row.getCell(0);
            XSSFCell columnNameCell = row.getCell(1);
            XSSFCell columnDisplayNameCell = row.getCell(2);
            String tableName = tableNameCell.getStringCellValue();
            String columnName = columnNameCell.getStringCellValue();
            String key = tableName+"|"+columnName;
            if(!set.add(key)){
                System.out.println("重复的列："+key);
            }
        }
    }
}
