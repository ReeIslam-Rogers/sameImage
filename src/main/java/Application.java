import models.Dictionary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.PrintService;
import services.SaveFileService;
import services.StdoutService;


public class Application {
    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {

        if(args.length < 2){
            printDoc();
        } else {
            logger.info("Application Started ...");

            String dir = args[0];
            String[] ext = args[1].split(",");

            Dictionary dictionary = new Dictionary(dir,ext);

            PrintService printService;

            if(args.length < 3)
            {
                printService = new StdoutService();
            } else {
                printService = new SaveFileService(args[2]);
            }

            printService.print(dictionary.getSet());

        }
    }

    private static void printDoc(){
        System.out.println("" +
                "\nUsage: sameImage DIR FILE_EXTENSION OUT_FILE" +
                "\nEx: sameImage /user jpg" +
                "\n" +
                "\nFILE_EXTENSION:" +
                "\n    For single extension just the extension" +
                "\n    For multiple extension use comma ie. jpg,bmp" +
                "\n" +
                "\nOUT_FILE: " +
                "\n    Output file name and location if save it to a file." +
                "\n\u001B[1mNote: only JPG, BMP and PNG are accepted.");

    }
}
