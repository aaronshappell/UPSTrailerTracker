package ups;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;

public class TrailerTracker {
    public static void main(String[] args) throws IOException, InvalidFormatException {
        Workbook workbook = WorkbookFactory.create(new File("./data/test.xlsx"));
        System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets.");
        Sheet sheet = workbook.getSheetAt(0);
        for(Row row : sheet){
            for(Cell cell : row){
                System.out.println(cell.getStringCellValue());
            }
        }
    }
}
