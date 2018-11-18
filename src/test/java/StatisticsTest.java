import dto.FileInfoDto;
import dto.LineDto;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class StatisticsTest {
    @Test
    public void getAllStatistics() {
        File file = new File("C://", "test.txt");
        String content = "ab d c\r\nc a y";
        Statistics statistics = new Statistics(file, content);

        List<LineDto> expectedLines = new ArrayList<>();
        expectedLines.add(LineDto.builder()
                .content("ab d c")
                .averageWordLength(1)
                .length(6)
                .longestWord("ab")
                .shortestWord("d")
                .build());
        expectedLines.add(LineDto.builder()
                .content("c a y")
                .averageWordLength(1)
                .length(5)
                .longestWord("c")
                .shortestWord("c")
                .build());

        FileInfoDto expected = FileInfoDto.builder()
                .name("test.txt")
                .averageWordLength(1)
                .length(13)
                .longestWord("ab")
                .shortestWord("d")
                .location("C:\\test.txt")
                .lines(expectedLines)
                .build();

        FileInfoDto actual = statistics.getAllStatistics();

        Assert.assertEquals(expected, actual);
    }
}
