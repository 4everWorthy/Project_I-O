/**
 * This class provides functionality for searching files within a specified directory based
 * on a file name or extension. The search results are returned as a stream of paths that
 * match the search query, allowing the user to quickly locate files that meet their criteria.
 * Methods:
 * - searchFiles: Searches for files within the specified directory that match the given query (name or extension).
 */


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
