package project;

import java.util.ArrayList;
import java.util.List;

public class Books {
    private List<Book> bookList = new ArrayList<>();

    public void addBook(Book book) {
        for (Book b : bookList) {
            if (b.getSNo() == book.getSNo() || b.getBookName().equalsIgnoreCase(book.getBookName())) {
                return;
            }
        }
        bookList.add(book);
    }

    public void upgradeBookQty(int sNo, int addingQty) {
        for (Book b : bookList) {
            if (b.getSNo() == sNo) {
                b.setBookQty(b.getBookQty() + addingQty);
                b.setBookQtyCopy(b.getBookQtyCopy() + addingQty);
                return;
            }
        }
    }

    public String searchBySno(int sNo) {
        for (Book b : bookList) {
            if (b.getSNo() == sNo) {
                return b.toString();
            }
        }
        return "No Book for Serial No " + sNo + " Found.";
    }

    public String searchByAuthorName(String authorName) {
        StringBuilder result = new StringBuilder();
        for (Book b : bookList) {
            if (b.getAuthorName().equalsIgnoreCase(authorName)) {
                result.append(b.toString()).append("\n");
            }
        }
        return result.length() == 0 ? "No Books of " + authorName + " Found." : result.toString();
    }

    public String showAllBooks() {
        StringBuilder result = new StringBuilder();
        for (Book b : bookList) {
            result.append(b.toString()).append("\n");
        }
        return result.length() == 0 ? "No Books Available." : result.toString();
    }

    public Book checkOutBook(int sNo) {
        for (Book b : bookList) {
            if (b.getSNo() == sNo && b.getBookQty() > 0) {
                b.setBookQty(b.getBookQty() - 1);
                return b;
            }
        }
        return null;
    }

    public void checkInBook(Book book) {
        for (Book b : bookList) {
            if (b.getSNo() == book.getSNo()) {
                b.setBookQty(b.getBookQty() + 1);
                return;
            }
        }
    }
}

