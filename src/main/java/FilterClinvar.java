import java.util.ArrayList;
import java.util.HashMap;

public class FilterClinvar {

    public static void main(String[] args) {
        String filenameClinvar = "data/clinvar_20220205.vcf";
        String filenameTestData = "data/test.vcf";

        VcfParser vcfParser = new VcfParser(filenameTestData, StarRating.ONESTAR);

        if (vcfParser.removeStatus(filenameClinvar)) {
            System.out.println("Successfully removed the 0 and 1 star rating lines");
        }
        Variants variants = vcfParser.matchWithClinvar();

        System.out.println(variants.getGeneHpo());
    }
}
