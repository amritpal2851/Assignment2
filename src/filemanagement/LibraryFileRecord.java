package filemanagement;
import librarybookrecord.BookShelf;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LibraryFileRecord {
    public void createNewFile(BookShelf bookShelf){
        try{
            File file = new File("D:\\user\\LibraryRecord.csv");
            FileWriter fileWriter=new FileWriter(file,true);
            if(file.createNewFile()){
                fileWriter.write("Book_ID"+","+"Book Name"+","+"Author Name"+","+"Number of Copies");
            }
            else{
                int count=0;
                for(String line: bookShelf.arrayList){
                    fileWriter.write(line+",");
                    count++;
                    if(count==4){
                        fileWriter.write("\n");
                        count=0;
                    }
                }
            }fileWriter.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


}
