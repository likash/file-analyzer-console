import lombok.NoArgsConstructor;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class ProgramInterface {

    private TextIO textIO = TextIoFactory.getTextIO();

    public void Start() {
        FileReader fileReader = new FileReader();
        DbManager dbManager = new DbManager();

        while(true) {
            List<File> files = new ArrayList<>();

            String path = textIO.newStringInputReader()
                    .withDefaultValue("C://")
                    .read("Path to file");

            if (!Paths.get(path).toFile().exists()) {
                textIO.getTextTerminal().println("Path not exists. Repeat, please");
            }
            else {
                files = fileReader.getTxtFilesByDirectory(path);
                for (File file : files) {
                    textIO.getTextTerminal().println(file.getName());
                }
            }

            for (File file : files) {
                textIO.getTextTerminal().println("Calculating statistics...");
                Statistics statistics = new Statistics(file, fileReader.ReadAll(file));

                textIO.getTextTerminal().println("Adding records to db...");
                dbManager.AddFile(statistics.getAllStatistics());
            }

            String answ = textIO.newStringInputReader()
                    .read("Do you want to continue? (yes/another answer)");
            if (answ.equals("yes")) {
                continue;
            }
            else {
                return;
            }
        }
    }
}
