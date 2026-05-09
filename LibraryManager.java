import java.util.ArrayList;
import java.util.List;

public class LibraryManager {
    private List<Book> books;
    private static List<User> users = new ArrayList<>();

    static {
        // Add a default user
        users.add(new User("admin", "admin123"));
    }

    public LibraryManager() {
        this.books = new ArrayList<>();
        // Pre-add some books for testing
        books.add(new Book("101", "Java Programming", "John Smith", "Education", 5));
        books.add(new Book("102", "Clean Code", "Robert Martin", "Software", 3));
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public Book searchBookById(String id) {
        for (Book book : books) {
            if (book.getId().equalsIgnoreCase(id)) {
                return book;
            }
        }
        return null;
    }

    public List<Book> searchBookByName(String name) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    public boolean deleteBook(String id) {
        Book book = searchBookById(id);
        if (book != null) {
            books.remove(book);
            return true;
        }
        return false;
    }

    public boolean issueBook(String id) {
        Book book = searchBookById(id);
        if (book != null && book.getQuantity() > 0) {
            book.issueBook();
            return true;
        }
        return false;
    }

    public boolean returnBook(String id) {
        Book book = searchBookById(id);
        if (book != null) {
            book.returnBook();
            return true;
        }
        return false;
    }

    // User Management
    public static void addUser(User user) {
        users.add(user);
    }

    public static boolean authenticate(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
