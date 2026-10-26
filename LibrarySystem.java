package GUI;
import Entity.*;
import LibraryManager.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LibrarySystem extends JFrame implements MouseListener, ActionListener {
    private JPanel panel, headingPanel, managePanel, libraryImagePanel, bookImagePanel;
    private JTextField searchBar;
    private JLabel titleLabel, searchLabel, booksLabel, borrowersLabel;
    private JButton searchBtn, addBookBtn, borrowBookBtn, returnBookBtn, viewBooksBtn, viewBorrowersBtn, viewDueDatesBtn;
    private JLabel[] bookImages;
    private JButton[] borrowButtons;
    private JTextArea displayArea;
    private JScrollPane displayScrollPane;
    private final LibraryManager libraryManager;

    public LibrarySystem() {
        this.setTitle("Online Library System");
        this.setSize(1800, 1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        libraryManager = new LibraryManager();

        initializeComponents();
        setupLayout();
        this.setVisible(true);
    }

    private void initializeComponents() {
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(217, 179, 130));

        // Heading Panel (extended to full width)
        headingPanel = new JPanel();
        headingPanel.setLayout(null);
        headingPanel.setBounds(0, 0, 1800, 80);
        headingPanel.setBackground(new Color(70, 130, 180));

        titleLabel = new JLabel("Online Library System");
        titleLabel.setBounds(20, 10, 400, 60);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);

        searchLabel = new JLabel("Search:");
        searchLabel.setBounds(450, 20, 80, 30);
        searchLabel.setFont(new Font("Arial", Font.BOLD, 16));
        searchLabel.setForeground(Color.WHITE);

        searchBar = new JTextField();
        searchBar.setBounds(530, 20, 300, 30);

        searchBtn = new JButton("Search");
        searchBtn.setBounds(850, 20, 100, 30);
        searchBtn.setBackground(new Color(50, 205, 50));
        searchBtn.setFocusable(false);
        searchBtn.addMouseListener(this);
        searchBtn.addActionListener(this);

        // Management Panel
        managePanel = new JPanel();
        managePanel.setLayout(null);
        managePanel.setBounds(20, 100, 300, 500);
        managePanel.setBackground(new Color(220, 220, 220));
        managePanel.setBorder(BorderFactory.createTitledBorder("Management"));

        booksLabel = new JLabel("Books Management");
        booksLabel.setBounds(20, 30, 200, 30);
        booksLabel.setFont(new Font("Arial", Font.BOLD, 16));

        addBookBtn = new JButton("Add New Book");
        addBookBtn.setBounds(20, 70, 250, 40);
        addBookBtn.setBackground(new Color(100, 149, 237));
        addBookBtn.setFocusable(false);
        addBookBtn.addMouseListener(this);
        addBookBtn.addActionListener(this);

        borrowBookBtn = new JButton("Borrow Book");
        borrowBookBtn.setBounds(20, 120, 250, 40);
        borrowBookBtn.setBackground(new Color(100, 149, 237));
        borrowBookBtn.setFocusable(false);
        borrowBookBtn.addMouseListener(this);
        borrowBookBtn.addActionListener(this);

        returnBookBtn = new JButton("Return Book");
        returnBookBtn.setBounds(20, 170, 250, 40);
        returnBookBtn.setBackground(new Color(100, 149, 237));
        returnBookBtn.setFocusable(false);
        returnBookBtn.addMouseListener(this);
        returnBookBtn.addActionListener(this);

        borrowersLabel = new JLabel("View Information");
        borrowersLabel.setBounds(20, 230, 200, 30);
        borrowersLabel.setFont(new Font("Arial", Font.BOLD, 16));

        viewBooksBtn = new JButton("View All Books");
        viewBooksBtn.setBounds(20, 270, 250, 40);
        viewBooksBtn.setBackground(new Color(100, 149, 237));
        viewBooksBtn.setFocusable(false);
        viewBooksBtn.addMouseListener(this);
        viewBooksBtn.addActionListener(this);

        viewBorrowersBtn = new JButton("View Borrowers");
        viewBorrowersBtn.setBounds(20, 320, 250, 40);
        viewBorrowersBtn.setBackground(new Color(100, 149, 237));
        viewBorrowersBtn.setFocusable(false);
        viewBorrowersBtn.addMouseListener(this);
        viewBorrowersBtn.addActionListener(this);

        viewDueDatesBtn = new JButton("View Due Dates");
        viewDueDatesBtn.setBounds(20, 370, 250, 40);
        viewDueDatesBtn.setBackground(new Color(100, 149, 237));
        viewDueDatesBtn.setFocusable(false);
        viewDueDatesBtn.addMouseListener(this);
        viewDueDatesBtn.addActionListener(this);

        // Library Image Panel with scaled image
        libraryImagePanel = new JPanel();
        libraryImagePanel.setLayout(null);
        libraryImagePanel.setBounds(1060, 100, 600, 550);
        libraryImagePanel.setBackground(new Color(220, 220, 220));
        libraryImagePanel.setBorder(BorderFactory.createTitledBorder("Library View"));
        JLabel libraryImage = new JLabel();
        ImageIcon libraryIcon = new ImageIcon("./images/library.jpg");
        Image libraryImg = libraryIcon.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        libraryImage.setIcon(new ImageIcon(libraryImg));
        libraryImage.setBounds(50, 50, 500, 500);
        libraryImagePanel.add(libraryImage);

        // Book Image Panel 
        bookImagePanel = new JPanel();
        bookImagePanel.setLayout(null);
        bookImagePanel.setBounds(350, 560, 690, 250);
        bookImagePanel.setBackground(new Color(220, 220, 220));
        bookImagePanel.setBorder(BorderFactory.createTitledBorder("Borrow Books"));

        bookImages = new JLabel[4];
        borrowButtons = new JButton[4];

        bookImages[0] = new JLabel();
        bookImages[0].setBounds(20, 20, 150, 150);
        bookImages[0].setBackground(Color.WHITE);
        bookImages[0].setOpaque(true);
        ImageIcon icon0 = new ImageIcon("./images/book1.jpg");
        Image img0 = icon0.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        bookImages[0].setIcon(new ImageIcon(img0));
        bookImagePanel.add(bookImages[0]);

        bookImages[1] = new JLabel();
        bookImages[1].setBounds(190, 20, 150, 150);
        bookImages[1].setBackground(Color.WHITE);
        bookImages[1].setOpaque(true);
        ImageIcon icon1 = new ImageIcon("./images/book2.jpg");
        Image img1 = icon1.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        bookImages[1].setIcon(new ImageIcon(img1));
        bookImagePanel.add(bookImages[1]);

        bookImages[2] = new JLabel();
        bookImages[2].setBounds(360, 20, 150, 150);
        bookImages[2].setBackground(Color.WHITE);
        bookImages[2].setOpaque(true);
        ImageIcon icon2 = new ImageIcon("./images/book3.jpg");
        Image img2 = icon2.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        bookImages[2].setIcon(new ImageIcon(img2));
        bookImagePanel.add(bookImages[2]);

        bookImages[3] = new JLabel();
        bookImages[3].setBounds(530, 20, 150, 150);
        bookImages[3].setBackground(Color.WHITE);
        bookImages[3].setOpaque(true);
        ImageIcon icon3 = new ImageIcon("./images/book4.jpg");
        Image img3 = icon3.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        bookImages[3].setIcon(new ImageIcon(img3));
        bookImagePanel.add(bookImages[3]);

        // Add borrow buttons
        borrowButtons[0] = new JButton("Borrow");
        borrowButtons[0].setBounds(20, 180, 150, 40);
        borrowButtons[0].setBackground(new Color(100, 149, 237));
        borrowButtons[0].setFocusable(false);
        borrowButtons[0].addMouseListener(this);
        borrowButtons[0].addActionListener(this);
        bookImagePanel.add(borrowButtons[0]);

        borrowButtons[1] = new JButton("Borrow");
        borrowButtons[1].setBounds(190, 180, 150, 40);
        borrowButtons[1].setBackground(new Color(100, 149, 237));
        borrowButtons[1].setFocusable(false);
        borrowButtons[1].addMouseListener(this);
        borrowButtons[1].addActionListener(this);
        bookImagePanel.add(borrowButtons[1]);

        borrowButtons[2] = new JButton("Borrow");
        borrowButtons[2].setBounds(360, 180, 150, 40);
        borrowButtons[2].setBackground(new Color(100, 149, 237));
        borrowButtons[2].setFocusable(false);
        borrowButtons[2].addMouseListener(this);
        borrowButtons[2].addActionListener(this);
        bookImagePanel.add(borrowButtons[2]);

        borrowButtons[3] = new JButton("Borrow");
        borrowButtons[3].setBounds(530, 180, 150, 40);
        borrowButtons[3].setBackground(new Color(100, 149, 237));
        borrowButtons[3].setFocusable(false);
        borrowButtons[3].addMouseListener(this);
        borrowButtons[3].addActionListener(this);
        bookImagePanel.add(borrowButtons[3]);

        // Display Area 
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Arial", Font.PLAIN, 14));
        displayArea.setBackground(Color.WHITE);

        displayScrollPane = new JScrollPane(displayArea);
        displayScrollPane.setBounds(350, 100, 690, 450);
        displayScrollPane.setBorder(BorderFactory.createTitledBorder("Library Information"));
    }

    private void setupLayout() {
        headingPanel.add(titleLabel);
        headingPanel.add(searchLabel);
        headingPanel.add(searchBar);
        headingPanel.add(searchBtn);
        panel.add(headingPanel);

        managePanel.add(booksLabel);
        managePanel.add(addBookBtn);
        managePanel.add(borrowBookBtn);
        managePanel.add(returnBookBtn);
        managePanel.add(borrowersLabel);
        managePanel.add(viewBooksBtn);
        managePanel.add(viewBorrowersBtn);
        managePanel.add(viewDueDatesBtn);
        panel.add(managePanel);

        panel.add(libraryImagePanel);
        panel.add(bookImagePanel);
        panel.add(displayScrollPane);
        this.add(panel);
    }

    private void updateDisplay(String text) {
        displayArea.setText(text);
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == searchBtn) {
            searchBtn.setBackground(new Color(34, 139, 34));
        } else if (e.getSource() instanceof JButton) {
            ((JButton)e.getSource()).setBackground(new Color(65, 105, 225));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == searchBtn) {
            searchBtn.setBackground(new Color(50, 205, 50));
        } else if (e.getSource() instanceof JButton) {
            ((JButton)e.getSource()).setBackground(new Color(100, 149, 237));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Check if the source is one of the borrow buttons
        for (int i = 0; i < borrowButtons.length; i++) {
            if (e.getSource() == borrowButtons[i]) {
                handleBorrowClick(i);
                return;
            }
        }

        if (e.getSource() == searchBtn) {
            handleSearch();
        } else if (e.getSource() == addBookBtn) {
            addNewBook();
        } else if (e.getSource() == borrowBookBtn) {
            borrowBook();
        } else if (e.getSource() == returnBookBtn) {
            returnBook();
        } else if (e.getSource() == viewBooksBtn) {
            viewAllBooks();
        } else if (e.getSource() == viewBorrowersBtn) {
            viewBorrowers();
        } else if (e.getSource() == viewDueDatesBtn) {
            viewDueDates();
        }
    }

    private void handleBorrowClick(int bookIndex) {
        String bookTitle = "Book " + (bookIndex + 1); // Placeholder title
        String borrowerName = JOptionPane.showInputDialog(this, "Enter borrower name for " + bookTitle + ":");
        if (borrowerName != null && !borrowerName.trim().isEmpty()) {
            String dueDate = JOptionPane.showInputDialog(this, "Enter due date (YYYY-MM-DD):");
            if (dueDate != null && !dueDate.trim().isEmpty()) {
                updateDisplay("Book borrowed successfully:\n\n" + 
                        "Title: " + bookTitle + "\n" +
                        "Borrower: " + borrowerName + "\n" +
                        "Due Date: " + dueDate);
            }
        }
    }

    private void handleSearch() {
        String searchTerm = searchBar.getText().trim();
        if (!searchTerm.isEmpty()) {
            List<Book> results = libraryManager.searchBooks(searchTerm);
            if (results.isEmpty()) {
                updateDisplay("No books found matching: " + searchTerm);
            } else {
                StringBuilder sb = new StringBuilder("Search Results (" + results.size() + "):\n\n");
                for (Book book : results) {
                    sb.append(formatBookInfo(book)).append("\n\n");
                }
                updateDisplay(sb.toString());
            }
        } else {
            updateDisplay("Please enter a search term.");
        }
    }

    private String formatBookInfo(Book book) {
        return "Title: " + book.getTitle() + "\n" +
               "Author: " + book.getAuthor() + "\n" +
               "ISBN: " + book.getIsbn() + "\n" +
               "Status: " + (book.isAvailable() ? "Available" : "Borrowed by " + book.getBorrower()) + "\n" +
               (book.isAvailable() ? "" : "Due Date: " + book.getDueDate());
    }

    private void addNewBook() {
        JTextField titleField = new JTextField();
        JTextField authorField = new JTextField();
        JTextField isbnField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Title:"));
        panel.add(titleField);
        panel.add(new JLabel("Author:"));
        panel.add(authorField);
        panel.add(new JLabel("ISBN:"));
        panel.add(isbnField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Add New Book", 
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if (result == JOptionPane.OK_OPTION) {
            String title = titleField.getText().trim();
            String author = authorField.getText().trim();
            String isbn = isbnField.getText().trim();
            
            if (!title.isEmpty() && !author.isEmpty() && !isbn.isEmpty()) {
                Book book = new Book(title, author, isbn);
                libraryManager.addBook(book);
                updateDisplay("Book added successfully:\n\n" + formatBookInfo(book));
            } else {
                JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void borrowBook() {
        List<Book> availableBooks = libraryManager.getAvailableBooks();
        if (availableBooks.isEmpty()) {
            updateDisplay("No books available for borrowing.");
            return;
        }

        Map<String, Book> bookMap = new LinkedHashMap<>();
        for (Book book : availableBooks) {
            String displayString = book.getTitle() + " by " + book.getAuthor() + " (ISBN: " + book.getIsbn() + ")";
            bookMap.put(displayString, book);
        }

        String selectedOption = (String) JOptionPane.showInputDialog(
                this,
                "Select a book to borrow:",
                "Borrow Book",
                JOptionPane.PLAIN_MESSAGE,
                null,
                bookMap.keySet().toArray(),
                bookMap.keySet().toArray()[0]);

        if (selectedOption != null) {
            Book selectedBook = bookMap.get(selectedOption);
            String borrowerName = JOptionPane.showInputDialog(this, "Enter borrower name:");
            if (borrowerName != null && !borrowerName.trim().isEmpty()) {
                String dueDate = JOptionPane.showInputDialog(this, "Enter due date (YYYY-MM-DD):");
                if (dueDate != null && !dueDate.trim().isEmpty()) {
                    libraryManager.borrowBook(selectedBook, borrowerName, dueDate);
                    updateDisplay("Book borrowed successfully:\n\n" + 
                            "Title: " + selectedBook.getTitle() + "\n" +
                            "Author: " + selectedBook.getAuthor() + "\n" +
                            "Borrower: " + borrowerName + "\n" +
                            "Due Date: " + dueDate);
                }
            }
        }
    }

    private void returnBook() {
        List<Book> borrowedBooks = libraryManager.getBorrowedBooks();
        if (borrowedBooks.isEmpty()) {
            updateDisplay("No books currently borrowed.");
            return;
        }

        Map<String, Book> bookMap = new LinkedHashMap<>();
        for (Book book : borrowedBooks) {
            String displayString = book.getTitle() + " by " + book.getAuthor() + 
                                 " (Borrower: " + book.getBorrower() + ", Due: " + book.getDueDate() + ")";
            bookMap.put(displayString, book);
        }

        String selectedOption = (String) JOptionPane.showInputDialog(
                this,
                "Select a book to return:",
                "Return Book",
                JOptionPane.PLAIN_MESSAGE,
                null,
                bookMap.keySet().toArray(),
                bookMap.keySet().toArray()[0]);

        if (selectedOption != null) {
            Book selectedBook = bookMap.get(selectedOption);
            libraryManager.returnBook(selectedBook);
            updateDisplay("Book returned successfully:\n\n" + 
                    "Title: " + selectedBook.getTitle() + "\n" +
                    "Author: " + selectedBook.getAuthor() + "\n" +
                    "Status: Now available");
        }
    }

    private void viewAllBooks() {
        List<Book> books = libraryManager.getAllBooks();
        if (books.isEmpty()) {
            updateDisplay("No books in the library.");
        } else {
            StringBuilder sb = new StringBuilder("All Books in Library (" + books.size() + "):\n\n");
            for (Book book : books) {
                sb.append(formatBookInfo(book)).append("\n\n");
            }
            updateDisplay(sb.toString());
        }
    }

    private void viewBorrowers() {
        List<Book> borrowedBooks = libraryManager.getBorrowedBooks();
        if (borrowedBooks.isEmpty()) {
            updateDisplay("No books currently borrowed.");
        } else {
            StringBuilder sb = new StringBuilder("Current Borrowers (" + borrowedBooks.size() + "):\n\n");
            for (Book book : borrowedBooks) {
                sb.append("Title: ").append(book.getTitle()).append("\n")
                  .append("Author: ").append(book.getAuthor()).append("\n")
                  .append("Borrower: ").append(book.getBorrower()).append("\n")
                  .append("Due Date: ").append(book.getDueDate()).append("\n\n");
            }
            updateDisplay(sb.toString());
        }
    }

    private void viewDueDates() {
        List<Book> borrowedBooks = libraryManager.getBorrowedBooks();
        if (borrowedBooks.isEmpty()) {
            updateDisplay("No books currently borrowed.");
        } else {
            StringBuilder sb = new StringBuilder("Upcoming Due Dates (" + borrowedBooks.size() + "):\n\n");
            for (Book book : borrowedBooks) {
                sb.append("Title: ").append(book.getTitle()).append("\n")
                  .append("Author: ").append(book.getAuthor()).append("\n")
                  .append("Due Date: ").append(book.getDueDate()).append("\n\n");
            }
            updateDisplay(sb.toString());
        }
    }
}