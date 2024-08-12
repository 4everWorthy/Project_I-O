package org.example;

import java.io.File;  // Importing the File class
import java.io.IOException;
import java.nio.file.*;
import java.util.Comparator;

public class DirectoryOperations {

    /**
     * Displays the contents of the specified directory, including file names, sizes, and last modified dates.
     *
     * @param directoryPath The path of the directory to display.
     * @throws IOException If an I/O error occurs.
     */
    public static void displayDirectoryContents(String directoryPath) throws IOException {
        Files.list(Paths.get(directoryPath)).forEach(path -> {
            File file = path.toFile();
            System.out.printf("%-20s %10d bytes %20s%n",
                    file.getName(), file.length(), file.lastModified());
        });
    }

    /**
     * Creates a new directory at the specified path.
     *
     * @param directoryPath The path where the directory should be created.
     * @throws IOException If an I/O error occurs.
     */
    public static void createDirectory(String directoryPath) throws IOException {
        Files.createDirectory(Paths.get(directoryPath));
    }

    /**
     * Deletes a directory at the specified path. If the directory contains files,
     * they will be deleted as well.
     *
     * @param directoryPath The path of the directory to delete.
     * @throws IOException If an I/O error occurs.
     */
    public static void deleteDirectory(String directoryPath) throws IOException {
        Files.walk(Paths.get(directoryPath))
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
    }
}

