package readFiles;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import factory.FactoryStream;
import streams.Stream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadStreams extends FileReadTemplate<Stream> {
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
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
        return send;
    }

    protected List<Stream> createObjects(List<String[]> fileContent) {
        List<Stream> streamerList=new ArrayList<>();

        FactoryStream factoryStream=FactoryStream.getInstance();

        for (String[] strings : fileContent) {
            streamerList.add(factoryStream.createStream(strings));
        }
        return streamerList;
    }
}
