package project;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private String regNum;
    private List<Book> borrowedBooks = new ArrayList<>();

    public Student(String name, String regNum) {
        this.name = name;
        this.regNum = regNum;
    }

    @Override
    public String toString() {
        return name + " | " + regNum;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public Book returnBook(int sNo) {
        for (Book b : borrowedBooks) {
            if (b.getSNo() == sNo) {
                borrowedBooks.remove(b);
                return b;
            }
        }
        return null;
    }

    public void showBorrowedBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("No borrowed books.");
        } else {
            for (Book b : borrowedBooks) {
                System.out.println(b.toString());
            }
        }
    }

    // Getters
    public String getRegNum() { return regNum; }
}
