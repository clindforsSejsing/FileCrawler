import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class FileCrawler {

   /**
    * Make an input with scanner for the user [x].
    * Make a method that searches trough file/directory for the chosen word and give feedback depending on result (once for each file).[X]
    * Make a method that goes trough rootfile (src/random) and see if file is a file- otherwise
    * treated as a directory and opened and searched again for files (recursion).
    * If file is found- call method that searches trough textfile for word and print path to System.out.[x]
    **///CLS
   public static void main(String[] args) throws Exception {
      Scanner userInput = new Scanner(System.in);
      System.out.println("Write the word you are searching for :  ");
      String mySearchWord = userInput.nextLine();

      File dir = new File("src\\random\\"); //folderpath to random directory

      isAFile(dir, mySearchWord);

      /* wordInFile("src\\random\\dogs\\first.txt", mySearchWord);  **test to see errormessage if file does not exist*/
   }

   private static String isAFile(File fetchedFile, String mySearchWord) throws IOException {

      if (fetchedFile.isFile()) { // see if its a file
         String pathWay = fetchedFile.getCanonicalPath();//path to file
         if (wordInFile(pathWay, mySearchWord)) {
            return pathWay;

         }
      } else { //if it's a directory
         File[] listOfFiles = fetchedFile.listFiles(); // make an array of directories


         if (listOfFiles != null && listOfFiles.length < 1) { //control- make recursion stop
            System.err.println("No more files/directories to search..");

         } else
            for ( File directory : listOfFiles ) { //for every index
               isAFile(directory, mySearchWord);
            }
      }

      return "";
   }

   public static Boolean wordInFile(String filePath, String mySearchWord) {

      File file = new File(filePath);

      Scanner myReader = null;
      try {

         myReader = new Scanner(file);

         while (myReader.hasNext()) {
            String data = myReader.next();
            if (data.equals(mySearchWord)) {
               System.out.println("Found in: " + file);
               return true;

            }//end.
         }
      } catch (FileNotFoundException e) {
         System.err.println("Failed to open - " + file);
         /*e.printStackTrace();*/
      } finally {
         if (myReader != null) {
            myReader.close();
         }
      }
      return false;
   }
}




