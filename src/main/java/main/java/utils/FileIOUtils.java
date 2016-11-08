package main.java.utils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.file.StandardOpenOption.APPEND;

/**
 * Created by ncapasso on 11/8/2016.
 */
public class FileIOUtils {

    Charset charset = Charset.forName("UTF-8"); //support international character sets
    //private final static Logger LOGGER = Logger.getLogger(FileIOUtils.class.getName());

    public boolean createNewFile(String name) throws IOException {
        File newFile = new File(name);
        try {
            return newFile.createNewFile();
        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public List<String> readFile(File file){
        List<String> fileContents = new ArrayList<>();
        try(Stream<String> stream = Files.lines(Paths.get(file.toURI()))) {
            fileContents = stream.collect(Collectors.toList());
        }catch(IOException e){
            e.printStackTrace();
        }
        return fileContents;
    }

    public void writeToFile(File file, ArrayList<String> content, boolean append){
        Path path = Paths.get(file.toURI());
        try{
            if(append){
                for(String s : content) {
                    s = s + "\n";
                    Files.write(path, s.getBytes(), APPEND);
                }
            }
            else{
                Files.write(path, new byte[0]); //clears file
                for(String s : content){
                    s = s + "\n";
                    Files.write(path, s.getBytes(), APPEND);
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public File createTempFile(){
        File f = null;
        try{
            f = File.createTempFile("tmp", ".txt");
            f.deleteOnExit();
        } catch(IOException e){
            e.printStackTrace();
        }

        return f;
    }





}
