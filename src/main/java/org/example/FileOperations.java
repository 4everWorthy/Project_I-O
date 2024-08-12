package org.example;

import java.io.IOException;
import java.nio.file.*;

import java.io.IOException;
import java.nio.file.*;

public class FileOperations {

    public static void copyFile(String sourcePath, String destinationPath) throws IOException {
        Path source = Paths.get(sourcePath);
        Path destination = Paths.get(destinationPath);

        // Check if the destination is a directory, and if so, append the file name
        if (Files.isDirectory(destination)) {
            destination = destination.resolve(source.getFileName());
        }

        Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("File copied successfully.");
    }

    public static void moveFile(String sourcePath, String destinationPath) throws IOException {
        Path source = Paths.get(sourcePath);
        Path destination = Paths.get(destinationPath);

        // Check if the destination is a directory, and if so, append the file name
        if (Files.isDirectory(destination)) {
            destination = destination.resolve(source.getFileName());
        }

        Files.move(source, destination, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("File moved successfully.");
    }

    public static void deleteFile(String filePath) throws IOException {
        Files.delete(Paths.get(filePath));
    }
}
