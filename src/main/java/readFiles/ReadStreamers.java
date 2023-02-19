package readFiles;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import factory.FactoryStreamer;
import stream.Streamer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ReadStreamers extends FileReadTemplate<Streamer> {
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


        protected List<Streamer> createObjects(List<String[]> fileContent) {
            // implementare pentru crearea obiectelor in memorie din fisierul TXT
            List<Streamer> streamerList=new ArrayList<>();

            FactoryStreamer factoryStreamer=FactoryStreamer.getInstance();

            for (int i = 0; i < fileContent.size(); i++) {
                streamerList.add(factoryStreamer.createStreamer(fileContent.get(i)));
            }
            return streamerList;
        }

}
