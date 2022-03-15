import java.util.ArrayList;

public class FilterClinvar {

    public static void main(String[] args) {
        String filenameClinvar = "data/clinvar_20220205.vcf";
        String filenameTestData = "data/test.vcf";

        VcfReader vcfReader = new VcfReader(filenameTestData, StarRating.ZEROSTAR);

//        if (vcfReader.removeStatus(filenameClinvar)) {
//            System.out.println("Successfully removed the 0 star rating lines");
//        }
        ArrayList<String> matches = vcfReader.matchWithClinvar();
//        System.out.println(matches);
        for(String i : matches) {
            System.out.println(i);
        }

    }
}
