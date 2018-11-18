import dto.*;
import lombok.AllArgsConstructor;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
public class Statistics {

    private File file;
    private String content;

    public FileInfoDto getAllStatistics () {
        List<LineDto> lines = new ArrayList<LineDto>();
        List<String> linesContent = new ArrayList<>(splitText(content));

        for(String lineContent : linesContent) {
            lines.add(LineDto.builder()
                    .content(lineContent)
                    .longestWord(getMaxLengthWord(lineContent))
                    .shortestWord(getMinLengthWord(lineContent))
                    .averageWordLength(getAverageWordLegth(lineContent))
                    .length(lineContent.length())
                    .build());
        }

        return FileInfoDto.builder()
                .name(file.getName())
                .location(file.getAbsolutePath())
                .longestWord(getMaxLengthWord(content))
                .shortestWord(getMinLengthWord(content))
                .averageWordLength(getAverageWordLegth(content))
                .length(content.length())
                .lines(lines)
                .build();
    }

    private static List<String> splitLine(String line) {

        return new ArrayList<>((Arrays.asList(line.split(" |\n", -1))));
    }

    private List<String> splitText (String content) {
        return new ArrayList<>(Arrays.asList(content.split("\r\n", -1)));
    }

    private String getMinLengthWord(String line) {
        List<String> words = splitLine(line);
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

    private String getMaxLengthWord(String line) {
        List<String> words = splitLine(line);
        String maxStr = "";

        for (String word : words) {
            if (maxStr.length() < word.length()) {
                maxStr = word;
            }
        }
        return maxStr;
    }

    private int getAverageWordLegth(String line) {
        List<String> words = splitLine(line);
        int sum = 0, average;

        for (String word : words) {
            sum += word.length();
        }
        average = sum / words.size();
        return average;
    }
}
