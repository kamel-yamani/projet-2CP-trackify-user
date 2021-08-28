package com.example.driverapp.Utils;

import java.util.ArrayList;

public final class InputContol {

    private static boolean containOnlyDegits(String phoneNumber) {
        String NUMS = "0123456789";
        for (int i = 0; i < phoneNumber.length(); i++) {
            if (!NUMS.contains("" + phoneNumber.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static ArrayList<String> controlPhoneNumber(String phoneNumber) {
        String[] messages = { "Phone number must start by 0", "Phone number must contain 10 degits",
                "Phone number must contain degits only", "this field cannot be blanc" };
        ArrayList<String> errorMessages = new ArrayList<String>();

        if (phoneNumber.length() != 10) {
            errorMessages.add(messages[1]);
        }
        if (phoneNumber.length()>0) {
            if (phoneNumber.charAt(0) != '0') {
                errorMessages.add(messages[0]);
            }
        }
        if (!containOnlyDegits(phoneNumber)) {
            errorMessages.add(messages[2]);
        }
        if(phoneNumber.length()==0){
            errorMessages.add(messages[3]);
        }
        return errorMessages;
    }

    public static String displayPhoneNumberErrors(String phoneNumber) {
        ArrayList<String> errs = controlPhoneNumber(phoneNumber);
        String all_errors= "";
        for (String string : errs) {
            all_errors += string + "\n";
        }
        return all_errors;
    }

    private static boolean contain2Nums(String nums, String privateCode) {
        short cpt = 0;
        for (int i = 0; i < privateCode.length(); i++) {
            if (nums.contains("" + privateCode.charAt(i)))
                cpt++;
        }
        return (cpt >= 2);
    }

    private static boolean contain2SpecialCharacters(String spChar, String privateCode) {
        short cpt = 0;
        for (int i = 0; i < privateCode.length(); i++) {
            if (spChar.contains("" + privateCode.charAt(i)))
                cpt++;
        }
        return (cpt >= 2);
    }

    private static boolean contains2Uppercase(String uppers, String privateCode) {
        short cpt = 0;
        for (int i = 0; i < privateCode.length(); i++) {
            if (uppers.contains("" + privateCode.charAt(i)))
                cpt++;
        }
        return (cpt >= 2);
    }

    public static ArrayList<String> controlPrivateCode(String privateCode) {
        String UPPERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String NUMS = "0123456789";
        String SPCHAR = " !\"#$%&'()*+,-./:;<=>?@[]^_`{|}~";

        String[] messages = { "Private code is too short, enter at least 8 characters",
                "Private code should contain at least 2 digits",
                "Private code should contain at least 2 special characters",
                "Private code should contain at least 2 Uppercase characters " };
        ArrayList<String> errorMessages = new ArrayList<String>();

        if (privateCode.length() < 8)
            errorMessages.add(messages[0]);
        if (!contain2Nums(NUMS, privateCode))
            errorMessages.add(messages[1]);
        if (!contain2SpecialCharacters(SPCHAR, privateCode))
            errorMessages.add(messages[2]);
        if (!contains2Uppercase(UPPERS, privateCode))
            errorMessages.add(messages[3]);

        return errorMessages;
    }

    public static String displayPrivateCodeErrors(String privateCode) {
        ArrayList<String> errs = controlPrivateCode(privateCode);
        String allErrors = "";
        for (String string : errs) {
            allErrors += string + "\n";
        }
        return allErrors;

    }
}
