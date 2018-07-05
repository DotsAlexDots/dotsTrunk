package com.dots.dataparsing;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;


/**
 * Created by oleksandrd on 7/5/2018.
 */
public class DataDocument {
    private List<String> header;
    private List<List<String>> content;

    public void DataDocument(){
        header = new ArrayList<>();
        content = new ArrayList<>();
    }

    public void setHeader(List<String> header) {
        this.header = header;
    }

    public void setContent(List<List<String>> content) {
        this.content = content;
    }

    public List<List<String>> getContent() {
        return content;
    }

    public List<String> getHeader() {
        return header;
    }

    public void dataDocumentToScreen(){
        List<String> lineNames = this.header;
        List<List<String>> lines = this.content;
        //System.out.println(lines.toString());
        int lineCounter = 1;
        for (List<String> listLines : lines){
            int i =0;

            System.out.print("[");
            for (String value: listLines){
                if (i==0)
                {System.out.print(lineNames.get(i)+"=" + "\"" +  value+ "\"");}
                else
                {System.out.print(" , " + lineNames.get(i)+"=" + "\"" + value+ "\"");}
                i++;
            }
            System.out.println("]");
            lineCounter++;

        }
        lineCounter++;
    }

    public void dataDocumentToCSV(String csvFile, String cvsSplitBy, Boolean firstLineNames){
        List<String> lineNames = this.header;
        List<List<String>> lines = this.content;
        boolean first =true;
        try {
            FileWriter writer = new FileWriter(csvFile);

            if (firstLineNames) {
                first = true;
                StringBuilder sb = new StringBuilder();
                for (String value : lineNames) {
                    if (!first) {
                        sb.append(cvsSplitBy);
                    }
                    sb.append(value);
                    first = false;
                }
                sb.append("\n");
                writer.append(sb.toString());
            }

            for (List<String> listLines : lines){
                first = true;
                StringBuilder sb = new StringBuilder();
                for (String value: listLines){
                    if (!first) {
                        sb.append(cvsSplitBy);
                    }
                    sb.append(value);
                    first = false;
                }
                sb.append("\n");
                writer.append(sb.toString());
            }

            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void dataDocumentToXls(String excelFile, Boolean firstLineNames){
        List<String> lineNames = this.header;
        List<List<String>> lines = this.content;
        boolean first =true;
        try {
            HSSFWorkbook hwb = new HSSFWorkbook();
            HSSFSheet sheet = hwb.createSheet("data");

            if (firstLineNames) {
                HSSFRow row = sheet.createRow(0);
                int i = 0; //counter for columns in Excel
                for (String value : lineNames) {
                    HSSFCell cell = row.createCell(i);
                    cell.setCellValue(value);
                    i++;
                }
            }

            int j=1; //counter for rows in Excel
            for (List<String> listLines : lines){
                HSSFRow row = sheet.createRow(j);
                int i = 0; //counter for columns in Excel
                for (String value: listLines){
                    HSSFCell cell = row.createCell(i);
                    cell.setCellValue(value);
                    i++;
                }
                j++;
            }

            FileOutputStream fileOut = new FileOutputStream(excelFile);
            hwb.write(fileOut);
            fileOut.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void dataDocumentToXlsx(String excelFile, Boolean firstLineNames){
        List<String> lineNames = this.header;
        List<List<String>> lines = this.content;
        try {
            XSSFWorkbook hwb = new XSSFWorkbook();
            XSSFSheet sheet = hwb.createSheet("data");

            if (firstLineNames) {
                XSSFRow row = sheet.createRow(0);
                int i = 0; //counter for columns in Excel
                for (String value : lineNames) {
                    XSSFCell cell = row.createCell(i);
                    cell.setCellValue(value);
                    i++;
                }
            }

            int j=1; //counter for rows in Excel
            for (List<String> listLines : lines){
                XSSFRow row = sheet.createRow(j);
                int i = 0; //counter for columns in Excel
                for (String value: listLines){
                    XSSFCell cell = row.createCell(i);
                    cell.setCellValue(value);
                    i++;
                }
                j++;
            }

            FileOutputStream fileOut = new FileOutputStream(excelFile);
            hwb.write(fileOut);
            fileOut.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
