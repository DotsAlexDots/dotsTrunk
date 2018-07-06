import com.dots.dataparsing.CSVParsing;
import com.dots.dataparsing.DataDocument;
import com.dots.dataparsing.RestApi;

/**
 * Created by oleksandrd on 7/5/2018.
 */
public class Main {
    public static void main(String[] args){
        System.out.println("...*******..START..*******...");
        RestApi ra = new RestApi();
        ra.getRestQuery("https://earthquake.usgs.gov/fdsnws/event/1/query?format=csv&starttime=2014-01-01&endtime=2014-01-02", "C://My/1.csv");

        CSVParsing p1 = new CSVParsing();
        //p1.csvReadToScreen("C://My/SacramentocrimeJanuary2006.csv", ",", true);
        //DataDocument d = p1.csvReadToList("C://My/SacramentocrimeJanuary2006.csv", ",", true);
        DataDocument d = p1.csvReadToList("C://My/1.csv", ",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)", true);
        //d.dataDocumentToScreen();

        // d.dataDocumentToCSV("C://My/1.csv", ",", true);

        //d.dataDocumentToXls("C://My/1.xls", true);
        DataDocument d2 = d.dataConvertEarthquaqe(d);
        d2.dataDocumentToXlsx("C://My/1.xlsx", true);
    }
}
