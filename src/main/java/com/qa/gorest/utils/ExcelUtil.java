package com.qa.gorest.utils;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelUtil {
    private static final String sheetPath="";
    private static Workbook workbook;
    private static XSSFSheet sheet;
    private static int rows;
    private static int columns;

    public static void getTestData(String sheetName){
        FileInputStream fis = null;
        Object data[][] = null;
        try {
            fis = new FileInputStream(sheetPath);
            workbook = WorkbookFactory.create(fis);
            sheet = (XSSFSheet) workbook.getSheet("");
            rows = sheet.getPhysicalNumberOfRows();
            columns = sheet.getRow(0).getPhysicalNumberOfCells();
            for(int i=0;i<rows;i++){
                for(int j=0;j<columns;j++){
                    data[i][j]= sheet.getRow(i).getCell(j);
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
