package screens;

import com.company.Main;
import helpers.Helpers;
import models.Advertisement;
import models.User;
import models.UserType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//  Program is started
//  Login or Register user

public class LoginScreen extends JFrame implements ActionListener {
    //  Buttons
    public JButton loginButton;
    public JButton registerButton;

    //  Labels
    public JLabel welcomeLabel;
    public JLabel nullLabel;
    public JLabel userNameLabel;
    public JLabel passLabel;

    // TextFields
    public JTextField userNameField;
    public JTextField passField;

    // Arrays
    public ArrayList<User> users;
    public ArrayList<Advertisement> ads;

    public LoginScreen(ArrayList<User> users, ArrayList<Advertisement> ads) {
        super("Login or Register");
        this.users = users;
        this.ads = ads;

        setFrameSettings();
        registerInterface();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            login();
        }
        if (e.getSource() == registerButton) {
            registerUser();
        }
    }

    public void setFrameSettings() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1, 5, 5));
        setSize(450, 350);
    }

    public void registerInterface() {
        welcomeLabel = new JLabel("WELCOME", SwingConstants.RIGHT);
        nullLabel = new JLabel("");
        userNameLabel = new JLabel("Потребителско име: ", SwingConstants.CENTER);
        userNameField = new JTextField("EvgeniAndreev");
        passLabel = new JLabel("Парола: ", SwingConstants.CENTER);
        passField = new JTextField("12345678");
        loginButton = new JButton("ВХОД");
        registerButton = new JButton("Регистрация");

        add(welcomeLabel);
        add(nullLabel);
        add(userNameLabel);
        add(userNameField);
        add(passLabel);
        add(passField);
        add(loginButton);
        add(registerButton);

        loginButton.addActionListener(this);
        registerButton.addActionListener(this);

        pack();
    }

    // Do Login
    public void login() {
        //Checking fields username and password for 8 symbols
        if (this.userNameField.getText().length() < 8 || this.passField.getText().length() < 8) {
            Helpers.showError("Потребителското име и паролата трябва да са минимум 8 символа");
            return;
        }
        //Username and Password verification -> true -> next Screen
        //Sending ArrayList of Cars and User + currentUser to next frame
        boolean isUsernameFound = false;
        boolean isPasswordFound = false;
        User currentUser = null;
        for (User myUser : users) {
            if (userNameField.getText().equals(myUser.getUsername())) {
                isUsernameFound = true;
                if (passField.getText().equals(myUser.getPass())) {
                    isPasswordFound = true;
                    currentUser = myUser;
                }
            }
        }
        if (isUsernameFound && isPasswordFound) {
            if (currentUser.getUserType() == UserType.ADMIN) {
                System.out.println("ADMIN LOG ON!!!");
                HomeScreen_Admin homeScreenAdmin = new HomeScreen_Admin(users, ads, currentUser);
                homeScreenAdmin.setVisible(true);
            } else {
                System.out.println("REGULAR USER LOG ON!!!");
                HomeScreen_Regular homeScreenRegular = new HomeScreen_Regular(users, ads, currentUser);
                homeScreenRegular.setVisible(true);
            }
            dispose();
        } else {
            Helpers.showError("Грешнo потребителско име или парола");
        }

    }

    public void registerUser() {
        setVisible(false);
        RegisterUserScreen regUserScreen = new RegisterUserScreen(users, ads);
        regUserScreen.setVisible(true);
    }
}
