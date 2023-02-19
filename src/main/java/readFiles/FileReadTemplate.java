package readFiles;

import java.util.List;

//Template method design pattern
public abstract class FileReadTemplate<T> {
    public List<T> readFromFile(String fileName) {
        List<T> streamers;
        // citirea si crearea obiectelor in memorie se face prin apelarea metodelor abstracte
        List<String[]> fileContent = readFile(fileName);
        streamers = createObjects(fileContent);
        return streamers;
    }


    // metode abstracte care vor fi implementate in subclase
    protected abstract List<String[]> readFile(String fileName);
    protected abstract List<T> createObjects(List<String[]> fileContent);
}
