package screens;

import helpers.Helpers;
import models.Advertisement;
import models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddAdvertisementScreen extends JFrame implements ActionListener {
    public JLabel brandLabel;
    public JTextField brandTextField;
    public JLabel modelLabel;
    public JTextField modelTextField;
    public JLabel millageLabel;
    public JTextField millageTextField;
    public JLabel priseLabel;
    public JTextField priseTextField;
    public JButton addAdvertisementButton;
    public JButton backButton;
    public ArrayList<User>users;
    public ArrayList<Advertisement>ads;
    public User currentUser;

    public AddAdvertisementScreen(ArrayList<User> users, ArrayList<Advertisement> ads, User currentUser) {
        super("ДОБАВЯНЕ НА ОБЯВА");
        this.users = users;
        this.ads = ads;
        this.currentUser = currentUser;

        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(500,300);
        setLayout(new GridLayout(5, 2, 5, 10));
        setVisible(true);

        brandLabel = new JLabel("Марка:");
        brandTextField = new JTextField();
        modelLabel = new JLabel("Модел:");
        modelTextField = new JTextField();
        millageLabel = new JLabel("Пробег:");
        millageTextField = new JTextField() ;
        priseLabel = new JLabel("Цена:");
        priseTextField = new JTextField();
        addAdvertisementButton = new JButton("Добави");
        backButton = new JButton("Назад");

        add(brandLabel); add(brandTextField);
        brandTextField.addActionListener(this);
        add(modelLabel); add(modelTextField);
        modelTextField.addActionListener(this);
        add(millageLabel); add(millageTextField);
        millageTextField.addActionListener(this);
        add(priseLabel); add(priseTextField);
        priseTextField.addActionListener(this);
        add(addAdvertisementButton); add(backButton);
        addAdvertisementButton.addActionListener(this);
        backButton.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addAdvertisementButton){
            createAdvertisement();
            if (Helpers.isAdminUser(currentUser)) {
                HomeScreen_Admin homeScreenAdmin = new HomeScreen_Admin(users, ads, currentUser);
                homeScreenAdmin.setVisible(true);
            }
            else {
                MyAdsScreen myAdsScreen = new MyAdsScreen(users,ads,currentUser);
                myAdsScreen.setVisible(true);
            }
            dispose();
        }
        if(e.getSource() == backButton){
            if (Helpers.isAdminUser(currentUser)) {
                HomeScreen_Admin homeScreenAdmin = new HomeScreen_Admin(users, ads, currentUser);
                homeScreenAdmin.setVisible(true);
            }
            else {
                HomeScreen_Regular homeScreen_regular = new HomeScreen_Regular(users, ads, currentUser);
                homeScreen_regular.setVisible(true);
            }
            dispose();
        }
    }

    private void createAdvertisement() {
        double prise;
        if(brandTextField.getText().length() == 0)
            Helpers.showError("Марка е задължително поле");
        else if(modelTextField.getText().length() ==0)
            Helpers.showError("Модел е задължително поле");
        else if(millageTextField.getText().length() == 0)
            Helpers.showError("Пробег е задължително поле");
        else if(!Helpers.isValidPrice(priseTextField.getText()))
            Helpers.showError("Цената трябва е валидно число");

        String brand = brandTextField.getText();
        String model = modelTextField.getText();
        String millage = millageTextField.getText();
        prise = Double.parseDouble(priseTextField.getText());

        ads.add(new Advertisement(brand, model, millage, prise, currentUser));
        addAdvertisementButton.setText("Добави нова обява");
        System.out.println(ads.toString());
//        HomeScreen_Admin homeScreenAdmin = new HomeScreen_Admin(users,ads, currentUser);
//        homeScreenAdmin.setVisible(true);
//        dispose();
    }
}
