package exceptions;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

public class FileReader {
    public static Optional<Stream<String>> getFromFile(String path) {
        try {
            return Optional.of(Files.lines(Paths.get(path)));
        } catch (Throwable t) {
            return Optional.empty();
        }
    }

    public static void main(String[] args) {
        Stream.of("a.txt", "b.txt", "c.txt")
                .map(FileReader::getFromFile)
                .peek(o -> {
                    if (!o.isPresent()) {
                        System.out.println("Ooops, that file was missing");
                    }
                })
                .filter(Optional::isPresent)
                .flatMap(Optional::get)
                .forEach(System.out::println);
    }
}
