package com.company;

import models.Advertisement;
import models.User;
import models.UserType;
import screens.LoginScreen;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {


        User pana = new User("PanaAtanasov", "12345678", "Panayot Atanasov", 33, "Varna", "male", "0888214819284");
        pana.setUserType(UserType.ADMIN);

        User evgeni = new User("EvgeniAndreev", "12345678", "Evgeni Andreev", 35, "Varna", "male", "09999929292");
        evgeni.setUserType(UserType.REGULAR);



        ArrayList<User> users = new ArrayList<>();
        ArrayList<Advertisement> ads = new ArrayList<>();
        users.add(pana);
        users.add(evgeni);
        ads.add(new Advertisement("HONDA", "Civic", "200000", 5000, evgeni));
        Advertisement ad1 = new Advertisement("Toyota", "Avensis", "198000", 25000, pana);
        ads.add(ad1);
        ads.add(new Advertisement("Suzuki", "Swift", "24000", 15000, evgeni));

        //StartUp Window (Register or Login)

        LoginScreen loginScreen = new LoginScreen(users, ads);
        loginScreen.setVisible(true);
    }
}
