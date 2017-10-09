package vn.sogo.lmscms.common;

public class EditString {

    public static char[] DIGITS = {
        '0', '1', '2', '3', '4',
        '5', '6', '7', '8', '9',
    };

    public static String CHARACTERS = "0123456789AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";

    public static String htmlSanitizing(String str) {
        if (str == null) {
            return null;
        }
        String sanitized = str.replaceAll("&", "&amp;");
        sanitized = sanitized.replaceAll("<", "&lt;");
        sanitized = sanitized.replaceAll(">", "&gt;");
        sanitized = sanitized.replaceAll("\"", "&quot;");
        sanitized = sanitized.replaceAll("'", "&#39;");
        sanitized = sanitized.replaceAll(" ", "&nbsp;");
        return sanitized;
    }

    public static String trimSpace(String str) {
        if (str == null){
            return null;
        }
        return str.replaceFirst("^[\\s�@]*", "").replaceFirst("[\\s�@]*$", "");
    }

    public static String replaceChangeLine(String str) {
        if (str == null){
            return null;
        }
        String rtnStr = str.replaceAll("\r\n", "<br>");
        rtnStr = rtnStr.replaceAll("\r", "<br>");
        rtnStr = rtnStr.replaceAll("\n", "<br>");

        return  rtnStr;
    }

    public static String generateRandomNumericalString(int length) {
        StringBuffer randPasswdBuff = new StringBuffer();
        for (int i = 0 ; i < length; i++) {
            randPasswdBuff.append(DIGITS[(int)(Math.floor(Math.random() * DIGITS.length))]);
        }
        return randPasswdBuff.toString();
    }


    public static String generateRandomString(int length) {
        StringBuffer randStrBuff = new StringBuffer();
        for (int i = 0 ; i < length; i++) {
            int idx = (int)(Math.floor(Math.random() * CHARACTERS.length()));
            randStrBuff.append(CHARACTERS.charAt(idx));
        }
        return randStrBuff.toString();
    }
}
