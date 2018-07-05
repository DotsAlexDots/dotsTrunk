package com.dots.dataparsing;

import java.util.ArrayList;
import java.util.List;

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
}
