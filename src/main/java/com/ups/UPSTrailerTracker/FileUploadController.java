package com.ups.UPSTrailerTracker;

import com.ups.UPSTrailerTracker.storage.StorageService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

@RestController
public class FileUploadController {
    @Autowired
    private StorageService storageService;

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file){
        storageService.store(file);

        String fileName = file.getOriginalFilename();
        if(fileName.length() >= 5 && fileName.substring(fileName.length() - 5).equals(".xlsx")) {
            try {
                loadExcelFile("./uploadDir/UnloadSchedule.xlsx");
            } catch (IOException | InvalidFormatException e) {
                System.out.println("Error loading excel file, invalid format");
                e.printStackTrace();
            }
        } else {
            storageService.deleteAll();
            storageService.init();
        }

        return "Successfully uploaded " + fileName;
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

        //Process data needed.
        while(rowIterator.hasNext()) {
            Row row = rowIterator.next();

            Iterator<Cell> cellIterator = row.cellIterator();

            double num = -1;

            //Only uses 7 cells within the row.
            //Use array to temporarily store double values;
            double[] values = new double[7];

            for(int i = 0; i <= 6; i++) {
                Cell cell = cellIterator.next();


                //Must check if the cell is a Formula or normal cell type.
                if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                    num = cell.getNumericCellValue();
                } else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                    //Check if the cell is empty.
                    if (!cell.getStringCellValue().equals("")) {
                        num = trimIdentificationNumber(cell.getStringCellValue());
                    }
                } else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
                    switch (cell.getCachedFormulaResultType()) {
                        case Cell.CELL_TYPE_NUMERIC:
                            num = cell.getNumericCellValue();
                            break;
                        case Cell.CELL_TYPE_STRING:
                            //Check if the cell is empty. Again.
                            if (!cell.getStringCellValue().equals("")) {
                                num = trimIdentificationNumber(cell.getStringCellValue());
                            }
                            break;
                    }
                }
                values[i] = num;

                Trailer trailer1 = new Trailer(values);
            }
            //Column 0 : Trailer Number : String
            //Column 1 : Origin Number : Double
            //Column 2 : Volume Number : Double
            //Column 3 : Smalls Number : Double
            //Column 4 : Number of Bags : Double
            //Column 5 : Number of Handles : Double
            //Column 6 : Planned Hours : Double
        }
    }

    //Trims origin code into a 6 digit identification number.
    public int trimIdentificationNumber(String rawNumber) {

        String str = rawNumber.replaceAll("[^\\d]", "");

        if(str.isEmpty()) {
            return -1;
        }

        return Integer.parseInt(str);
    }

    /*
    Will we need GET to retrieve uploaded files?
    Or just temporarily upload to extract data and then delete?
     */
}
