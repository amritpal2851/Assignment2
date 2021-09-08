package deleterecord;
import librarybookrecord.BookShelf;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.log4j.Logger;

public class DeleteRecord implements Runnable {
    Logger logger= Logger.getLogger(DeleteRecord.class);
    BookShelf bookShelf;
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    String bookId;
    int bookIndex;

    public DeleteRecord(BookShelf bookShelf) {
        this.bookShelf = bookShelf;
    }

    public void bookRecordDelete(BookShelf bookShelf) {
        try {
            System.out.println("Please enter book ID, for which you want ");
            bookId = bufferedReader.readLine();
            if (bookShelf.arrayList.contains(bookId)) {
                bookIndex = bookShelf.arrayList.indexOf(bookId);
                for (int i = 0; i < 4; i++) {
                    bookShelf.arrayList.remove(bookIndex);
                    System.out.println("Book is deleted from successfully: ");
                }
            } else {
                System.out.println("Sorry, this book is not available in the library ");
            }
        } catch (IOException e) {
            logger.info("Exception "+ e);
        }

    }

    @Override
    public void run() {
        try {
            bookRecordDelete(bookShelf);
            Thread.sleep(500);
        } catch (InterruptedException e) {
            logger.info("Exception "+e);
        }
    }
}
