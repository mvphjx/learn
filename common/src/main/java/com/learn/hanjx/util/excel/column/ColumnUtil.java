package com.learn.hanjx.util.excel.column;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ColumnUtil
{
    @Test
    public void assertExcel() throws IOException
    {
        List<String> paths = new ArrayList<>();
        paths.add(ColumAibbsTest.cn);
        paths.add(ColumAibbsTest.aibbs);
        for (String path : paths)
        {
            ColumnUtil.assertExcel(path);
        }
    }

    /**
     * 校验列描述
     *
     * @return
     */
    public static void assertExcel(String path) throws IOException
    {
        if (!new File(path).exists())
        {
            System.out.println("文件不存在");
            return;
        }
        System.out.println("重复性校验（" + path + "）：");
        XSSFWorkbook workbook_aibbs = new XSSFWorkbook(path);
        XSSFSheet sheet = workbook_aibbs.getSheetAt(0);
        Set<String> set = new HashSet<>();
        int rowNums = sheet.getLastRowNum();
        for (int i = 0; i <= rowNums; i++)
        {
            XSSFRow row = sheet.getRow(i);
            XSSFCell tableNameCell = row.getCell(0);
            XSSFCell columnNameCell = row.getCell(1);
            XSSFCell columnDisplayNameCell = row.getCell(2);
            String tableName = tableNameCell.getStringCellValue();
            String columnName = columnNameCell.getStringCellValue();
            String key = tableName + "|" + columnName;
            if (!set.add(key))
            {
                System.out.println("重复的列：" + key);
            }
        }
        System.out.println("校验完毕");
    }
}
