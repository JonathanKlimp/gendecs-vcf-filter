import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class VcfReader {
    String filename = "data/clinvar_20220205.vcf";
    File inputFile = new File(filename);
    File tempFile = new File("data/tempFile.vcf");

    public ArrayList<String> zeroStar = new ArrayList<>(
            List.of("no_assertion_provided",
                    "no_assertion_criteria_provided")
    );

    public void readFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            File fileObject = new File("data/clinvar_20220205.vcf");
//            File fileObject = new File("data/lp.vcf");
            Scanner reader = new Scanner(fileObject);
            while(reader.hasNextLine()) {
                String data = reader.nextLine();
                if(stringContainsItemFromList(data, zeroStar)) {
                    continue;
                }
                writer.write(data + System.getProperty("line.separator"));
            }
            reader.close();
            writer.close();
            boolean successful = tempFile.renameTo(inputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static boolean stringContainsItemFromList(String inputString, ArrayList<String> items) {
        return items.stream().anyMatch(inputString::contains);
    }
}
