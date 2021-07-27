package com.Keerthan;
import java.util.*;

public class PasswordCoding {

   public String getEncodedString(String password){
       return Base64.getEncoder().encodeToString(password.getBytes());
   }
   public String getDecodedString(String password){
       return new String (Base64.getMimeDecoder().decode(password));
   }
}
