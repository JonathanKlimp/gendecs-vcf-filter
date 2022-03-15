import java.util.ArrayList;

public class Variants {
    ArrayList<String> variants;
    ArrayList<String> genes;

    public ArrayList<String> getVariants() {
        return variants;
    }

    public void addVariant(String variants) {
        this.variants.add(variants);
    }

    public ArrayList<String> getGenes() {
        return genes;
    }

    public void addGene(String genes) {
        this.genes.add(genes);
    }
}
