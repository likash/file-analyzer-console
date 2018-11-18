package dto;

import lombok.*;

@Builder
@Data
public class LineDto {
    private String content;
    private String longestWord;
    private String shortestWord;
    private int averageWordLength;
    private int length;
}
