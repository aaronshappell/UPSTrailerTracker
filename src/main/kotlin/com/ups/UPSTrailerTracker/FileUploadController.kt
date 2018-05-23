package com.ups.UPSTrailerTracker

import com.ups.UPSTrailerTracker.storage.StorageService
import com.ups.UPSTrailerTracker.trailer.Trailer
import com.ups.UPSTrailerTracker.trailer.TrailerService
import org.apache.poi.ss.usermodel.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.io.File

@RestController
class FileUploadController {
    @Autowired
    lateinit var storageService: StorageService
    @Autowired
    lateinit var trailerService: TrailerService

    @PostMapping("/")
    fun handleFileUpload(@RequestParam("file") file: MultipartFile): String {
        storageService.store(file)

        val fileName: String = file.originalFilename as String
        var trailers: ArrayList<Trailer>? = null
        if(fileName.length >= 5 && fileName.substring(fileName.length - 5) == ".xlsx") {
            trailers = extractTrailers(storageService.load(fileName).toString())
        }
        if(trailers != null) {
            for(trailer in trailers) {
                trailerService.addTrailer(trailer)
            }
        }
        storageService.deleteAll()
        storageService.init()

        return "Successfully uploaded $fileName"
    }

    fun extractTrailers(excelFile: String): ArrayList<Trailer> {
        val trailers: ArrayList<Trailer> = ArrayList<Trailer>()
        //Process the excel file
        val workbook: Workbook = WorkbookFactory.create(File(excelFile))
        val sheet: Sheet = workbook.getSheetAt(0)
        val rowIterator: Iterator<Row> = sheet.rowIterator()

        //Pre-process to row 7 for data
        for(i in 1..7) {
            rowIterator.next()
        }

        //Process data needed
        while(rowIterator.hasNext()) {
            val row: Row = rowIterator.next()
            val cellIterator: Iterator<Cell> = row.cellIterator()
            var num: Double = -1.0

            val values: Array<Double> = Array(7, {0.0})

            cellIterator.next()

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
            trailers.add(Trailer(values[0].toInt(), values[1].toInt(), values[2].toInt(), values[3].toInt(), values[4].toInt(), values[5].toInt(), values[6]))
            //Column 0 : Trailer Number : String
            //Column 1 : Origin Number : Double
            //Column 2 : Volume Number : Double
            //Column 3 : Smalls Number : Double
            //Column 4 : Number of Bags : Double
            //Column 5 : Number of Handles : Double
            //Column 6 : Planned Hours : Double
        }

        return trailers
    }

    //Trims origin code into a 6 digit identification number.
    fun trimTrailerNumber(rawNumber: String): Int {
        val str: String = rawNumber.replace("[^\\d]".toRegex(), "")
        if(str.isEmpty()){
            return -1
        }
        return Integer.parseInt(str)
    }
}