package output;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class OutputFile extends OutputDestination {

    private String outPath = "D:\\work\\Logs";

    public OutputFile() {

    }


    public OutputFile(String destinationPath) {

        outPath = destinationPath;
    }

    @Override
    public void print(String s) {
        Path path = Paths.get(outPath + "\\log.txt");
        try(BufferedWriter writer = Files.newBufferedWriter(path))
        {
            writer.write(s);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setOutPath(String outPath) {
        this.outPath = outPath;
    }
}
