import java.util.ArrayList;
import java.util.HashMap;

public class Variants {
    private ArrayList<String> variants = new ArrayList<>();
    private ArrayList<String> genes = new ArrayList<>();
    private HashMap<String, String> genesHpo = new HashMap<>();

    public ArrayList<String> getVariants() {
        return variants;
    }

    public void addVariant(String variants) {
        this.variants.add(variants);
    }

    public ArrayList<String> getGenes() {
        if(this.genes.size() == 0) {
            for(String variant: variants) {
                String[] splittedLine = variant.split("\t");
                String[] infoString = splittedLine[7].split(";");
                for(String i: infoString) {
                    if(i.contains("GENEINFO")) {
                        this.addGene(i.split("=")[1]);
                    }
                }
            }
        }
        return genes;
    }

    private void addGene(String genes) {
        this.genes.add(genes);
    }

    public HashMap<String, String> getGeneHpo() {
        if(this.genesHpo.size() == 0) {
            this.genesHpo = GeneToHpo.geneToHpo(this.getGenes());
        }
        return genesHpo;
    }
}
