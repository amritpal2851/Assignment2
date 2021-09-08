package librarybookrecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AddBookRecord implements Runnable {
    BookRecord bookRecord;

    public AddBookRecord(BookRecord bookRecord) {
        this.bookRecord = bookRecord;
    }

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public void setBookId(BookRecord bookRecord) {
        try {
            System.out.println("Please enter book Id : ");
            String bookId = bufferedReader.readLine();
            bookRecord.setBookId(bookId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setBookName(BookRecord bookRecord) {
        try {
            System.out.println("Please enter book name ");
            String bookName = bufferedReader.readLine();
            bookRecord.setBookName(bookName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAuthorName(BookRecord bookRecord) {
        try {
            System.out.println("Please enter Author Name: ");
            String authorName = bufferedReader.readLine();
            bookRecord.setAuthorName(authorName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setNumberOfBooks(BookRecord bookRecord) {
        try {
            System.out.println("Please enter number of copies for books ");
            String numberOfCopies = bufferedReader.readLine();
            bookRecord.setNumberOfBooks(numberOfCopies);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        setBookId(bookRecord);
        setBookName(bookRecord);
        setAuthorName(bookRecord);
        setNumberOfBooks(bookRecord);
    }
}
