import java.util.Arrays;

public class SentimentAnalyzer {
    public static int[] detectProsAndCons(String review, String[][]
            featureSet, String[] posOpinionWords,
                                          String[] negOpinionWords) {
        int[] featureOpinions = new int[featureSet.length];
        int i=0;
        for(String[] featuresList: featureSet){
            label:
            for(String feature: featuresList){
                int opinion = getOpinionOnFeature(review,feature,posOpinionWords,negOpinionWords);
                if(opinion!=0){
                    featureOpinions[i]=opinion;
                    break label;
                }
            }
            i++;
        }
        return featureOpinions;
    }

    private static int getOpinionOnFeature(String review, String feature,
                                           String[] posOpinionWords, String[] negOpinionWords) {

        int opinion = checkForWasPhrasePattern(review,feature,posOpinionWords,negOpinionWords);

        if(opinion!=0)
            return opinion;

        opinion = checkForOpinionFirstPattern(review,feature,posOpinionWords,negOpinionWords);
        return opinion;

    }
    private static int checkForWasPhrasePattern(String review, String
            feature, String[] posOpinionWords, String[] negOpinionWords) {
        int opinion = 0;
        String pattern = feature+" was ";

        int position = review.indexOf(pattern);

        if(position == -1)return opinion;

        position = position + pattern.length();

        StringBuffer buffer = new StringBuffer();

        while(Character.isLetter(review.charAt(position))){
            buffer.append(review.charAt(position));
            position++;
        }
        String word = buffer.toString();
        if(Arrays.asList(posOpinionWords).contains(word))
            opinion = 1;
        else if(Arrays.asList(negOpinionWords).contains(word))
            opinion = -1;
        return opinion;
    }
    private static int checkForOpinionFirstPattern(String review, String
            feature, String[] posOpinionWords,
                                                   String[] negOpinionWords) {
        review = review.replaceAll("[^a-zA-Z]", " ");
        String[] sentences = review.split("\\s+");

        int i, opinion = 0;
        for(i = 1; i < sentences.length ;i++){
            if(sentences[i].equalsIgnoreCase(feature)){
                if(searchInArray(sentences[i-1], posOpinionWords)){
                    return 1;
                }
            }
        }
        for(i = 1; i < sentences.length ;i++){
            if(sentences[i].equalsIgnoreCase(feature)){
                if(searchInArray(sentences[i-1], negOpinionWords)){
                    return -1;
                }
            }
        }
        return opinion;
    }
    private  static boolean searchInArray(String opinion, String[] Words){
        if(Arrays.asList(Words).contains(opinion.toLowerCase()))
            return true;
        else
            return false;
    }
    public static void main(String[] args) {
        String review = "Haven't been here in years! Fantastic service and the food was delicious! Definetly will be a frequent flyer! Francisco was very attentive";

        String[][] featureSet = {
                { "ambiance", "ambience", "atmosphere", "decor" },
                { "dessert", "ice cream", "desert" },
                { "food" },
                { "soup" },{ "service", "management", "waiter", "waitress",
                "bartender", "staff", "server" } };
        String[] posOpinionWords = { "good", "fantastic", "friendly",
                "great", "excellent", "amazing", "awesome",
                "delicious" };
        String[] negOpinionWords = { "slow", "bad", "horrible",
                "awful", "unprofessional", "poor" };
        int[] featureOpinions = detectProsAndCons(review, featureSet,
                posOpinionWords, negOpinionWords);
        System.out.println("Opinions on Features: " +
                Arrays.toString(featureOpinions));
    }
}