package com.ups.UPSTrailerTracker

import org.apache.poi.openxml4j.exceptions.InvalidFormatException
import org.apache.poi.ss.usermodel.*
import java.io.File
import java.io.IOException

class TrailerTracker {
    fun loadExcelFile(excelFile: String) {
        val workbook: Workbook = WorkbookFactory.create(File("./data/UnloadSchedule.xlsx"))
        val sheet: Sheet = workbook.getSheetAt(0)
        val rowIterator: Iterator<Row> = sheet.rowIterator()

        //Pre-process to row 7 for data.
        for(i in 1..7){
            rowIterator.next()
        }

        while(rowIterator.hasNext()){
            val row: Row = rowIterator.next()
            val cellIterator: Iterator<Cell> = row.cellIterator()
            var num: Double = -1.0
            //Only uses 7 cells within the row.
            //Use array to temporarily store double values;
            val values: Array<Double> = Array(7, {0.0}) //empty array of 7 zeros

            for(i in 0..6){
                val cell: Cell = cellIterator.next()

                //Must check if the cell is a Formula or normal cell type.
                if(cell.cellType == Cell.CELL_TYPE_NUMERIC){
                    num = cell.numericCellValue
                } else if(cell.cellType == Cell.CELL_TYPE_STRING){
                    //Check if the cell is empty.
                    if(cell.stringCellValue != ""){
                        num = trimTrailerNumber(cell.stringCellValue).toDouble()
                    }
                } else if(cell.cellType == Cell.CELL_TYPE_FORMULA){
                    when(cell.cachedFormulaResultType){
                        Cell.CELL_TYPE_NUMERIC -> num = cell.numericCellValue
                        Cell.CELL_TYPE_STRING -> if(cell.stringCellValue != ""){num = trimTrailerNumber(cell.stringCellValue).toDouble()}
                    }
                }
                values[i] = num
            }
            val trailer: Trailer = Trailer(values[0].toInt(), values[1].toInt(), values[2].toInt(), values[3].toInt(), values[4].toInt(), values[5].toInt(), values[6])
            //Column 0 : Trailer Number : String
            //Column 1 : Origin Number : Double
            //Column 2 : Volume Number : Double
            //Column 3 : Smalls Number : Double
            //Column 4 : Number of Bags : Double
            //Column 5 : Number of Handles : Double
            //Column 6 : Planned Hours : Double
        }
    }

    fun trimTrailerNumber(rawNumber: String): Int {
        val str = rawNumber.replace("[^\\d]".toRegex(), "")
        if(str.isEmpty()){
            return -1
        }
        return Integer.parseInt(str)
    }
}

fun main(args: Array<String>) {
    val tracker: TrailerTracker = TrailerTracker()
    tracker.loadExcelFile("./data/UnloadSchedule.xlsx")
}