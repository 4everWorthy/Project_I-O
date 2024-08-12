/**
 * This class contains methods to handle file-related operations such as copying, moving,
 * and deleting files. It ensures that files are managed efficiently and includes a check
 * to determine whether the destination path is a directory, allowing the file to be copied
 * or moved into the directory with its original name if needed.
 * Methods:
 * - copyFile: Copies a file from the source path to the destination path.
 * - moveFile: Moves a file from the source path to the destination path.
 * - deleteFile: Deletes a file at the specified path.
 */

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
