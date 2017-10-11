package vn.sogo.lmscms.helpers;

import org.apache.log4j.Logger;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by VinhLe on 4/15/2017.
 */
public class PasswordHelper {

    private static Logger log = Logger.getLogger(PasswordHelper.class);

    public static String ParseToMD5(String password) {
        String result = "";
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(password.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            result = number.toString(16);
        } catch (NoSuchAlgorithmException e) {
            log.error("PasswordHelper parse MD5", e);
        } finally {
            return result;
        }
    }

}
