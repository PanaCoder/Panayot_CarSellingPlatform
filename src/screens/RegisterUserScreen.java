package screens;

import helpers.Helpers;
import models.Advertisement;
import models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RegisterUserScreen extends JFrame implements ActionListener {
    public JLabel userNameLabel;
    public JTextField userNameTextField;
    public JLabel passLabel;
    public JTextField passTextField;
    public JLabel fullNameLabel;
    public JTextField fullNameTextField;
    public JLabel ageLabel;
    public JTextField ageTextField;
    public JLabel cityLabel;
    public JTextField cityTextField;
    public JRadioButton maleRadioButton;
    public JRadioButton femaleRadioButton;
    public JButton registerButton;
    public JButton backButton;
    public JLabel successRegLabel;
    public ArrayList<User> users;
    public ArrayList<Advertisement> ads;
    public JLabel phoneNumberLabel;
    public JTextField phoneNumberTextField;

    public RegisterUserScreen(ArrayList<User> users, ArrayList<Advertisement> ads) {
        super("Registration user");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 300);
        setLayout(new GridLayout(9, 2, 5, 10));

        this.users = users;
        this.ads = ads;

        userNameLabel = new JLabel("Потребителско име: ");
        userNameTextField = new JTextField(10);
        passLabel = new JLabel("Парола: ");
        passTextField = new JTextField(10);
        fullNameLabel = new JLabel("Име и фамилия: ");
        fullNameTextField = new JTextField(10);
        ageLabel = new JLabel("Години: ");
        ageTextField = new JTextField(10);
        cityLabel = new JLabel("Град/Село: ");
        cityTextField = new JTextField(10);
        phoneNumberLabel = new JLabel("Телефонен номер: ");
        phoneNumberTextField = new JTextField(10);
        maleRadioButton = new JRadioButton("мъж");
        femaleRadioButton = new JRadioButton("жена");
        registerButton = new JButton("РЕГИСТРАЦИЯ");
        successRegLabel = new JLabel();
        backButton = new JButton("НАЗАД");

        add(userNameLabel);
        add(userNameTextField);
        add(passLabel);
        add(passTextField);
        add(fullNameLabel);
        add(fullNameTextField);
        add(ageLabel);
        add(ageTextField);
        add(cityLabel);
        add(cityTextField);
        add(phoneNumberLabel);
        add(phoneNumberTextField);
        add(maleRadioButton);
        add(femaleRadioButton);
        add(registerButton);
        add(successRegLabel);
        add(backButton);

        registerButton.addActionListener(this);
        backButton.addActionListener(this);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);
        maleRadioButton.setSelected(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Pressed Register Button
        if (e.getSource() == registerButton) {
            registerUser();
        }
        //Pressed Back Button
        if (e.getSource() == backButton) {
            LoginScreen loginScreen = new LoginScreen(users , ads);
            loginScreen.setVisible(true);
            dispose();
        }
    }

    // Registration method with all checks
    public void registerUser() {
        //Checking fields for symbols
        if (userNameTextField.getText().length() < 8) {
            Helpers.showError("Потребителското име трябва да е най-малко 8 символа");
            return;
        }
        if (passTextField.getText().length() < 8) {
            Helpers.showError("Паролата трябва да е най-малко 8 символа");
            return;
        }
        if (fullNameTextField.getText().length() < 3) {
            Helpers.showError("Името и фамилията трябва да са най-малко 3 символа");
            return;
        }
        if (ageTextField.getText().length() == 0) {
            Helpers.showError("Годините са задължително поле");
            return;
        }
        if (!Helpers.isValidAge(ageTextField.getText())) {
            Helpers.showError("Годините трябва да са цяло число над 18");
            return;
        }
        if (cityTextField.getText().length() < 3) {
            Helpers.showError("Градът трябва да има най-малко 3 символа");
        }
        String sex;
        if (maleRadioButton.isSelected()) {
            sex = "мъж";
        } else {
            sex = "жена";
        }

        String username = userNameTextField.getText();
        String password = passTextField.getText();
        String fullName = fullNameTextField.getText();
        int age = Integer.parseInt(ageTextField.getText());
        String city = cityTextField.getText();
        String phone = phoneNumberTextField.getText();

        //Checking username is free
        boolean isExistingUser = false;
        for (User dummy : users) {
            if (dummy.getUsername().equals(username)) {
                isExistingUser = true;
                break;
            }
        }
        //Username free - adding it to ArrayList
        if (!isExistingUser) {
            users.add(new User(username, password, fullName, age, city, sex, phone));
            successRegLabel.setText("Успешна регистрация");
            registerButton.setEnabled(false);
            System.out.println(users);
        }
        //Username not free - show error message
        else {
            Helpers.showError("Потребителското име е заето");
            return;
        }
    }
}
