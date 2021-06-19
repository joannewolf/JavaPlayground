package twr;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BreakThis {
    public static void main(String[] args) /* throws SomeImportantException */ {
//        BufferedReader bufferedReader = null;
//        BufferedWriter output = null;
        try (
                BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("text.txt"));
                BufferedWriter output = Files.newBufferedWriter(Paths.get("out.txt"));

                ) {

            // body of try... business logic
            // throw new SomeImportantException...
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
//        finally {
//            bufferedReader
//        }
//        finally {
//            if (bufferedReader != null) {
//                try {
//                    bufferedReader.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (output != null) {
//                try {
//                    output.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
    }
}
