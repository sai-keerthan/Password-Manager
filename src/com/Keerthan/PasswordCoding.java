package com.Keerthan;
import java.util.*;

public class PasswordCoding {

    public int getRandomNumberString() {
        Random rnd = new Random();
        int number = rnd.nextInt(9999);

        return number;
    }

   public String getEncodedString(String password){
       return Base64.getEncoder().encodeToString(password.getBytes());
   }
   public String getDecodedString(String password){
       return new String (Base64.getMimeDecoder().decode(password));
   }
}
