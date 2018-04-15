package ups;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;

import java.util.*;

public class TrailerTracker {
    public static void main(String[] args) throws IOException, InvalidFormatException {
        //Each trailer will be linked by its identification number.
        Map<Integer, Trailer> trailers = new TreeMap<Integer, Trailer>();

        //Process the excel file.
        DataFormatter dataFormatter = new DataFormatter();
        Workbook workbook = WorkbookFactory.create(new File("./data/UnloadSchedule.xlsx"));
        System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets.");
        Sheet sheet = workbook.getSheetAt(0);
        for(Row row : sheet){
            for(Cell cell : row){
                System.out.println(dataFormatter.formatCellValue(cell));
            }
        }

        /*
        Iterator<Row> rowIterator = sheet.rowIterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                String cellValue = dataFormatter.formatCellValue(cell);
                System.out.print(cellValue);
            }
            System.out.println();
        }
        */
    }

    //Trims origin code into a 6 digit identification number.
    public int trimIdentificationNumber() {


        return 0;
    }
}
