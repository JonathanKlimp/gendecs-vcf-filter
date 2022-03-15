import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class GeneToHpo {
    static ArrayList<String> geneSymbols = new ArrayList<>();

    static HashMap<String, String> geneHpo = new HashMap<>();

    public static HashMap<String, String> geneToHpo(ArrayList<String> genesRaw) {
        getGeneSymbol(genesRaw);
        getHpo(geneSymbols);
        return geneHpo;
    }

    private static void getGeneSymbol(ArrayList<String> genesRaw) {
        for(String gene : genesRaw) {
            String[] geneSplit = gene.split(":");
            if(geneSplit.length > 2) {
//                geneSymbols.add(geneSplit[0]);
            } else {
                geneSymbols.add(geneSplit[0]);
            }
        }
    }

    private static void getHpo(ArrayList<String> geneSymbols) {
        try {
            File file = new File("data/genes_to_phenotype.txt");
            Scanner reader = new Scanner(file);
            while(reader.hasNextLine()) {
                String currentLine = reader.nextLine();
                for(String geneSymbol : geneSymbols) {
                    if(currentLine.contains(geneSymbol)) {
                        String[] lineSplit = reader.nextLine().split("\t");
                        String gene = lineSplit[1];
                        String hpoId = lineSplit[2];
                        String hpoTerm = lineSplit[3];
                        geneHpo.put(gene, hpoTerm);
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
