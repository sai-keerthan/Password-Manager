package com.Keerthan;

public class MasterPassword  {
    public String mpwd;
    public boolean status;

    public void inputmpwd(String mpwd) {
        if(mpwd.equals(null)){
            System.out.println("No Password Entered.");
        }
        else{
            this.mpwd=mpwd;
        }
    }
    public boolean verifympwd(){
        if (!mpwd.equals("Tiger")) {
            System.out.println("Sorry! Wrong Password.");
            status=false;

        } else {
            System.out.println("Login Successful!");
            status= true;
        }
        return status;
    }

}
