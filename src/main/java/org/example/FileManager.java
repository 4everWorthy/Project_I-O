package org.example;

import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.io.IOException;
import java.util.logging.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManager {

    private static final Logger logger = Logger.getLogger(FileManager.class.getName());

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String directoryPath = "C:\\Users\\kjaso\\OneDrive\\Desktop\\CTACT\\JAVA_FS103\\Project_I-O\\src\\main\\java\\org\\example\\testDirectory";

        int choice;
        do {
            choice = displayMenu(scanner);

            switch (choice) {
                case 1:
                    createDirectoryIfNotExists(directoryPath); // Ensure the directory exists
                    break;
                case 2:
                    listDirectoryContents(directoryPath); // List the contents of the directory
                    break;
                case 3:
                    System.out.print("Enter the file extension to search for (e.g., .txt): ");
                    String extension = scanner.next();
                    searchFiles(directoryPath, extension); // Search for files by extension
                    break;
                case 4:
                    System.out.print("Enter the source file name (e.g., file1.txt): ");
                    String sourceFileName = scanner.next();
                    System.out.print("Enter the target file name (e.g., file1_copy.txt): ");
                    String targetFileName = scanner.next();
                    copyFile(directoryPath + "\\" + sourceFileName, directoryPath + "\\" + targetFileName); // Copy a file
                    break;
                case 5:
                    System.out.print("Enter the file name to move (e.g., file1_copy.txt): ");
                    String moveSourceFileName = scanner.next();
                    System.out.print("Enter the target file name (e.g., file1_moved.txt): ");
                    String moveTargetFileName = scanner.next();
                    moveFile(directoryPath + "\\" + moveSourceFileName, directoryPath + "\\" + moveTargetFileName); // Move a file
                    break;
                case 6:
                    System.out.print("Enter the file name to delete (e.g., file1_moved.txt): ");
                    String deleteFileName = scanner.next();
                    deleteFile(directoryPath + "\\" + deleteFileName); // Delete a file
                    break;
                case 7:
                    System.out.print("Enter the directory name to delete (e.g., newDirectory): ");
                    String deleteDirectoryName = scanner.next();
                    deleteDirectory(directoryPath + "\\" + deleteDirectoryName); // Delete a directory
                    break;
                case 0:
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    // Method to display the menu and get user input
    public static int displayMenu(Scanner scanner) {
        System.out.println("\nFile Manager Menu:");
        System.out.println("1. Create Directory");
        System.out.println("2. List Directory Contents");
        System.out.println("3. Search Files by Extension");
        System.out.println("4. Copy File");
        System.out.println("5. Move File");
        System.out.println("6. Delete File");
        System.out.println("7. Delete Directory");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    // Directory-related methods

    /**
     * Creates a directory if it doesn't exist.
     *
     * @param directoryPath the path of the directory to create
     */
    public static void createDirectoryIfNotExists(String directoryPath) {
        Path path = Paths.get(directoryPath);
        if (!Files.exists(path)) { // If the path does not exist, the code inside the if block will be executed
            try {
                Files.createDirectories(path); // If the directory doesn't exist, it will be created.
                System.out.println("Directory created at: " + directoryPath);
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Error creating directory", e);
            }
        } else {
            System.out.println("Directory already exists: " + directoryPath);
        }
    }

    /**
     * Deletes a directory if it exists and is empty.
     *
     * @param directoryPath the path of the directory to delete
     */
    public static void deleteDirectory(String directoryPath) {
        Path path = Paths.get(directoryPath);

        try {
            if (Files.isDirectory(path) && Files.list(path).count() == 0) { // Check if the directory is empty before deleting :: Files.list(path).count() == 0: Counts the number of entries in the directory.
                Files.delete(path);
                System.out.println("Directory deleted: " + directoryPath);
            } else if (Files.isDirectory(path)) {
                logger.log(Level.WARNING, "Directory is not empty: " + directoryPath);
            } else {
                logger.log(Level.WARNING, "Not a directory: " + directoryPath);
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error deleting directory", e);
        }
    }

    // File-related methods

    /**
     * Copies a file from the source path to the target path.
     * If the target file exists, it will be replaced.
     *
     * @param sourceFilePath the path of the source file
     * @param targetFilePath the path of the target file
     */
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

    /**
     * Moves a file from the source path to the target path.
     * If the target file exists, it will be replaced.
     *
     * @param sourceFilePath the path of the source file
     * @param targetFilePath the path of the target file
     */
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

    /**
     * Deletes a file at the specified path.
     *
     * @param filePath the path of the file to delete
     */
    public static void deleteFile(String filePath) {
        Path path = Paths.get(filePath);

        try {
            Files.delete(path);
            System.out.println("File deleted: " + filePath);
        } catch (NoSuchFileException e) {
            logger.log(Level.WARNING, "File not found: " + filePath, e);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error deleting file", e);
        }
    }

    /**
     * Lists the contents of the specified directory.
     *
     * @param directoryPath the path of the directory to list
     */
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

    /**
     * Searches for files in the directory by the specified extension.
     *
     * @param directoryPath the path of the directory to search
     * @param searchPattern the file extension to search for (e.g., ".txt")
     */
    public static void searchFiles(String directoryPath, String searchPattern) {
        Path path = Paths.get(directoryPath);
        List<Path> matchingFiles = new ArrayList<>();

        System.out.println("Searching in directory: " + directoryPath + " for pattern: *" + searchPattern);

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path, "*" + searchPattern)) { // Open the directory stream with the search pattern
            for (Path entry : stream) { // Loop through each entry that matches the search pattern
                matchingFiles.add(entry);
                System.out.println("Found: " + entry.getFileName()); // Print out each found file
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error searching files in directory", e);
        }

        if (matchingFiles.isEmpty()) { // If no matching files are found, notify the user
            System.out.println("No files matching the pattern were found.");
        }
    }
}
