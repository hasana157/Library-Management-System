import java.awt.*;
import java.io.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class LibraryManagementSystem extends JFrame {
    private JPanel mainPanel, homePanel, adminPanel, userPanel;
    private CardLayout cardLayout;

    public LibraryManagementSystem() {
        setTitle("Library Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        createHomePanel();
        createAdminPanel();
        createUserPanel();

        mainPanel.add(homePanel, "Home");
        mainPanel.add(adminPanel, "Admin");
        mainPanel.add(userPanel, "User");

        add(mainPanel);
        showPanel("Home");
        setVisible(true);
        ensureFileExists("books.txt");
    }

    private void createHomePanel() {
        homePanel = new JPanel();
        homePanel.setLayout(new GridLayout(3, 1, 5, 5));  // Use GridLayout to arrange buttons in rows

        JLabel label = new JLabel("Welcome to Library Management System", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));

        JButton adminButton = createStyledButton("Admin", new Color(95, 99, 102));
        JButton userButton = createStyledButton("User", new Color(77, 109, 154));

        adminButton.setToolTipText("Go to Admin Control Panel");
        userButton.setToolTipText("Go to User Control Panel");

        adminButton.addActionListener(e -> showPanel("Admin"));
        userButton.addActionListener(e -> showPanel("User"));

        // Adjust button size
        Dimension buttonSize = new Dimension(300, 70);
        adminButton.setPreferredSize(buttonSize);
        userButton.setPreferredSize(buttonSize);

        homePanel.add(label);
        homePanel.add(adminButton);
        homePanel.add(userButton);
    }

    private void createAdminPanel() {
        adminPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon background = new ImageIcon("library.jpg");
                g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        adminPanel.setLayout(new BoxLayout(adminPanel, BoxLayout.Y_AXIS));
        adminPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel adminLabel = new JLabel("Admin Control Panel", JLabel.CENTER);
        adminLabel.setFont(new Font("Arial", Font.BOLD, 28));
        adminLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        adminLabel.setForeground(Color.BLACK);
        adminPanel.add(adminLabel);

        JButton addBookButton = createStyledButton("Add Book", new Color(134, 179, 209));
        JButton viewBooksButton = createStyledButton("View Books", new Color(153, 206, 211));
        JButton deleteBookButton = createStyledButton("Delete Book", new Color(237, 181, 191));
        JButton issueBookButton = createStyledButton("Issue Book", new Color(77, 109, 154));
        JButton backButton = createStyledButton("Back", new Color(95, 99, 102));

        addBookButton.setToolTipText("Add a new book to the library");
        viewBooksButton.setToolTipText("View all books in the library");
        deleteBookButton.setToolTipText("Delete a book from the library");
        issueBookButton.setToolTipText("Issue a book to a user");
        backButton.setToolTipText("Return to Home Panel");

        addBookButton.addActionListener(e -> addBook());
        viewBooksButton.addActionListener(e -> viewBooks());
        deleteBookButton.addActionListener(e -> deleteBook());
        issueBookButton.addActionListener(e -> issueBook());
        backButton.addActionListener(e -> showPanel("Home"));

        adminPanel.add(Box.createVerticalStrut(20));
        adminPanel.add(addBookButton);
        adminPanel.add(Box.createVerticalStrut(10));
        adminPanel.add(viewBooksButton);
        adminPanel.add(Box.createVerticalStrut(10));
        adminPanel.add(deleteBookButton);
        adminPanel.add(Box.createVerticalStrut(10));
        adminPanel.add(issueBookButton);
        adminPanel.add(Box.createVerticalStrut(10));
        adminPanel.add(backButton);
    }

    private void createUserPanel() {
        userPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon background = new ImageIcon("library.jpg");
                g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));
        userPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel userLabel = new JLabel("User Control Panel", JLabel.CENTER);
        userLabel.setFont(new Font("Arial", Font.BOLD, 28));
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        userLabel.setForeground(Color.BLACK);
        userPanel.add(userLabel);

        JButton borrowBookButton = createStyledButton("Borrow Book", new Color(134, 179, 209));
        JButton returnBookButton = createStyledButton("Return Book", new Color(153, 206, 211));
        JButton viewBooksButton = createStyledButton("View Books", new Color(237, 181, 191));
        JButton backButton = createStyledButton("Back", new Color(77, 109, 154));

        borrowBookButton.setToolTipText("Borrow a book from the library");
        returnBookButton.setToolTipText("Return a borrowed book");
        viewBooksButton.setToolTipText("View all books in the library");
        backButton.setToolTipText("Return to Home Panel");

        borrowBookButton.addActionListener(e -> borrowBook());
        returnBookButton.addActionListener(e -> returnBook());
        viewBooksButton.addActionListener(e -> viewBooks());
        backButton.addActionListener(e -> showPanel("Home"));

        userPanel.add(Box.createVerticalStrut(20));
        userPanel.add(borrowBookButton);
        userPanel.add(Box.createVerticalStrut(10));
        userPanel.add(returnBookButton);
        userPanel.add(Box.createVerticalStrut(10));
        userPanel.add(viewBooksButton);
        userPanel.add(Box.createVerticalStrut(10));
        userPanel.add(backButton);
    }

    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(color);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return button;
    }

    private void showPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }

    private void addBook() {
        String id = JOptionPane.showInputDialog(this, "Enter Book ID:");
        String title = JOptionPane.showInputDialog(this, "Enter Book Title:");
        String author = JOptionPane.showInputDialog(this, "Enter Book Author:");
        String status = "Available";

        if (id != null && title != null && author != null) {
            String bookDetails = id + "," + title + "," + author + "," + status;
            writeToFile("books.txt", bookDetails);
            JOptionPane.showMessageDialog(this, "Book added successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Invalid input. Book not added.");
        }
    }

    private void deleteBook() {
        String bookId = JOptionPane.showInputDialog(this, "Enter Book ID to Delete:");
        if (bookId == null || bookId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Invalid Book ID.");
            return;
        }

        try {
            File file = new File("books.txt");
            File tempFile = new File("temp.txt");

            try (BufferedReader reader = new BufferedReader(new FileReader(file));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

                String line;
                boolean deleted = false;

                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (!parts[0].equals(bookId)) {
                        writer.write(line);
                        writer.newLine();
                    } else {
                        deleted = true;
                    }
                }

                if (deleted) {
                    JOptionPane.showMessageDialog(this, "Book deleted successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Book not found.");
                }
            }

            if (!file.delete() || !tempFile.renameTo(file)) {
                throw new IOException("Error updating file.");
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void issueBook() {
        String bookId = JOptionPane.showInputDialog(this, "Enter Book ID(only positive integer)to Issue:");
        if (bookId == null || bookId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Invalid Book ID.");
            return;
        }

        try {
            File file = new File("books.txt");
            File tempFile = new File("temp.txt");

            try (BufferedReader reader = new BufferedReader(new FileReader(file));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

                String line;
                boolean found = false;

                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts[0].equals(bookId) && parts[3].equals("Available")) {
                        parts[3] = "Issued";  // Mark the book as issued
                        line = String.join(",", parts);////////////unchanged 
                        found = true;
                    }
                    writer.write(line);
                    writer.newLine();
                }

                if (found) {
                    JOptionPane.showMessageDialog(this, "Book issued successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Book not available or not found.");
                }
            }

            if (!file.delete() || !tempFile.renameTo(file)) {
                throw new IOException("Error updating file.");
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
        private void viewBooks() {
            Vector<String> columnNames = new Vector<>();
            columnNames.add("ID");
            columnNames.add("Title");
            columnNames.add("Author");
            columnNames.add("Status");
    
            Vector<Vector<String>> data = new Vector<>();
    
            try (BufferedReader reader = new BufferedReader(new FileReader("books.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    Vector<String> row = new Vector<>();
                    for (String part : parts) {
                        row.add(part);
                    }
                    data.add(row);
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error reading from file: " + e.getMessage());
            }
    
            JTable table = new JTable(new DefaultTableModel(data, columnNames));
            JScrollPane scrollPane = new JScrollPane(table);
            JOptionPane.showMessageDialog(this, scrollPane, "Books", JOptionPane.INFORMATION_MESSAGE);
        
    

        // Functionality to view books can be implemented here, such as displaying them in a table.
    }

        // Borrow book functionality can be added here.
        private void borrowBook() {
            String bookId = JOptionPane.showInputDialog(this, "Enter Book ID to Borrow:");
            if (bookId == null || bookId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Invalid Book ID.");
                return;
            }
    
            try {
                File file = new File("books.txt");
                File tempFile = new File("temp.txt");
    
                try (BufferedReader reader = new BufferedReader(new FileReader(file));
                     BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
    
                    String line;
                    boolean found = false;
    
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(",");
                        if (parts[0].equals(bookId) && parts[3].equals("Available")) {
                            parts[3] = "Borrowed";
                            found = true;
                        }
                        writer.write(String.join(",", parts));
                        writer.newLine();
                    }
    
                    if (found) {
                        JOptionPane.showMessageDialog(this, "Book borrowed successfully! Return the book after a week.");
                    } else {
                        JOptionPane.showMessageDialog(this, "Book not available or invalid ID.");
                    }
                }
                if (!file.delete() || !tempFile.renameTo(file)) {
                    throw new IOException("Error updating file.");
                }
    
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        }
   
        private void returnBook() {
            String bookId = JOptionPane.showInputDialog(this, "Enter Book ID to Return:");
            if (bookId == null || bookId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Invalid Book ID.");
                return;
            }
    
            try {
                File file = new File("books.txt");
                File tempFile = new File("temp.txt");
    
                try (BufferedReader reader = new BufferedReader(new FileReader(file));
                     BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
    
                    String line;
                    boolean found = false;
    
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(",");
                        if (parts[0].equals(bookId) && parts[3].equals("Borrowed")) {
                            parts[3] = "Available";
                            found = true;
                        }
                        writer.write(String.join(",", parts));
                        writer.newLine();
                    }
    
                    if (found) {
                        JOptionPane.showMessageDialog(this, "Book returned successfully!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Book not found or not borrowed.");
                    }
                }
    
                if (!file.delete() || !tempFile.renameTo(file)) {
                    throw new IOException("Error updating file.");
                }
    
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        }

    private void writeToFile(String filename, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(content);
            writer.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error writing to file: " + e.getMessage());
        }
    }

    private void ensureFileExists(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error creating file: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LibraryManagementSystem());
    }
}