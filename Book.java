public class Book {
    private String id;
    private String name;
    private String author;
    private String category;
    private int quantity;

    public Book(String id, String name, String author, String category, int quantity) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.category = category;
        this.quantity = quantity;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public void issueBook() {
        if (quantity > 0) {
            quantity--;
        }
    }

    public void returnBook() {
        quantity++;
    }
}
