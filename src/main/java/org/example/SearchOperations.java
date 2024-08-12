package org.example;

import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

public class SearchOperations {

    public static Stream<Path> searchFiles(String directoryPath, String searchQuery) throws IOException {
        return Files.walk(Paths.get(directoryPath))
                .filter(path -> path.getFileName().toString().contains(searchQuery));
    }
}
