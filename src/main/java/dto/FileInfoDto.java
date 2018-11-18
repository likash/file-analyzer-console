package dto;

import lombok.*;

import java.util.List;

@Builder
@Data
public class FileInfoDto {
    private String name;
    private String location;
    private String longestWord;
    private String shortestWord;
    private int averageWordLength;
    private int length;
    private List<LineDto> lines;
}
