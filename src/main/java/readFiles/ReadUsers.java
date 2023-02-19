package readFiles;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import factory.FactoryUser;
import user.User;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ReadUsers extends FileReadTemplate<User> {
    protected List<String[]> readFile(String fileName) {
        List<String[]> send=new ArrayList<>();

        try {
            boolean first=false;

            CSVReader myReader = new CSVReader(new FileReader(fileName));
            String[] data;
            while ((data=myReader.readNext())!=null) {

                if(first)
                    send.add(data);
                else
                    first=true;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (CsvValidationException | IOException e) {
            throw new RuntimeException(e);
        }
        return send;
    }


    protected List<User> createObjects(List<String[]> fileContent) {
        List<User> userList=new ArrayList<>();

        FactoryUser factoryUser=FactoryUser.getInstance();

        for (String[] strings : fileContent) {
            userList.add(factoryUser.createUser(strings));
        }
        return userList;
    }

}
