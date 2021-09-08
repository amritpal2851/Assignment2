package librarybookrecord;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import org.apache.log4j.Logger;

public class BookShelf {
    public static ArrayList<String> arrayList = new ArrayList<>();
    public static ArrayList<String> borrowBookRecord = new ArrayList<>();
    Logger logger=Logger.getLogger(BookShelf.class);

    public void fetchDataFromFile(){
        String line;
        try{
            File file = new File("D:\\user\\LibraryRecord.csv");
            BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
            while((line=bufferedReader.readLine())!=null){
                Collections.addAll(arrayList,line.split(","));
            }
            bufferedReader.close();
            file.delete();
        }catch(Exception e){
          logger.info("File not found");
        }
    }

    public void libraryRecord(int id, String bookName, String authorName, int numberOfCopies) {
        arrayList.add(String.valueOf(id));
        arrayList.add(bookName);
        arrayList.add(authorName);
        arrayList.add(String.valueOf(numberOfCopies));

    }

    public void studentBookRecord(String dateTime, String studentName, String bookId, String bookName) {
        borrowBookRecord.add(dateTime);
        borrowBookRecord.add(studentName);
        borrowBookRecord.add(bookId);
        borrowBookRecord.add(bookName);
    }
}
