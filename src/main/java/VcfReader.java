import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class VcfReader {
    public boolean removeStatus(String inputFile, ArrayList<String> reviewStatus) {
        File tempFile = new File("data/tempFile.vcf");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            File fileObject = new File(inputFile);
            Scanner reader = new Scanner(fileObject);
            while(reader.hasNextLine()) {
                String data = reader.nextLine();
                if(stringContainsItemFromList(data, reviewStatus)) {
                    continue;
                }
                writer.write(data + System.getProperty("line.separator"));
            }
            reader.close();
            writer.close();
            return tempFile.renameTo(fileObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static boolean stringContainsItemFromList(String inputString, ArrayList<String> items) {
        return items.stream().anyMatch(inputString::contains);
    }
}
