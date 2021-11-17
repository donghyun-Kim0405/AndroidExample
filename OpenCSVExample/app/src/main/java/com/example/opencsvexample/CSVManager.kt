package com.example.opencsvexample

import android.util.Log
import com.opencsv.CSVReader
import com.opencsv.CSVWriter
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.io.IOException

class CSVManager(private val filePath: String) {

    fun writeCSV(fileName: String, dataList: ArrayList<Array<String>>) {
        try {
            FileWriter(File("$filePath/$fileName")).use { fw ->
                CSVWriter(fw).use {
                    it.writeAll(dataList)
                }
            }
        } catch (e: IOException) { Log.e("SCVManager", e.message.toString()) }
    }

    fun writeEach(fileName: String, dataList: ArrayList<Array<String>>) {
        try {
            FileWriter(File("$filePath/$fileName")).use { fw ->
                // writeNext()를 이용한 리스트 데이터 등록
                CSVWriter(fw).use {
                    for (data in dataList) {
                        it.writeNext(data)
                    }
                }
            }
        } catch (e: IOException) { Log.e("SCVManager", e.message.toString()) }
    }

    fun readCSV(fileName: String) : List<Array<String>> {
        return try {
            FileReader("$filePath/$fileName").use { fr ->
                // readAll()을 이용해 데이터 읽기
                CSVReader(fr).use {
                    it.readAll()
                }
            }
        } catch (e: IOException) {
            Log.e("SCVManager", e.message.toString())
            listOf()
        }
    }

    fun readEach(fileName: String) : List<Array<String>> {
        return try {
            FileReader("$filePath/$fileName").use { fr ->
                val dataList = arrayListOf<Array<String>>()

                //for문을 이용해 데이터 읽기
                CSVReader(fr).use {
                    for (data in it) {
                        dataList.add(data)
                        Log.e("CSVManager-readCsvData", data.toString())
                    }
                }
                dataList
            }
        } catch (e: IOException) {
            Log.e("SCVManager", e.message.toString())
            listOf()
        }
    }
}