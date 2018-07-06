package com.dots.dataparsing;

/**
 * Created by oleksandrd on 7/5/2018.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVParsing {
    String line = "";

    public void csvReadToScreen(String csvFile, String cvsSplitBy, Boolean firstLineNames) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            int i = 1;
            int lineCounter = 1;
            List<String> lineNames = new ArrayList<>();

            while (((line = br.readLine()) != null)) {

                String[] lineString = line.split(cvsSplitBy);
                if (firstLineNames && i == 1) {
                    String[] lineNamesArray = lineString;
                    for (int j = 0; j <= lineNamesArray.length - 1; j++) {
                        lineNames.add(lineNamesArray[j]);
                    }
                } else {

                    System.out.println("[" + lineNames.get(0) + "= " + lineString[0] +
                            " , " + lineNames.get(1) + "=" + lineString[1] + " , " + lineNames.get(2) + "=" + lineString[2] +
                            " , " + lineNames.get(3) + "=" + lineString[3] + " , " + lineNames.get(4) + "=" + lineString[4] +
                            " , " + lineNames.get(5) + "=" + lineString[5] + " , " + lineNames.get(6) + "=" + lineString[6] +
                            " , " + lineNames.get(7) + "=" + lineString[7] + " , " + lineNames.get(8) + "=" + lineString[8] + "]");
                    lineCounter++;
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public DataDocument csvReadToList(String csvFile, String cvsSplitBy, Boolean firstLineNames) {
        DataDocument d = new DataDocument();
        try {
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            int i = 1;
            List<String> lineNames = new ArrayList<>();
            //List<String> lineValues = new ArrayList<>();
            List<List<String>> lines = new ArrayList<>();

            while (((line = br.readLine()) != null)) {
                String[] lineString = line.split(cvsSplitBy);
                if (firstLineNames && i == 1) {
                    String[] lineNamesArray = lineString;
                    for (int j = 0; j <= lineNamesArray.length - 1; j++) {
                        lineNames.add(lineNamesArray[j]);
                    }
                } else {
                    // lineValues.clear();
                    List<String> lineValues = new ArrayList<>();
                    for (int j = 0; j <= lineString.length - 1; j++) {
                        lineValues.add(lineString[j]);
                    }
                    lines.add(lineValues);
                 }
                d.setHeader(lineNames);
                d.setContent(lines);
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return d;
    }



}
