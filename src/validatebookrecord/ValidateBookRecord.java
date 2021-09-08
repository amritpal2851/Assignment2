package validatebookrecord;
import librarybookrecord.BookRecord;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import librarybookrecord.BookShelf;
import org.apache.log4j.Logger;

public class ValidateBookRecord implements Runnable {
    BookRecord bookRecord;
    BookShelf bookShelf;
    int id;
    String bookName;
    String bookAuthorName;
    int numberOfCopies;
    Logger log=Logger.getLogger(ValidateBookRecord.class);


    public ValidateBookRecord(BookRecord bookRecord, BookShelf bookShelf) {
        this.bookRecord = bookRecord;
        this.bookShelf=bookShelf;
    }

    public int validateBookId(BookRecord bookRecord) {
        String bookId = bookRecord.getBookId();
        Pattern pattern = Pattern.compile("\\d{5}", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(bookId);
        if (matcher.find()) {
            this.id = Integer.parseInt(bookId);
            return 1;
        } else {
            log.info("ID is invalid"+ bookRecord.getBookId());
            return 0;
        }
    }

    public int validateBookName(BookRecord bookRecord) {
        String bookName = bookRecord.getBookName();
        Pattern pattern = Pattern.compile("^[a-zA-Z]+.*$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(bookName);
        if (matcher.find()) {
            this.bookName = bookName;
            return 1;
        } else {
            log.info("Name is invalid"+ bookRecord.getBookName());
            return 0;
        }
    }

    public int validateAuthorName(BookRecord bookRecord) {
        String bookAuthorName = bookRecord.getAuthorName();
        Pattern pattern = Pattern.compile("^[a-zA-Z]+.*$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(bookAuthorName);
        if (matcher.find()) {
            this.bookAuthorName = bookAuthorName;
            return 1;
        } else {
            log.info("Author Name is invalid"+ bookRecord.getAuthorName());
            return 0;
        }
    }

    public int validateNumberOfCopies(BookRecord bookRecord) {
        String numberOfCopies = bookRecord.getNumberOfBooks();
        Pattern pattern = Pattern.compile("^[0-9]+$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(numberOfCopies);
        if (matcher.find()) {
            this.numberOfCopies = Integer.parseInt(numberOfCopies);
            return 1;
        } else {
            log.info("Number of copies is invalid"+ bookRecord.getAuthorName());
            return 0;
        }
    }

    @Override
    public void run() {
        if (validateBookId(bookRecord) == 1) {
            //validateBookId(bookRecord);
            if (validateBookName(bookRecord) == 1) {
               // validateBookName(bookRecord);
                if (validateBookName(bookRecord) == 1) {
                    //validateAuthorName(bookRecord);
                    if (validateAuthorName(bookRecord) == 1) {
                        //validateNumberOfCopies(bookRecord);
                        if (validateNumberOfCopies(bookRecord) == 1) {
                            bookShelf.libraryRecord(id,bookName,bookAuthorName,numberOfCopies);
                        }
                    }
                }
            }
        }
    }
}