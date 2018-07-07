package com.dots.dataparsing;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class JSONParserMy {
    public void jsonToScreen(String pathToJSON){
        JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(new FileReader(pathToJSON));
            JSONObject myJson = (JSONObject) obj;
           // System.out.println(myJson);

            JSONArray nodeFeatures = (JSONArray) myJson.get("features");
            //System.out.println(nodeFeatures);


            for (Object el : nodeFeatures) {
                JSONObject feature = (JSONObject)  el;
                JSONObject geometryObject = (JSONObject) feature.get("geometry");
                JSONObject propertiesObject = (JSONObject) feature.get("properties");
                System.out.println((String) feature.get("id"));
                System.out.println(propertiesObject.get("time"));
                System.out.println(propertiesObject.get("title"));
                System.out.println(propertiesObject.get("mag"));
                System.out.println(geometryObject.get("coordinates"));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public DataDocument jsonToData(String pathToJSON){
        DataDocument d = new DataDocument();
        JSONParser parser = new JSONParser();
        List<String> lineNames = new ArrayList<>();
        List<List<String>> lines = new ArrayList<>();

        lineNames.add("Id");
        lineNames.add("Time");
        lineNames.add("Title");
        lineNames.add("Magnitude level");
        lineNames.add("Coordinates");

        try {

            Object obj = parser.parse(new FileReader(pathToJSON));
            JSONObject myJson = (JSONObject) obj;
            // System.out.println(myJson);

            JSONArray nodeFeatures = (JSONArray) myJson.get("features");
            //System.out.println(nodeFeatures);

            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            Calendar c = Calendar.getInstance();
            String magnitude = "";
            String coordinates = "";
            String[] coordinatesArray;

            for (Object el : nodeFeatures) {
                List<String> lineValues = new ArrayList<>();
                JSONObject feature = (JSONObject)  el;
                JSONObject geometryObject = (JSONObject) feature.get("geometry");
                JSONObject propertiesObject = (JSONObject) feature.get("properties");

                lineValues.add((String) feature.get("id"));

                c.setTimeInMillis(Long.parseLong(propertiesObject.get("time").toString())); //Transform time parameter in readable format
                lineValues.add(formatter.format(c.getTime()));

                lineValues.add(propertiesObject.get("title").toString());

                magnitude = propertiesObject.get("mag").toString().replace(".", ","); //Transform for Excel number format
                lineValues.add(magnitude);

                coordinatesArray = geometryObject.get("coordinates").toString().replace("[","").replace("]","").split(",");
                coordinates = "Latitude = " + CoordinateToDegree(Double.parseDouble(coordinatesArray[1])) + "; Longitude = " + CoordinateToDegree(Double.parseDouble(coordinatesArray[0])) + "; Depth = " + coordinatesArray[2] + "km";
                lineValues.add(coordinates);

                lines.add(lineValues);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        d.setHeader(lineNames);
        d.setContent(lines);
        return d;
    }

    public static final String CoordinateToDegree(double aLat) {
        int DD = (int) aLat;
        int MM = (int) ((aLat-((double) DD)) * 60);
        double SS = ((aLat-DD) * 60 - MM) * 60;
        return String.valueOf(DD) +  "°" +  String.valueOf(MM) + "'" + String.format("%.1f", SS) + "\"";
    }

}
