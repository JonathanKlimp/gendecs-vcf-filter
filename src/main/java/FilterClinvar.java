import java.util.ArrayList;
import java.util.List;

public class FilterClinvar {
    public static void main(String[] args) {
        String filename = "data/clinvar_20220205.vcf";
        ArrayList<String> zeroStar = new ArrayList<>(
                List.of("no_assertion_provided",
                        "no_assertion_criteria_provided")
        );

        VcfReader vcfReader = new VcfReader();

        if (vcfReader.removeStatus(filename, zeroStar)) {
            System.out.println("Successfully removed the 0 star rating lines");
        }
    }
}
