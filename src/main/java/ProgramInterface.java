import lombok.NoArgsConstructor;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;

import java.io.File;

@NoArgsConstructor
public class ProgramInterface {

    private TextIO textIO = TextIoFactory.getTextIO();

    public void Start() {
        String path = textIO.newStringInputReader()
                .withDefaultValue("C://")
                .read("Path to file");

        File file = new File(path);
        FileReader fileReader = new FileReader();

        Statistics statistics = new Statistics(file, fileReader.ReadAll(file));

        DbManager dbManager = new DbManager();
        dbManager.AddFile(statistics.getAllStatistics());


        textIO.getTextTerminal().printf(statistics.getAllStatistics().toString());

    }
}
