package com.developer.tonny.encryption;

import com.scottyab.aescrypt.AESCrypt;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;

/**
 * Created by Tonny on 8/3/2017.
 */

public class Encriptado {

    String password = "A-TIME";
    String encryptedMsg = "";

    public String encriptar(String message) {
        try {
            encryptedMsg = AESCrypt.encrypt(password, message);
        } catch (GeneralSecurityException e) {
            //handle error
        }
        return  encryptedMsg;
    }

    public String desencriptar(String message) {
        try {
            encryptedMsg = AESCrypt.decrypt(password, message);
        }catch (GeneralSecurityException e){
            //handle error - could be due to incorrect password or tampered encryptedMsg
        }
        return  encryptedMsg;
    }

    public String shaEncryption(String stringToHash)
    {
        try
        {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] result = digest.digest(stringToHash.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte b : result)
            {
                sb.append(String.format("%02X", b));
            }
            return sb.toString();
        }
        catch(Exception e)
        {
            return null;
        }
    }

}
