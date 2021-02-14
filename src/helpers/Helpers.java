package helpers;

import models.User;
import models.UserType;

import javax.swing.*;

public class Helpers {

    public static void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Грешка", JOptionPane.ERROR_MESSAGE);
    }

    public static boolean isValidAge(String age) {
        try {
            int validAge = Integer.parseInt(age);
            return validAge >= 18 && validAge < 100;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isValidPrice(String prise) {
        try {
            double validPrise = Double.parseDouble(prise);
            return validPrise > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isAdminUser(User currentUser) {
        return (currentUser.getUserType() == UserType.ADMIN);
    }
}
