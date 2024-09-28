package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LibraryGUI {
    private JFrame frame;
    private JTextArea textArea;
    private JTextField inputField;
    private JButton addBookButton, upgradeBookButton, searchBookButton, showBooksButton;
    private JButton addStudentButton, showStudentsButton, checkOutBookButton, checkInBookButton;

    private Books books;
    private Students students;

    public LibraryGUI(Books books, Students students) {
        this.books = books;
        this.students = students;

        frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        inputField = new JTextField();
        inputField.setPreferredSize(new Dimension(200, 25));

        addBookButton = new JButton("Add Book");
        upgradeBookButton = new JButton("Upgrade Book Quantity");
        searchBookButton = new JButton("Search Book");
        showBooksButton = new JButton("Show All Books");

        addStudentButton = new JButton("Register Student");
        showStudentsButton = new JButton("Show All Students");
        checkOutBookButton = new JButton("Check Out Book");
        checkInBookButton = new JButton("Check In Book");

        // Add Components to Frame
        JPanel panel = new JPanel();
        panel.add(addBookButton);
        panel.add(upgradeBookButton);
        panel.add(searchBookButton);
        panel.add(showBooksButton);
        panel.add(addStudentButton);
        panel.add(showStudentsButton);
        panel.add(checkOutBookButton);
        panel.add(checkInBookButton);
        frame.getContentPane().add(BorderLayout.NORTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER, scrollPane);

        // Action Listeners
        addBookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String sNoStr = JOptionPane.showInputDialog("Enter Book Serial Number:");
                String bookName = JOptionPane.showInputDialog("Enter Book Name:");
                String authorName = JOptionPane.showInputDialog("Enter Author Name:");
                String bookQtyStr = JOptionPane.showInputDialog("Enter Book Quantity:");

                try {
                    int sNo = Integer.parseInt(sNoStr.trim());
                    int bookQty = Integer.parseInt(bookQtyStr.trim());
                    Book newBook = new Book(sNo, bookName, authorName, bookQty);
                    books.addBook(newBook);
                    textArea.append("Book added: " + newBook.toString() + "\n");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input format.");
                }
            }
        });

        upgradeBookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String sNoStr = JOptionPane.showInputDialog("Enter Book Serial Number to Upgrade:");
                String bookQtyStr = JOptionPane.showInputDialog("Enter Quantity to Add:");

                try {
                    int sNo = Integer.parseInt(sNoStr.trim());
                    int addingQty = Integer.parseInt(bookQtyStr.trim());
                    books.upgradeBookQty(sNo, addingQty);
                    textArea.append("Book quantity updated for Serial Number: " + sNo + "\n");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input format.");
                }
            }
        });

        searchBookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String searchChoiceStr = JOptionPane.showInputDialog("Search by:\n1. Serial Number\n2. Author Name");
                int searchChoice = Integer.parseInt(searchChoiceStr.trim());

                if (searchChoice == 1) {
                    String sNoStr = JOptionPane.showInputDialog("Enter Book Serial Number:");
                    try {
                        int sNo = Integer.parseInt(sNoStr.trim());
                        String result = books.searchBySno(sNo);
                        textArea.append(result + "\n");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Invalid input format.");
                    }
                } else if (searchChoice == 2) {
                    String authorName = JOptionPane.showInputDialog("Enter Author Name:");
                    String result = books.searchByAuthorName(authorName);
                    textArea.append(result + "\n");
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid search choice.");
                }
            }
        });

        showBooksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String result = books.showAllBooks();
                textArea.append(result + "\n");
            }
        });

        addStudentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("Enter Student Name:");
                String regNum = JOptionPane.showInputDialog("Enter Registration Number:");
                Student newStudent = new Student(name, regNum);
                students.addStudent(newStudent);
                textArea.append("Student added: " + newStudent.toString() + "\n");
            }
        });

        showStudentsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String result = students.showAllStudents();
                textArea.append(result + "\n");
            }
        });

        checkOutBookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String regNum = JOptionPane.showInputDialog("Enter Student Registration Number:");
                Student student = students.getStudentByRegNum(regNum);
                if (student != null) {
                    String sNoStr = JOptionPane.showInputDialog("Enter Book Serial Number to Check Out:");
                    try {
                        int sNo = Integer.parseInt(sNoStr.trim());
                        Book book = books.checkOutBook(sNo);
                        if (book != null) {
                            student.borrowBook(book);
                            textArea.append("Book checked out: " + book.toString() + "\n");
                        } else {
                            textArea.append("Book not available.\n");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Invalid input format.");
                    }
                } else {
                    textArea.append("Student not found.\n");
                }
            }
        });

        checkInBookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String regNum = JOptionPane.showInputDialog("Enter Student Registration Number:");
                Student student = students.getStudentByRegNum(regNum);
                if (student != null) {
                    String sNoStr = JOptionPane.showInputDialog("Enter Book Serial Number to Check In:");
                    try {
                        int sNo = Integer.parseInt(sNoStr.trim());
                        Book book = student.returnBook(sNo);
                        if (book != null) {
                            books.checkInBook(book);
                            textArea.append("Book checked in: " + book.toString() + "\n");
                        } else {
                            textArea.append("Book not found in borrowed books.\n");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Invalid input format.");
                    }
                } else {
                    textArea.append("Student not found.\n");
                }
            }
        });

        frame.setVisible(true);
    }
}
