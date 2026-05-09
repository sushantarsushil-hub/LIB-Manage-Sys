import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DashboardFrame extends JFrame {
    private LibraryManager manager;
    private JTable bookTable;
    private DefaultTableModel tableModel;

    public DashboardFrame() {
        manager = new LibraryManager(); // In a real app, this might be shared
        
        setTitle("Library Management System - Dashboard");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Top Panel - Title
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(41, 128, 185));
        JLabel titleLabel = new JLabel("Library Dashboard");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        topPanel.add(titleLabel);
        add(topPanel, BorderLayout.NORTH);

        // Left Panel - Buttons
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new GridLayout(7, 1, 10, 10));
        sidePanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        sidePanel.setBackground(new Color(236, 240, 241));

        JButton btnAdd = createStyledButton("Add Book");
        JButton btnView = createStyledButton("View Books");
        JButton btnSearch = createStyledButton("Search Book");
        JButton btnIssue = createStyledButton("Issue Book");
        JButton btnReturn = createStyledButton("Return Book");
        JButton btnDelete = createStyledButton("Delete Book");
        JButton btnLogout = createStyledButton("Logout");
        btnLogout.setBackground(new Color(231, 76, 60));
        btnLogout.setForeground(Color.WHITE);

        sidePanel.add(btnAdd);
        sidePanel.add(btnView);
        sidePanel.add(btnSearch);
        sidePanel.add(btnIssue);
        sidePanel.add(btnReturn);
        sidePanel.add(btnDelete);
        sidePanel.add(btnLogout);
        add(sidePanel, BorderLayout.WEST);

        // Center Panel - Content Area (Table)
        String[] columns = {"ID", "Name", "Author", "Category", "Qty"};
        tableModel = new DefaultTableModel(columns, 0);
        bookTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(bookTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Book Records"));
        add(scrollPane, BorderLayout.CENTER);

        // Button Actions
        btnAdd.addActionListener(e -> showAddDialog());
        btnView.addActionListener(e -> refreshTable(manager.getAllBooks()));
        btnSearch.addActionListener(e -> showSearchDialog());
        btnIssue.addActionListener(e -> showIssueDialog());
        btnReturn.addActionListener(e -> showReturnDialog());
        btnDelete.addActionListener(e -> showDeleteDialog());
        btnLogout.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });

        refreshTable(manager.getAllBooks());
    }

    private JButton createStyledButton(String text) {
        JButton btn = new JButton(text);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Arial", Font.PLAIN, 14));
        return btn;
    }

    private void refreshTable(List<Book> books) {
        tableModel.setRowCount(0);
        for (Book b : books) {
            tableModel.addRow(new Object[]{b.getId(), b.getName(), b.getAuthor(), b.getCategory(), b.getQuantity()});
        }
    }

    private void showAddDialog() {
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField authorField = new JTextField();
        JTextField categoryField = new JTextField();
        JTextField qtyField = new JTextField();

        Object[] message = {
            "Book ID:", idField,
            "Name:", nameField,
            "Author:", authorField,
            "Category:", categoryField,
            "Quantity:", qtyField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add New Book", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                String id = idField.getText();
                String name = nameField.getText();
                String author = authorField.getText();
                String category = categoryField.getText();
                int qty = Integer.parseInt(qtyField.getText());

                if (id.isEmpty() || name.isEmpty()) throw new Exception("ID and Name are required");
                
                manager.addBook(new Book(id, name, author, category, qty));
                refreshTable(manager.getAllBooks());
                JOptionPane.showMessageDialog(this, "Book Added Successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }
    }

    private void showSearchDialog() {
        String query = JOptionPane.showInputDialog(this, "Enter Book ID or Name to Search:");
        if (query != null && !query.isEmpty()) {
            Book b = manager.searchBookById(query);
            if (b != null) {
                refreshTable(List.of(b));
            } else {
                List<Book> results = manager.searchBookByName(query);
                if (!results.isEmpty()) {
                    refreshTable(results);
                } else {
                    JOptionPane.showMessageDialog(this, "No book found matching: " + query);
                }
            }
        }
    }

    private void showIssueDialog() {
        String id = JOptionPane.showInputDialog(this, "Enter Book ID to Issue:");
        if (id != null && !id.isEmpty()) {
            if (manager.issueBook(id)) {
                refreshTable(manager.getAllBooks());
                JOptionPane.showMessageDialog(this, "Book Issued Successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Book not found or out of stock.");
            }
        }
    }

    private void showReturnDialog() {
        String id = JOptionPane.showInputDialog(this, "Enter Book ID to Return:");
        if (id != null && !id.isEmpty()) {
            if (manager.returnBook(id)) {
                refreshTable(manager.getAllBooks());
                JOptionPane.showMessageDialog(this, "Book Returned Successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Book not found.");
            }
        }
    }

    private void showDeleteDialog() {
        String id = JOptionPane.showInputDialog(this, "Enter Book ID to Delete:");
        if (id != null && !id.isEmpty()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete book ID: " + id + "?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (manager.deleteBook(id)) {
                    refreshTable(manager.getAllBooks());
                    JOptionPane.showMessageDialog(this, "Book Deleted Successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Book not found.");
                }
            }
        }
    }
}
