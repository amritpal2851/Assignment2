package borrobook;
import librarybookrecord.BookShelf;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;

public class BorrowBook implements Runnable {
    Logger logger=Logger.getLogger(BorrowBook.class);
    BookShelf bookShelf;
    private String studentName;
    LocalDateTime myDateObj = LocalDateTime.now();
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private String formattedDate = myDateObj.format(myFormatObj);
    String bookId;
    int index;
    ArrayList<String> bookDetails = new ArrayList<>();
    BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
    public BorrowBook(BookShelf bookShelf){
        this.bookShelf=bookShelf;
    }
    public void studentNameValidation(){
        System.out.println("Please enter your name :");
        try{
           String studentTempName=bufferedReader.readLine();
            Pattern pattern = Pattern.compile("^[a-zA-Z]+.*$", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(studentTempName);
            if(matcher.find()){
                this.studentName=studentTempName;
            }
            else{
                System.out.println("Enter name is invalid, please your name again ");
                studentNameValidation();
            }
        }catch (IOException e){
            logger.info("Exception "+e);
        }
    }

    public int bookAvailableValidation() {
        System.out.println("Please enter book ID: ");
        try{
            bookId=bufferedReader.readLine();
        }catch (IOException e){
            logger.info("IO Exception "+e);
        }
        if (bookShelf.arrayList.contains(bookId)) {
            int numberOfCopies;
            index = bookShelf.arrayList.indexOf(bookId);
            String number = bookShelf.arrayList.get(index + 3);
            numberOfCopies = Integer.parseInt(number);
            if (numberOfCopies == 0) {
                System.out.println("All books are borrowed, please come after few days ");
            } else {
                String copies = String.valueOf(numberOfCopies -= 1);
                BookShelf.arrayList.set(index + 3, copies);
                String bookName = bookShelf.arrayList.get(index + 1);
                String authorName = bookShelf.arrayList.get(index + 2);
                bookDetails.add(bookName);
                bookDetails.add(authorName);
                return 1;

            }
        } else {
            System.out.println("The Library does not have");
        }
        return 0;
    }

    public void borrowedBook() {
        studentNameValidation();
        if (bookAvailableValidation() == 1) {
            bookShelf.studentBookRecord(formattedDate, studentName, bookDetails.get(0), bookDetails.get(1));
        } else {
            System.out.println("Book is not available in the library");
        }
    }

    @Override
    public void run() {
        try {
            borrowedBook();
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            logger.info("Exception "+e);
        }
    }
}
