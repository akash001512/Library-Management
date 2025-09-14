import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private double price;
    private String fileSize; // For eBook
    private int pageCount;   // For printed book

    private static Scanner scanner = new Scanner(System.in);

    // Set book details
    public void setBookDetails() {
        System.out.print("Enter Title: ");
        title = scanner.nextLine();

        System.out.print("Enter Author: ");
        author = scanner.nextLine();

        System.out.print("Enter Price: ");
        price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
    }

    // Set eBook details
    public void setEBookDetails() {
        System.out.println("Enter EBook Details:");
        setBookDetails();
        System.out.print("Enter File Size: ");
        fileSize = scanner.nextLine();
    }

    // Set printed book details
    public void setPrintedBookDetails() {
        System.out.println("Enter Printed Book Details:");
        setBookDetails();
        System.out.print("Enter Page Count: ");
        pageCount = scanner.nextInt();
        scanner.nextLine(); // Consume newline
    }

    // Display details
    public void displayEBookDetails() {
        System.out.println("EBook Details:");
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Price: $" + price);
        System.out.println("File Size: " + fileSize);
    }

    public void displayPrintedBookDetails() {
        System.out.println("Printed Book Details:");
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Price: $" + price);
        System.out.println("Page Count: " + pageCount);
    }

    public String getTitle() {
        return title;
    }
}

public class LibraryManagement{
    public static void main(String[] args) {
        String[] options = {
            "1. Add Book",
            "2. Display Books",
            "3. Search Book by Title",
            "4. Exit"
        };

        Scanner scanner = new Scanner(System.in);
        Book[] books = new Book[10];
        int bookCount = 0;

        while (true) {
            System.out.println("\nLibrary Menu:");
            for (String option : options) {
                System.out.println(option);
            }
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    if (bookCount < books.length) {
                        System.out.print("Is this an (1) EBook or (2) Printed Book or (3) Both Books ? Enter 1 or 2 or 3: ");
                        int type = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        Book newBook = new Book();
                        if (type == 1) {
                            newBook.setEBookDetails();
                        } else if (type == 2) {
                            newBook.setPrintedBookDetails();
                        } else if (type == 3) {
                            newBook.setEBookDetails();
                            System.out.println();
                            newBook.setPrintedBookDetails();
                        } else {
                            System.out.println("Invalid type! Returning to main menu.");
                            continue;
                        }
                        books[bookCount++] = newBook;
                        System.out.println("Book added successfully!");
                    } else {
                        System.out.println("Library is full. Cannot add more books.");
                    }
                    break;

                case 2:
                    if (bookCount > 0) {
                        System.out.println("Displaying all books:");
                        for (int i = 0; i < bookCount; i++) {
                            books[i].displayEBookDetails();
                            System.out.println();
                            books[i].displayPrintedBookDetails();
                            System.out.println();
                        }
                    } else {
                        System.out.println("No books to display.");
                    }
                    break;

                case 3:
                    System.out.print("Enter the title to search: ");
                    String titleToSearch = scanner.nextLine();
                    boolean found = false;

                    for (int i = 0; i < bookCount; i++) {
                        if (books[i].getTitle().equalsIgnoreCase(titleToSearch)) {
                            System.out.println("Book found:");
                            books[i].displayEBookDetails();
                            books[i].displayPrintedBookDetails();
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("No book found with the title: " + titleToSearch);
                    }
                    break;

                case 4:
                    System.out.println("Exiting from Library...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
