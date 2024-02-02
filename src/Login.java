/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private JTextField username;
    private JPasswordField password;
    private int loginAttempts = 3;

    public Login(){
        initializeComponents();
        pack();

    }
    private void initializeComponents(){
        JPanel panel = new JPanel ();
        JLabel usernameLabel = new JLabel ("Username");
        JLabel passwordLabel = new JLabel("Password:");

        username = new JTextField(15);
        password = new JPasswordField(15);

        JButton loginButton = new JButton ("Login");
        loginButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                onLoginButtonClick();
            }
        });

        panel.add(usernameLabel);
        panel.add(username);
        panel.add(passwordLabel);
        panel.add(password);
        panel.add(loginButton);

        add(panel);
    }
    private void onLoginButtonClick(){
        String uname= username.getText();
        char[] passwordChars = password.getPassword();
        String pw = new String (passwordChars);

        if(authenticate(uname,pw)){
            JOptionPane.showMessageDialog(this, "Login Successful");
            TestPanel secondFrame = new TestPanel();
            secondFrame.show();
            secondFrame.setLocationRelativeTo(null);
            setVisible(false);
            loginAttempts=3;
        } else{
            loginAttempts--;
            if(loginAttempts > 0){
                JOptionPane.showMessageDialog(this, "Login Failed. You Have" + loginAttempts + "attemps left.");
            } else {
                JOptionPane.showMessageDialog(this, "Login attempts exceeded. Account Locked");
                System.exit(0);

            }
        }
        password.setText("");
    }

    private boolean authenticate(String username, String password){
        return username.equals("admin") && password.equals("admin123");
    }

}
