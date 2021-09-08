package librarybookrecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import org.apache.log4j.Logger;

public class NumberOfBooks {
    int index;
    String id;
    BookShelf bookShelf;
    BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
    Scanner scanner= new Scanner(System.in);
    Logger logger= Logger.getLogger(NumberOfBooks.class);
    public NumberOfBooks(BookShelf bookShelf){
        this.bookShelf=bookShelf;
    }
    public void addNumberOfBooks(BookShelf bookShelf){
        System.out.println("Please enter ID of the book ");
        try{
            id=bufferedReader.readLine();
            if(bookShelf.arrayList.contains(id)){
                index=bookShelf.arrayList.indexOf(id);
                System.out.println("How many books you want to add ? ");
                int copies=scanner.nextInt();
                int existingCopies=Integer.parseInt(bookShelf.arrayList.get(index+3));
                bookShelf.arrayList.set(index+3,String.valueOf(existingCopies+copies));
            }
            else{
                System.out.println("Sorry, this book does not exist in our record");
            }
        }catch (IOException e){
            logger.info("Exception "+e);
        }
    }

}
