package dto;

import lombok.*;

@Builder
@Data
public class LineDto {
    private String content;
    private String longestWord;
    private String shortestWord;
    private int avarageWordLength;
    private int length;
}
