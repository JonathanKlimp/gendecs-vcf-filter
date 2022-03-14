import java.util.ArrayList;
import java.util.List;

public class FilterClinvar {

    public static void main(String[] args) {
        String filenameClinvar = "data/clinvar_20220205.vcf";
        String filenameTestData = "data/test.vcf";
        ArrayList<String> zeroStar = new ArrayList<>(
                List.of("no_assertion_provided",
                        "no_assertion_criteria_provided")
        );
        ArrayList<String> oneStar = new ArrayList<>(
                List.of("criteria_provided,single_submitter",
                        "criteria_provided,conflicting_interpretations")
        );

        VcfReader vcfReader = new VcfReader(filenameTestData);

//        if (vcfReader.removeStatus(filenameClinvar, zeroStar)) {
//            System.out.println("Successfully removed the 0 star rating lines");
//        }
        ArrayList<String> matches = vcfReader.matchWithClinvar();
        System.out.println(matches);

    }
}
