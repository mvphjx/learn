package com.learn.http.test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.hanjx.test.ABISHelper;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class GeoInfo
{
    public static final String url = "https://restapi.amap.com/v3/geocode/geo?address={{address}}&output=json&key={{key}}";
    public static final String key = "1280d3e38676dac717b6462f05c9347d";
    public static final String UNIT_INFO = "C:\\Users\\han\\Desktop\\2018\\work\\gis\\unitinfo.xlsx";

    public static void main(String[] args) throws Exception
    {
        XSSFWorkbook workbook = new XSSFWorkbook(UNIT_INFO);
        XSSFSheet sheetAt = workbook.getSheetAt(0);
        int lastRowNum = sheetAt.getLastRowNum();
        for (int i = 1; i <=lastRowNum; i++)
        {
            XSSFRow row = sheetAt.getRow(i);
            getGeoInfo(row);
        }
        FileOutputStream out = new FileOutputStream(UNIT_INFO,true);
        workbook.write(out);
        out.close();
        workbook.close();
    }
    private static void getGeoInfo(XSSFRow row) throws IOException
    {
        if (ABISHelper.isEmpty(row))
        {
            return;
        }
        XSSFCell unitCodeCell = row.getCell(0);
        XSSFCell unitNameCell = row.getCell(1);
        XSSFCell lonCell = row.getCell(2);
        XSSFCell latCell = row.getCell(3);
        if (ABISHelper.isEmpty(unitCodeCell)||ABISHelper.isEmpty(unitNameCell)){
            return;
        }
        String unitCode= unitCodeCell.getStringCellValue();
        String unitName= unitNameCell.getStringCellValue();
        if(ABISHelper.isEmpty(lonCell)||ABISHelper.isEmpty(latCell)){
            String lonlat = getGeoInfo(unitName);
            if(lonlat.split(",").length==2){
                String lon = lonlat.split(",")[0];
                String lat = lonlat.split(",")[1];
                if(ABISHelper.isEmpty(lonCell)){
                    lonCell = row.createCell(2);
                }
                if(ABISHelper.isEmpty(latCell)){
                    latCell = row.createCell(3);
                }
                lonCell.setCellValue(lon);
                latCell.setCellValue(lat);
                System.out.println(unitName+"-"+lon+"-"+lat);
            }
        }
    }
    private static String getGeoInfo(String unitName) throws IOException
    {
        unitName = unitName.replaceAll("[\\r\\n\\t]", "");
        String thisUrl = url.replace("{{address}}", unitName).replace("{{key}}", key);
        ObjectMapper mapper = new ObjectMapper();
        URL url = new URL(thisUrl);
        JsonNode jsonNode = mapper.readTree(url);
        JsonNode location = jsonNode.findValue("location");
        String lonlat = "";
        if(!ABISHelper.isEmpty(location)){
            if(location.isArray()){
                lonlat = location.get(0).toString();
            }else{
                lonlat = location.asText();
            }
        }
        return lonlat;
    }

}