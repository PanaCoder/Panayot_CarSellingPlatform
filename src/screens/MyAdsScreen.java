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

public class MyAdsScreen extends JFrame implements ActionListener {
    public ArrayList<User> users;
    public ArrayList<Advertisement> ads;
    public DefaultTableModel tableModel;
    public JTable table;
    public JButton removeAdButton;
    public JButton addAdButton;
    public User currentUser;
    public JButton backButton;

    public MyAdsScreen(ArrayList<User> users, ArrayList<Advertisement> ads, User currentUser) {
        super("Обявите на " + currentUser.getFullName());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 450);
        setLayout(new GridLayout(4, 0, 5, 10));

        this.users = users;
        this.ads = ads;
        this.currentUser = currentUser;

        tableModel = new DefaultTableModel();
        String[] columns = {"Марка", "Модел", "Цена(BGN)", "Километри", "Продавач", "Телефон", "Локация"};
        tableModel.setColumnIdentifiers(columns);

        table = new JTable(tableModel);
        add(new JScrollPane(table));

        addAdButton = new JButton("Добави обява");
        addAdButton.addActionListener(this);
        removeAdButton = new JButton("Премахни обява");
        removeAdButton.addActionListener(this);
        backButton = new JButton("Назад");
        backButton.addActionListener(this);

        add(addAdButton);
        add(removeAdButton);
        add(backButton);

        refreshMyTableData();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addAdButton) {
            AddAdvertisementScreen addAdvertisementScreen = new AddAdvertisementScreen(users, ads, currentUser);
            addAdvertisementScreen.setVisible(true);
            dispose();
        } else if (e.getSource() == removeAdButton) {
            removeAdvertisement();
        }else if (e.getSource() == backButton) {
            HomeScreen_Regular homeScreen_regular = new HomeScreen_Regular(users,ads,currentUser);
            homeScreen_regular.setVisible(true);
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
    }

    public void refreshMyTableData() {
        tableModel.setRowCount(0);

        ArrayList<Advertisement> myAds = new ArrayList<>();
        for (int i = 0; i < this.ads.size(); i++) {
            Advertisement ad = this.ads.get(i);
            String myUser = ad.getOwner().getUsername();
            if (myUser.equals(currentUser.getUsername())) {
                myAds.add(ad);
            }
        }
        showAdvertisements(myAds);
    }

    public void removeAdvertisement() {
        // Current selected table index
        if (table.getSelectedRow() < 0) {
            return;
        }
        this.ads.remove(table.getSelectedRow());
        refreshMyTableData();
    }
}