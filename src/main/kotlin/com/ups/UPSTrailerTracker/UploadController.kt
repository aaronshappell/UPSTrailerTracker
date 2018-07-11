package com.ups.UPSTrailerTracker

import com.ups.UPSTrailerTracker.storage.StorageService
import com.ups.UPSTrailerTracker.trailer.Trailer
import com.ups.UPSTrailerTracker.trailer.TrailerService
import org.apache.poi.openxml4j.exceptions.InvalidFormatException
import org.apache.poi.ss.usermodel.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.IOException

@Controller
class UploadController {
    @Autowired
    lateinit var storageService: StorageService
    @Autowired
    lateinit var trailerService: TrailerService

    val maxFileSize: Long = 5242880 // 5MB

    @GetMapping("/upload")
    fun getUploadView(@RequestParam(value = "success", defaultValue = "", required = false) success: String,
                      @RequestParam(value = "error", defaultValue = "", required = false) error: String,
                      model: Model): String {
        model.addAttribute("view", "upload")
        return "layout"
    }

    @PostMapping("/upload")
    fun uploadFile(@RequestParam("file") file: MultipartFile): String {
        // don't store the file if it's too big
        if(file.size > maxFileSize){
            return "redirect:/upload?error=filesize"
        }
        storageService.store(file)

        val fileName: String = file.originalFilename as String
        var trailers: ArrayList<Trailer>? = null
        var error: String = ""
        if(fileName.length >= 5 && fileName.substring(fileName.length - 5) == ".xlsx") {
            trailers = extractTrailers(storageService.load(fileName).toString())
        }
        if(trailers != null) {
            for(trailer in trailers) {
                trailerService.addTrailer(trailer)
            }
        } else{
            error = "invalidfile"
        }
        storageService.deleteAll()
        storageService.init()

        // no error
        if(error == ""){
            return "redirect:/upload?success=true"
        }

        return "redirect:/upload?error=$error"
    }

    private fun extractTrailers(excelFile: String): ArrayList<Trailer>? {
        val trailers: ArrayList<Trailer> = ArrayList<Trailer>()
        //Process the excel file
        val workbook: Workbook = try {
            WorkbookFactory.create(File(excelFile))
        } catch(e: IOException){
            null
        } catch(e: InvalidFormatException){
            null
        } ?: return null
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

                //Must check if the cell is a formula or normal cell type.
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
                        Cell.CELL_TYPE_STRING -> if(cell.stringCellValue != ""){num =
                                trimTrailerNumber(cell.stringCellValue).toDouble()}
                    }
                }
                values[i] = num
            }

            //Create a temporary trailer to check if it's empty.
            val tempTrailer = Trailer(values[0].toInt(), values[1].toInt(), values[2].toInt(),
                    values[3].toInt(), values[4].toInt(), values[5].toInt(), values[6])

            //Doesn't add trailer if it's empty.
            if(!tempTrailer.isEmpty()) {
                trailers.add(tempTrailer)
            }

            //Column 0 : Trailer Number
            //Column 1 : Origin Number
            //Column 2 : Volume Number
            //Column 3 : Smalls Number
            //Column 4 : Number of Bags
            //Column 5 : Number of Handles
            //Column 6 : Planned Hours
        }
        return trailers
    }

    //Trims origin code into a 6 digit identification number.
    //@param rawNumber : Value to be processed into a number.
    private fun trimTrailerNumber(rawNumber: String): Int {
        val str: String = rawNumber.replace("[^\\d]".toRegex(), "")
        if(str.isEmpty()){
            return -1
        }
        return Integer.parseInt(str)
    }
}