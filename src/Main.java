import com.dots.dataparsing.CSVParsing;
import com.dots.dataparsing.DataDocument;
import com.dots.dataparsing.JSONParserMy;
import com.dots.dataparsing.RestApi;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class Main {
    final static String dateFrom = "2014-01-01";
    final static String dateTo = "2014-01-02";

    public static void main(String[] args){
        System.out.println("...*******..START..*******...");

        File file = new File("C:\\My");
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }

        RestApi ra = new RestApi();
        //Make rest query (json) and save results into json file
        ra.getRestQuery("https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime="+dateFrom+"&endtime="+dateTo, "C://My/Earthquaqe.json", "json");

        //Read necessary parameters from json file to the screen (just to check if the code is correct)
        JSONParserMy jp = new JSONParserMy();
        jp.jsonToScreen("C://My/Earthquaqe.json");

        //Put necessary parameters from json file into xlsx file
        DataDocument dFromJSON = jp.jsonToData("C://My/Earthquaqe.json");
        dFromJSON.dataDocumentToXlsx("C://My/Task_Earthquaqe_From_JSON.xlsx", true);

        //Make rest query (CSV) and save results into csv file
        ra.getRestQuery("https://earthquake.usgs.gov/fdsnws/event/1/query?format=csv&starttime=2014-01-01&endtime=2014-01-02", "C://My/Earthquaqe.csv", "csv");

        //Read necessary parameters from csv file to the screen (just to check if the code is correct)
        CSVParsing parsCSV = new CSVParsing();
        parsCSV.csvReadToScreen("C://My/Earthquaqe.csv", ",", true);

        //Put necessary parameters from csv file into xlsx file
        DataDocument dFromCSV = parsCSV.csvReadToList("C://My/Earthquaqe.csv", ",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)", true);
        dFromCSV.dataDocumentToXlsx("C://My/Full_Data_Earthquaqe_From_CSV.xlsx", true);

        System.out.println("...*******..FINISH..*******...");
    }



}
