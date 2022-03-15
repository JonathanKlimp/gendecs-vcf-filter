public class FilterClinvar {

    public static void main(String[] args) {
        String filenameClinvar = "data/clinvar_20220205.vcf";
        String filenameTestData = "data/test.vcf";
        StarRating starRating = StarRating.ONESTAR;

        VcfParser vcfParser = new VcfParser(filenameTestData, starRating);

        if (vcfParser.removeStatus(filenameClinvar)) {
            System.out.println("Successfully removed " + starRating + " and below from " + filenameClinvar);
        }
        Variants variants = vcfParser.matchWithClinvar();

        System.out.println(variants.getGeneHpo());
    }
}
