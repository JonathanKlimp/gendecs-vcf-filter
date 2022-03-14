import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Pattern;

public class VcfReader {
    File vcfFile;
    public VcfReader(String filename) {
        vcfFile = new File(filename);
    }

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

    public void matchWithClinvar() {
        Map<String, Pattern> stringsToFind = new HashMap<>();
        try {
            Scanner reader = new Scanner(vcfFile);
            while(reader.hasNextLine()) {
                String data = reader.nextLine();
                if (data.startsWith("#")) {
                    continue;
                }
                String[] splittedData = data.split("\t");
                String chromosome = splittedData[0];
                String position = splittedData[1];
                String ref = splittedData[3];
                String alt = splittedData[4];
                Pattern pattern = Pattern.compile(
                        String.format("%s\\t%s\\t[0-9]+\\t%s\\t%s.+",
                                chromosome, position, ref, alt));
                stringsToFind.put(data, pattern);

            }
            ArrayList<String> foundMatches = getMatchesClinvar(stringsToFind);
            filterMatches(foundMatches);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<String> getMatchesClinvar(Map<String, Pattern> stringsToFind) throws IOException {
        File file = new File("data/clinvar_20220205.vcf");
        Scanner reader = new Scanner(file);
        ArrayList<String> foundMatches = new ArrayList<>();
        while(reader.hasNextLine()) {
            String currentLine = reader.nextLine();
            for(Pattern stringToFind: stringsToFind.values()) {
                if (currentLine.matches(String.valueOf(stringToFind))) {
                    foundMatches.add(currentLine);
                }
            }
        }
        return foundMatches;
    }

    private static void filterMatches(ArrayList<String> matchedLines) {
        ArrayList<String> matchedVariants = new ArrayList<>();
        ArrayList<String> clinSig = new ArrayList<>(
                List.of("likely_pathogenic",
                        "pathogenic", "pathogenic/likely_pathogenic")
        );
        for(String variant: matchedLines) {
            String[] splittedLine = variant.split("\t");
            String[] infoString = splittedLine[7].split(";");

            System.out.println(infoString[5]);
            for(String i: infoString) {
                if(i.contains("CLNSIG")) {
                    if (clinSig.contains(i.split("=")[1])) {
                        matchedVariants.add(variant);
                    }
                }
            }
        }
        System.out.println(matchedVariants);
    }
}
