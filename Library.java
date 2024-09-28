package project;
import javax.swing.SwingUtilities;

public class Library {
    public static Books ob = new Books();
    public static Students obStudent = new Students();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LibraryGUI(ob, obStudent));
    }
}
