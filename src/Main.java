import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Login mainframe = new Login();
        mainframe.setTitle("Login Form");
        mainframe.setSize(300,150);
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.setLocationRelativeTo(null);
        mainframe.setVisible(true);
    }
}