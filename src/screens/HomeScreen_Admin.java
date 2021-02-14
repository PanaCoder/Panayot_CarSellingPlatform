package screens;

import helpers.Helpers;
import models.Advertisement;
import models.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

public class HomeScreen_Admin extends JFrame implements ActionListener {
    //Buttons
    public JButton addAdvertisementButton;
    public JButton removeAdvertisementButton;
    public JButton searchAdvertisementButton;
    public JButton logoutButton;
    public JButton showAllAdsButton;

    //TextFields
    public JTextField searchTextField;

    //Data
    public User currentUser;
    public ArrayList<User> users;
    public ArrayList<Advertisement> ads;
    public ArrayList<Advertisement> searchedAds;
    public boolean isSearching;

    //Table Advertisement
    public JTable table;
    public DefaultTableModel tableModel;

    public HomeScreen_Admin(ArrayList<User> users, ArrayList<Advertisement> ads, User currentUser) {
        super("Добре дошъл __ADMIN PANEL__" + currentUser.getFullName());
        this.users = users;
        this.ads = ads;
        this.currentUser = currentUser;

        setFrameSettings();
        registerTable();
        registerInterface();
        refreshTableData();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addAdvertisementButton) {
            addAdvertisement();
            dispose();
        } else if (e.getSource() == removeAdvertisementButton) {
            removeAdvertisement();
        } else if (e.getSource() == searchAdvertisementButton) {
            searchAction();
        } else if (e.getSource() == logoutButton) {
            logout();
        } else if (e.getSource() == showAllAdsButton) {
            refreshTableData();
        }

    }


    public void setFrameSettings() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLayout(new GridLayout(7, 0, 5, 10));
    }

    public void registerTable() {
        tableModel = new DefaultTableModel();
        String[] columns = {"Марка", "Модел", "Цена(BGN)", "Километри", "Продавач", "Телефон", "Локация"};
        tableModel.setColumnIdentifiers(columns);

        table = new JTable(tableModel);
        add(new JScrollPane(table));
    }

    public void registerInterface() {
        searchTextField = new JTextField("Въведи търсената обява");
        searchTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                searchTextField.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });
        addAdvertisementButton = new JButton("Добави обява");
        addAdvertisementButton.addActionListener(this);
        removeAdvertisementButton = new JButton("Изтрий обява");
        removeAdvertisementButton.addActionListener(this);
        searchAdvertisementButton = new JButton("Търси");
        searchAdvertisementButton.addActionListener(this);
        logoutButton = new JButton("Изход от системата  (Admin:" + currentUser.getUsername() + ")");
        logoutButton.addActionListener(this);
        showAllAdsButton = new JButton("Покажи всички обяви");
        showAllAdsButton.addActionListener(this);


        add(searchTextField);
        add(searchAdvertisementButton);
        add(showAllAdsButton);
        add(addAdvertisementButton);
        add(removeAdvertisementButton);
        add(logoutButton);
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

        if (this.isSearching) {
            showAdvertisements(this.searchedAds);
        } else
            showAdvertisements(this.ads);
    }

    public void addAdvertisement() {
        AddAdvertisementScreen addAdvertisementScreen = new AddAdvertisementScreen(users, ads, currentUser);
        addAdvertisementScreen.setVisible(true);
//        refreshTableData();
    }

    public void removeAdvertisement() {
        // Current selected table index
        if (table.getSelectedRow() < 0) {
            return;
        }
        this.ads.remove(table.getSelectedRow());
        refreshTableData();
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
        if (isFound) {
            refreshTableData();
        } else
            Helpers.showError("Няма резултати за: " + searchText);
    }

    public void logout() {
        LoginScreen loginScreen = new LoginScreen(users, ads);
        loginScreen.setVisible(true);
        dispose();
    }
}
