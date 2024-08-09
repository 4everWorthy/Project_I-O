package org.example;

import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.io.IOException;
import java.util.logging.*;

public class FileManager {

    private static final Logger logger = Logger.getLogger(FileManager.class.getName());

    public static void main(String[] args) {
        String directoryPath = "C:\\Users\\kjaso\\OneDrive\\Desktop\\CTACT\\JAVA_FS103\\Assignment_I-O\\src\\main\\java\\org\\example\\testDirectory";
        createDirectoryIfNotExists(directoryPath); // Ensure the directory exists
        listDirectoryContents(directoryPath); // List the contents of the directory

        // Test file operations
        String sourceFilePath = directoryPath + "\\file1.txt"; // Source file path
        String targetFilePath = directoryPath + "\\file1_copy.txt"; // Target file path for copy

        // Check if the source file exists before copying
        if (Files.exists(Paths.get(sourceFilePath))) {
            copyFile(sourceFilePath, targetFilePath); // Test copying a file

            // Check if the target file was successfully copied before moving
            if (Files.exists(Paths.get(targetFilePath))) {
                moveFile(targetFilePath, directoryPath + "\\file1_moved.txt"); // Test moving a file

                // Check if the file was successfully moved before deleting
                if (Files.exists(Paths.get(directoryPath + "\\file1_moved.txt"))) {
                    deleteFile(directoryPath + "\\file1_moved.txt"); // Test deleting a file
                } else {
                    logger.log(Level.WARNING, "Moved file does not exist: " + directoryPath + "\\file1_moved.txt");
                }
            } else {
                logger.log(Level.WARNING, "Target file for moving does not exist: " + targetFilePath);
            }
        } else {
            logger.log(Level.WARNING, "Source file for copying does not exist: " + sourceFilePath);
        }
    }

    // Method to create the directory if it doesn't exist
    public static void createDirectoryIfNotExists(String directoryPath) {
        Path path = Paths.get(directoryPath);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
                System.out.println("Directory created at: " + directoryPath);
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Error creating directory", e);
            }
        }
    }

    // Method to list contents of a specified directory
    public static void listDirectoryContents(String directoryPath) {
        Path path = Paths.get(directoryPath); // Initialize the path to the directory

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) { // Open the directory stream
            for (Path entry : stream) { // Loop through each entry in the directory
                BasicFileAttributes attrs = Files.readAttributes(entry, BasicFileAttributes.class); // Read the file attributes
                System.out.printf("File: %s, Size: %d bytes, Last Modified: %s%n",
                        entry.getFileName(), attrs.size(), attrs.lastModifiedTime());
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading directory contents", e);
        }
    }

    // Method to copy a file from source to target
    public static void copyFile(String sourceFilePath, String targetFilePath) {
        Path sourcePath = Paths.get(sourceFilePath);
        Path targetPath = Paths.get(targetFilePath);

        try {
            Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File copied from " + sourceFilePath + " to " + targetFilePath);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error copying file", e);
        }
    }

    // Method to move a file from source to target
    public static void moveFile(String sourceFilePath, String targetFilePath) {
        Path sourcePath = Paths.get(sourceFilePath);
        Path targetPath = Paths.get(targetFilePath);

        try {
            Files.move(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File moved from " + sourceFilePath + " to " + targetFilePath);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error moving file", e);
        }
    }

    // Method to delete a file at the specified path
    public static void deleteFile(String filePath) {
        Path path = Paths.get(filePath);

        try {
            Files.delete(path);
            System.out.println("File deleted: " + filePath);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error deleting file", e);
        }
    }
}
