package screens;

import helpers.Helpers;
import models.Advertisement;
import models.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HomeScreen_Regular extends JFrame implements ActionListener {
    public JTextField searchTextField;
    public JButton myAdsButton;
    public JButton searchAdvertisementButton;
    public ArrayList<User> users;
    public ArrayList<Advertisement> ads;
    public ArrayList<Advertisement> searchedAds;
    public DefaultTableModel tableModel;
    public JTable table;
    public User currentUser;
    public boolean isSearching;
    public JButton logOutButton;
    public JButton showAllAdsButton;

    public HomeScreen_Regular(ArrayList<User> users, ArrayList<Advertisement> ads, User currentUser) {
        super("Добре дошъл " + currentUser.getFullName());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLayout(new GridLayout(6, 0, 5, 10));

        this.users = users;
        this.ads = ads;
        this.currentUser = currentUser;

        tableModel = new DefaultTableModel();
        String[] columns = {"Марка", "Модел", "Цена(BGN)", "Километри", "Продавач", "Телефон", "Локация"};
        tableModel.setColumnIdentifiers(columns);

        table = new JTable(tableModel);
        add(new JScrollPane(table));

        searchTextField = new JTextField("Въведи търсената обява");
        
        searchAdvertisementButton = new JButton("Търси");
        searchAdvertisementButton.addActionListener(this);
        myAdsButton = new JButton("Моите обяви");
        myAdsButton.addActionListener(this);
        logOutButton = new JButton("Изход от профила");
        logOutButton.addActionListener(this);
        showAllAdsButton = new JButton("Покажи всички обяви");
        showAllAdsButton.addActionListener(this);


        add(searchTextField);
        add(searchAdvertisementButton);
        add(showAllAdsButton);
        add(myAdsButton);
        add(logOutButton);

        refreshTableData();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == myAdsButton) {
            MyAdsScreen myAdsScreen = new MyAdsScreen(users, ads, currentUser);
            myAdsScreen.setVisible(true);
            dispose();

        } else if (e.getSource() == searchAdvertisementButton) {
            searchAction();
        } else if (e.getSource() == showAllAdsButton) {
            refreshTableData();
        } else if (e.getSource() == logOutButton) {
            LoginScreen loginScreen = new LoginScreen(users,ads);
            loginScreen.setVisible(true);
            dispose();
        }
    }

    public void showAdvertisements(ArrayList<Advertisement> list) {
        for (Advertisement ad :
                list) {
            Object[] row = new Object[7];
            row[0] = ad.getBrand();
            row[1] = ad.getModel();
            row[2] = ad.getPrice();
            row[3] = ad.getMillage();
            row[4] = ad.getOwner().getFullName();
            row[5] = ad.getOwner().getPhoneNumber();
            row[6] = ad.getOwner().getCity();
            tableModel.addRow(row);
        }
        isSearching = false;
    }

    public void refreshTableData() {
        tableModel.setRowCount(0);

        if (this.isSearching == true) {
            showAdvertisements(this.searchedAds);
        } else
            showAdvertisements(this.ads);
    }

    public void searchAction() {
        if (searchTextField.getText().length() == 0) {
            this.isSearching = false;
            refreshTableData();
            return;
        }
        this.isSearching = true;
        String searchText = searchTextField.getText();

        boolean isFound = false;
        searchedAds = new ArrayList<>();
        for (int i = 0; i < this.ads.size(); i++) {
            Advertisement ad = this.ads.get(i);
            String combine = ad.getBrand() + " " + ad.getModel();
            if (combine.toLowerCase().contains(searchText.toLowerCase())) {
                isFound = true;
                this.searchedAds.add(ad);
            }
        }
        if (isFound == true) {
            refreshTableData();
        } else
            Helpers.showError("Няма резултати за: " + searchText);
    }
}
