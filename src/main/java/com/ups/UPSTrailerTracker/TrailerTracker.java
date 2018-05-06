package com.ups.UPSTrailerTracker;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;

import java.util.*;

public class TrailerTracker {
    private Map<Integer, Trailer> trailers;

    public TrailerTracker(){
        trailers = new TreeMap<Integer, Trailer>();
    }

    public void loadExcelFile(String excelFile) throws IOException, InvalidFormatException {
        //Process the excel file.
        Workbook workbook = WorkbookFactory.create(new File("./data/UnloadSchedule.xlsx"));

        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.rowIterator();

        //Pre-process to row 7 for data.
        for(int i = 1; i <= 6; i++) {
            rowIterator.next();
        }

        while(rowIterator.hasNext()) {
            Row row = rowIterator.next();

            Iterator<Cell> cellIterator = row.cellIterator();

            double num;
            String str;


            for(int i = 0; i <= 6; i++) {
                Cell cell = cellIterator.next();

                if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                    num = cell.getNumericCellValue();
                } else if(cell.getCellType() == Cell.CELL_TYPE_STRING) {
                    str = cell.getStringCellValue();
                } else if(cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
                    switch (cell.getCachedFormulaResultType()) {
                        case Cell.CELL_TYPE_NUMERIC:
                            num = cell.getNumericCellValue();
                            break;
                        case Cell.CELL_TYPE_STRING:
                            str = cell.getStringCellValue();
                            break;
                    }
                }
            }
            //Column 1 : Trailer Number : String
            //Column 2 : Origin Number : Double
            //Column 3 : Volume Number : Double
            //Column 4 : Smalls Number : Double
            //Column 5 : Number of Bags : Double
            //Column 6 : Number of Handles : Double
            //Column 7 : Planned Hours : Double
            //Anything beyond is inputed by the user, skip to the next line.


        }
        /*while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                double num = 0;
                String str = "";
                if(cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
                    switch(cell.getCachedFormulaResultType()) {
                        case Cell.CELL_TYPE_NUMERIC:
                            num = cell.getNumericCellValue();
                            System.out.println(num);
                            break;
                        case Cell.CELL_TYPE_STRING:
                            str = cell.getRichStringCellValue();
                            System.out.println(str);
                            break;
                    }
                } else {
                    switch(cell.getCellType()) {
                        case Cell.CELL_TYPE_NUMERIC:
                            num = cell.getNumericCellValue();
                            System.out.println(num);
                            break;
                        case Cell.CELL_TYPE_STRING:
                            str = cell.getRichStringCellValue();
                            System.out.println(str);
                            break;
                    }
                }

                //Column 1 : Trailer Number : String
                //Column 2 : Origin Number : Double
                //Column 3 : Volume Number : Double
                //Column 4 : Smalls Number : Double
                //Column 5 : Number of Bags : Double
                //Column 6 : Number of Handles : Double
                //Column 7 : Planned Hours : Double
                //Anything beyond is inputed by the user, skip to the next line.

            }
        }*/
    }

    //Trims origin code into a 6 digit identification number.
    public int trimIdentificationNumber(String rawNumber) {
        return 0;
    }

    public static void main(String[] args) {
        TrailerTracker tracker = new TrailerTracker();
        try {
            tracker.loadExcelFile("./data/UnloadSchedule.xlsx");
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
    }
}
