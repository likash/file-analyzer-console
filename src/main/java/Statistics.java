import java.util.ArrayList;
import java.util.Arrays;

public class Statistics {

    private static ArrayList<String> splitLine(String line) {
        ArrayList<String> words = new ArrayList<>();
        words.addAll(Arrays.asList(line.split(" ")));

        return words;
    }

    public static int getLength(String line) {
        return line.length();
    }

    public static String getMinLengthWord(String line) {
        ArrayList<String> words = splitLine(line);
        String minStr = "";
        int min = Integer.MAX_VALUE;
        for (String word : words) {
            if (min > word.length()) {
                minStr = word;
                min = word.length();
            }
        }
        return minStr;
    }

    public static String getMaxLengthWord(String line) {
        ArrayList<String> words = splitLine(line);
        String maxStr = "";

        for (String word : words) {
            if (maxStr.length() < word.length()) {
                maxStr = word;
            }
        }
        return maxStr;
    }

    public static float getAvarageWordLegth(String line) {
        ArrayList<String> words = splitLine(line);
        float sum = 0, avarage;

        for (String word : words) {
            sum += word.length();
        }
        avarage = sum / words.size();
        return avarage;
    }
}
