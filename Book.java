package project;

public class Book {
    private int sNo;
    private String bookName;
    private String authorName;
    private int bookQty;
    private int bookQtyCopy;

    public Book(int sNo, String bookName, String authorName, int bookQty) {
        this.sNo = sNo;
        this.bookName = bookName;
        this.authorName = authorName;
        this.bookQty = bookQty;
        this.bookQtyCopy = bookQty;
    }

    @Override
    public String toString() {
        return sNo + " | " + bookName + " | " + authorName + " | " + bookQtyCopy + " | " + bookQty;
    }

    // Getters and Setters
    public int getSNo() { return sNo; }
    public String getBookName() { return bookName; }
    public String getAuthorName() { return authorName; }
    public int getBookQty() { return bookQty; }
    public int getBookQtyCopy() { return bookQtyCopy; }
    public void setBookQty(int bookQty) { this.bookQty = bookQty; }
    public void setBookQtyCopy(int bookQtyCopy) { this.bookQtyCopy = bookQtyCopy; }
}

