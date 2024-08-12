/**
 * Main class of the file manager application. It provides the command-line
 * interface (CLI) to interact with users. The class allows users
 * to perform various file and directory operations such as displaying directory contents,
 * copying, moving, and deleting files, creating and deleting directories, and searching
 * for files based on name or extension.
 */

package org.example;

import java.util.Scanner;
import java.io.IOException;

public class FileManager {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("File Manager Options:");
            System.out.println("1. Display Directory Contents");
            System.out.println("2. Copy File");
            System.out.println("3. Move File");
            System.out.println("4. Delete File");
            System.out.println("5. Create Directory");
            System.out.println("6. Delete Directory");
            System.out.println("7. Search Files");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter directory path to display: ");
                        String dirPath = scanner.nextLine();
                        DirectoryOperations.displayDirectoryContents(dirPath);
                        break;
                    case 2:
                        System.out.print("Enter source file path: ");
                        String sourcePath = scanner.nextLine();
                        System.out.print("Enter destination file path: ");
                        String destPath = scanner.nextLine();
                        FileOperations.copyFile(sourcePath, destPath);
                        System.out.println("File copied successfully.");
                        break;
                    case 3:
                        System.out.print("Enter source file path: ");
                        sourcePath = scanner.nextLine();
                        System.out.print("Enter destination file path: ");
                        destPath = scanner.nextLine();
                        FileOperations.moveFile(sourcePath, destPath);
                        System.out.println("File moved successfully.");
                        break;
                    case 4:
                        System.out.print("Enter file path to delete: ");
                        String filePath = scanner.nextLine();
                        FileOperations.deleteFile(filePath);
                        System.out.println("File deleted successfully.");
                        break;
                    case 5:
                        System.out.print("Enter directory path to create: ");
                        dirPath = scanner.nextLine();
                        DirectoryOperations.createDirectory(dirPath);
                        System.out.println("Directory created successfully.");
                        break;
                    case 6:
                        System.out.print("Enter directory path to delete: ");
                        dirPath = scanner.nextLine();
                        DirectoryOperations.deleteDirectory(dirPath);
                        System.out.println("Directory deleted successfully.");
                        break;
                    case 7:
                        System.out.print("Enter directory path to search: ");
                        String searchDirPath = scanner.nextLine();
                        System.out.print("Enter file name or extension to search: ");
                        String searchQuery = scanner.nextLine();
                        SearchOperations.searchFiles(searchDirPath, searchQuery)
                                .forEach(path -> System.out.println("Found: " + path.getFileName()));
                        break;
                    case 8:
                        System.out.println("Exiting...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid option. Try again.");
                }
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
}
