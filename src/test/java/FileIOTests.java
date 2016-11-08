import main.java.utils.FileIOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static org.junit.Assert.assertTrue;

/**
 * Created by ncapasso on 11/8/2016.
 */
public class FileIOTests {
    private File file = null;
    private File tempFile = null;
    private FileIOUtils utils;

    @Before
    public void setUp() throws IOException {
        utils = new FileIOUtils();
        file = new File("test.txt");
        Files.write(file.toPath(), "default text\nnext line\nfinal line.".getBytes());
    }

    @Before
    public void prepareNewFileWithContents() throws IOException {
        tempFile = new File(File.createTempFile("newfile", ".txt").toURI());
        try{
            BufferedWriter writer = Files.newBufferedWriter(tempFile.toPath());
            writer.write("this is a test."); //set up
        }catch (Exception e){
            e.printStackTrace();
        }
        tempFile.deleteOnExit();
    }


    @Test
    public void FileShouldBeCreated() throws IOException {
        file = new File("test1.txt");
        if(file.exists())
                assertTrue("Error: file already exists",false);

        assertTrue("File was not created.",utils.createNewFile("test1.txt"));
    }

    @Test
    public void fileContentsAreOverwritten() throws IOException {
        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> foundInFile = new ArrayList<>();

        list.add("foo");
        list.add("bar");
        list.add("End.");
        utils.writeToFile(file, list, false);

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                foundInFile.add(sc.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(foundInFile.equals(list));
    }

    @Test
    public void tempFileIsCreated(){
        assertTrue("Temp file does not exist.",utils.createTempFile().exists());
    }

    @Test
    public void fileCanBeRead(){
        List<String> expectedVals = new ArrayList<>();
        expectedVals.add("default text");
        expectedVals.add("next line");
        expectedVals.add("final line.");
        List<String> retrievedVals = utils.readFile(file);
        assertTrue("Read file was returned empty.", !retrievedVals.isEmpty());
        assertTrue("Retrieved values did not match expected values", retrievedVals.equals(expectedVals));
    }

    @After
    public void tearDown(){
        try {
            Files.delete(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
