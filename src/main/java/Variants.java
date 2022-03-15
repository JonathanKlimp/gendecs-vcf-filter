import java.util.ArrayList;

public class Variants {
    private ArrayList<String> variants = new ArrayList<>();
    private ArrayList<String> genes = new ArrayList<>();

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
