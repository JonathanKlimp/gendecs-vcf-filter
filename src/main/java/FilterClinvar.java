import java.util.ArrayList;
import java.util.List;

public class FilterClinvar {

    public static void main(String[] args) {
        String filenameClinvar = "data/clinvar_20220205.vcf";
        String filenameTestData = "data/lp.vcf";
        ArrayList<String> zeroStar = new ArrayList<>(
                List.of("no_assertion_provided",
                        "no_assertion_criteria_provided")
        );

        VcfReader vcfReader = new VcfReader(filenameTestData);

//        if (vcfReader.removeStatus(filenameClinvar, zeroStar)) {
//            System.out.println("Successfully removed the 0 star rating lines");
//        }
        vcfReader.matchWithClinvar();
    }
}
