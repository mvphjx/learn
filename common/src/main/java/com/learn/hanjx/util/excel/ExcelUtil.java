package com.learn.hanjx.util.excel;

import junit.framework.Assert;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtil
{

    /**
     * creates an {@link HSSFWorkbook} with the specified OS filename.
     */
    public static XSSFWorkbook readFile(String filename) throws IOException
    {
        try (FileInputStream fis = new FileInputStream(filename))
        {
            return new XSSFWorkbook(fis);        // NOSONAR - should not be closed here
        }
    }

    public static void mainWrite(String[] args) throws Throwable
    {
        SXSSFWorkbook wb = new SXSSFWorkbook(100); // keep 100 rows in memory, exceeding rows will be flushed to disk
        Sheet sh = wb.createSheet();
        for (int rownum = 0; rownum < 1000; rownum++)
        {
            Row row = sh.createRow(rownum);
            for (int cellnum = 0; cellnum < 10; cellnum++)
            {
                Cell cell = row.createCell(cellnum);
                String address = new CellReference(cell).formatAsString();
                cell.setCellValue(address);
            }

        }

        // Rows with rownum < 900 are flushed and not accessible
        for (int rownum = 0; rownum < 900; rownum++)
        {
            Assert.assertNull(sh.getRow(rownum));
        }

        // ther last 100 rows are still in memory
        for (int rownum = 900; rownum < 1000; rownum++)
        {
            Assert.assertNotNull(sh.getRow(rownum));
        }

        FileOutputStream out = new FileOutputStream("sxssf.xlsx");
        wb.write(out);
        out.close();

        // dispose of temporary files backing this workbook on disk
        wb.dispose();
    }


}

