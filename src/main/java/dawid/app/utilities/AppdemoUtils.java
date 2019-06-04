package dawid.app.utilities;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppdemoUtils {

    public static boolean checkEmailOrPassword(String pattern, String pStr) {

        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(pStr);
        return m.matches();
    }

    /**
     * Generator losowych łańcuchów znaków
     *
     * @return String
     */
    public static String randomStringGenerator() {
        StringBuilder randomString = new StringBuilder();

        String signs = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

        Random rnd = new Random();

        for (int i = 0; i < 32; i++) {
            int liczba = rnd.nextInt(signs.length());
            randomString.append(signs, liczba, liczba + 1);
        }
        return randomString.toString();
    }

}
