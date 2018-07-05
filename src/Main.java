import com.dots.dataparsing.CSVParsing;
import com.dots.dataparsing.DataDocument;

/**
 * Created by oleksandrd on 7/5/2018.
 */
public class Main {
    public static void main(String[] args){
        System.out.println("YYY");
        CSVParsing p1 = new CSVParsing();
        //p1.csvReadToScreen("C://My/SacramentocrimeJanuary2006.csv", ",", true);
        DataDocument d = p1.csvReadToList("C://My/SacramentocrimeJanuary2006.csv", ",", true);
        d.dataDocumentToScreen();
    }
}
