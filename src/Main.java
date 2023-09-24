import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    enum Choices {
        SEARCH,
        PRINT_ALL,
        INVALID,
        EXIT
    }
    public static void main(String[] args) {
        ArrayList<String> lines;
        if (args.length == 2 && args[0].equals("--data")) {
            String fileName = args[1];
            lines = readLinesFromFile(fileName);

        } else {
            System.out.println("Usage: java Main --data <filename>");
            return;
        }

        Scanner scanner = new Scanner(System.in);


        while (true) {
            Choices choice = choose(scanner);
            System.out.println();
            switch (choice) {
                case EXIT -> {
                    System.out.println("Bye!");
                    return;
                }

                case SEARCH -> {
                    search(scanner, lines);
                }

                case PRINT_ALL -> {
                    printALl(lines);
                }

                case INVALID -> {
                    System.out.println("Incorrect option! Try again.");
                    System.out.println();
                }
            }

        }

    }

    private static ArrayList<String> readLinesFromFile(String fileName) {
        ArrayList<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Exclude any processing related to counting lines or totals
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        return lines;
    }



    public static void printALl(ArrayList<String> lines) {
        System.out.println("=== List of people ===");
        for (String line : lines ) {
            System.out.println(line);
        }
        System.out.println();
    }



    public static Choices choose(Scanner scanner) {
        System.out.println("""
                === Menu ===
                1. Find a person
                2. Print all people
                0. Exit """);

        int choice = scanner.nextInt();
        switch (choice) {
            case 0 -> {
                return Choices.EXIT;
            }

            case 1 -> {
                return Choices.SEARCH;
            }

            case 2 -> {
                return Choices.PRINT_ALL;
            }
        }

        return Choices.INVALID; // invalid

    }

    @Deprecated
    public static void query(Scanner scanner, ArrayList<String> lines) {

        System.out.println("Enter the number of search queries:");
        int qNum;
        while (true) {
            try {
                qNum = Integer.parseInt(scanner.nextLine());
                break; // Exit the loop if a valid integer is entered
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for the number of search queries. Please enter an integer.");
            }
        }

        while (qNum > 0) {
            System.out.println();
            System.out.println("Enter data to search people:");
            String word = scanner.nextLine();
            ArrayList<String> info = search(lines, word);
            if (info.isEmpty()) {
                System.out.println("No matching people found.\n");
            } else {
                System.out.println("Found people:");
                for (String details : info) {
                    System.out.println(details);
                }
            }
            qNum--;
        }
    }

    public static void search(Scanner scanner, ArrayList<String> lines) {
            System.out.println("Enter a name or email to search all suitable people.");
            scanner.nextLine();
            String word = scanner.nextLine();
            ArrayList<String> info = search(lines, word);
            if (info.isEmpty()) {
                System.out.println("No matching people found.\n");
            } else {
                for (String details : info) {
                    System.out.println(details);
                }
            }
        System.out.println();
    }


    public static ArrayList<String> search(ArrayList<String> lines, String word) {

        ArrayList<String> foundInfo = new ArrayList<>();
        for (String line : lines) {
            String[] words = line.split(" ");
            for (String s : words) {
                if ((s.toLowerCase()).contains(word.toLowerCase())) {
                    foundInfo.add(line);
                    break; // Break the inner loop when a match is found
                }
            }
        }
        return foundInfo;
    }

    @SuppressWarnings(value = "Warning:(173, 37) Method 'inputData(java.util.Scanner)' is never used")
    public static ArrayList<String> inputData(Scanner scanner) {
        System.out.println("Enter the number of people:");
        int noOfData = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter all people:");
        ArrayList<String> data = new ArrayList<>(noOfData);
        for (int i = 1; i <= noOfData; i++) {
            String personData = scanner.nextLine();
            data.add(personData);
        }
        System.out.println();
        return data;
    }
}
