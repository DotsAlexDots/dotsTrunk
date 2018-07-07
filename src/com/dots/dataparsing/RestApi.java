package com.dots.dataparsing;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;
import static java.lang.System.*;

public class RestApi {

    public void getRestQuery(String urlLink, String pathToFile, String typeOfFile){
        try {
            FileWriter writer = new FileWriter(pathToFile);
            URL url = new URL(urlLink);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String output;


            while ((output = br.readLine()) != null) {
                writer.append(output);
                if (typeOfFile == "csv")
                {writer.write(lineSeparator());}
               // System.out.println(output);

            }

            conn.disconnect();
            writer.flush();
            writer.close();



        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
