package lk.tchk.validation;

import javax.swing.JOptionPane;
import raven.toast.Notifications;

public class validater {

    public validater() {
        init();
    }

    private void init() {

    }

    public static boolean isEmailValid(String value) {
        if (value.isBlank()) {
            Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.BOTTOM_RIGHT, 3000, "Email input can't the Empty...");
            return false;
        } else if (!value.matches(validation.EMAIL.validate())) {
            JOptionPane.showMessageDialog(null, "Please Enter valid Email Address...", "Validation Message", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public static boolean isMobileValid(String value) {
        if (value.isBlank()) {
            Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.BOTTOM_RIGHT, "Mobile input can't the Empty...");
            return false;
        } else if (!value.matches(validation.MOBILE.validate())) {
            JOptionPane.showMessageDialog(null, "Please Enter valid Mobile Number...", "Validation Message", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public static boolean isPasswordValid(String value) {
        if (value.isBlank()) {
            Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.BOTTOM_RIGHT, 3000, "Password input can't the Empty...");
            return false;
        } else if (!value.matches(validation.PASSWORD.validate())) {
            JOptionPane.showMessageDialog(
                    null,
                    "Please enter your correct password.\n\n"
                    + "Password must meet the following rules:\n"
                    + "- At least 4 characters long\n"
                    + "- At least one digit (0-9)\n"
                    + "- At least one lowercase letter (a-z)\n"
                    + "- At least one uppercase letter (A-Z)\n"
                    + "- At least one special character (@, #, $, %, ^, &, +, =)",
                    "Validation Message",
                    JOptionPane.WARNING_MESSAGE
            );

            return false;

        }

        return true;
    }

}
