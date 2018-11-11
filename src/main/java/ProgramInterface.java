import lombok.NoArgsConstructor;
import org.beryx.textio.TextIO;
import java.util.ArrayList;
import org.beryx.textio.TextIoFactory;

@NoArgsConstructor
public class ProgramInterface {

    private TextIO textIO = TextIoFactory.getTextIO();

    public void Start() {
        String path = textIO.newStringInputReader()
                .withDefaultValue("C://")
                .read("Path to file");

        FileReader fileReader = new FileReader(path);
        ArrayList<String> lines = fileReader.ReadByLine();

        for (String line : lines) {
            textIO.getTextTerminal().printf("Line: " + line + "\n Line length: " +
                    Statistics.getLength(line) + "\n Avarage word length: " + Statistics.getAvarageWordLegth(line) +
                    "\n MaxLengthWord: " + Statistics.getMaxLengthWord(line) +
                    "\n MinLengthWord: " + Statistics.getMinLengthWord(line));
        }
    }
}
