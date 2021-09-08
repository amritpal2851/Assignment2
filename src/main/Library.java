package main;

import borrobook.BorrowBook;
import deleterecord.DeleteRecord;
import filemanagement.LibraryFileRecord;
import librarybookrecord.AddBookRecord;
import librarybookrecord.BookRecord;
import librarybookrecord.BookShelf;
import librarybookrecord.NumberOfBooks;
import validatebookrecord.ValidateBookRecord;

import java.io.FileNotFoundException;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class Library {

    static BookRecord bookRecord = new BookRecord();
    static BookShelf bookShelf = new BookShelf();
    static AddBookRecord addBookRecord = new AddBookRecord(bookRecord);
    static ValidateBookRecord validateBookRecord = new ValidateBookRecord(bookRecord, bookShelf);
    static BorrowBook borrowBook = new BorrowBook(bookShelf);
    static DeleteRecord deleteRecord = new DeleteRecord(bookShelf);
    static Logger log = Logger.getLogger(Library.class);
    static Scanner scanner = new Scanner(System.in);
    static NumberOfBooks numberOfBooks=new NumberOfBooks(bookShelf);
    static LibraryFileRecord libraryFileRecord=new LibraryFileRecord();

    public static int menuOption() {
        int option;
        System.out.println("Please choose from below options :");
        System.out.println("1-> Add Book or Add new Copies");
        System.out.println("2-> Borrow Book ");
        System.out.println("3-> Delete book");
        System.out.println("4-> Library Record");
        System.out.println("5-> Borrow Record ");
        System.out.println("6-> Exit ");
        option = scanner.nextInt();
        return option;
    }

    public static void main(String[] args) {
        bookShelf.fetchDataFromFile();
        while (true) {
            int option = menuOption();
            if (option > 6) {
                System.out.println("You have enter invalid option ");
                menuOption();
            } else {
                switch (option) {
                    case 1:
                        System.out.println("1-> Add new book record ");
                        System.out.println("2-> Add new copies for Already existing book");
                        int addRecordOption = scanner.nextInt();
                        if (addRecordOption > 2) {
                            System.out.println("You have entered invalid option ");
                        }
                        else{
                            switch (addRecordOption){
                                case 1:
                                    try {
                                        Thread addBookRecordThread = new Thread(addBookRecord);
                                        Thread validationThread = new Thread(validateBookRecord);
                                        addBookRecordThread.start();
                                        addBookRecordThread.join();
                                        System.out.println("Data validation is in process, if data found to be correct it will be stored in Library, else discarded");
                                        validationThread.start();
                                        continue;
                                    } catch (InterruptedException e) {
                                        log.info("Interrupted Exception in case 1");
                                        continue;
                                    }
                                case 2:
                                    numberOfBooks.addNumberOfBooks(bookShelf);
                                    continue;
                            }
                        }
                    case 2:
                        try {
                            Thread borrowThread = new Thread(borrowBook);
                            borrowThread.start();
                            borrowThread.join();
                        } catch (InterruptedException e) {
                            log.info("Interrupted Exception in case 2");
                        }
                        continue;
                    case 3:
                        try {
                            Thread deleteThread = new Thread(deleteRecord);
                            deleteThread.start();
                            deleteThread.join();
                        } catch (InterruptedException e) {
                            log.info("Interrupted Exception in case 3");
                        }
                        continue;
                    case 4:
                        int counter = 0;
                        for (String string : BookShelf.arrayList) {
                            System.out.print(string + "|");
                            counter++;
                            if (counter == 4) {
                                System.out.println(" ");
                                counter = 0;
                            }
                        }
                        continue;
                    case 5:
                        int count = 0;
                        for (String string : BookShelf.borrowBookRecord) {
                            System.out.print(string + "|");
                            count++;
                            if (count == 4) {
                                System.out.println(" ");
                                count = 0;
                            }
                        }
                        continue;
                    case 6:
                        libraryFileRecord.createNewFile(bookShelf);
                        System.out.println("Thank you for visiting library :");
                        System.exit(0);
                        break;
                }
            }

        }
    }
}