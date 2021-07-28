package com.Keerthan;

/*
 Developed by: Sai Keerthan Kasula.

 Features : ->Passwords are Encrypted.
            ->Stores your password in oracle-database.
            ->Provides a menu to store,retrieve and delete the password.
            
  Default Master-Password: Tiger
 */

import java.sql.*;
import java.util.*;
import static java.lang.System.exit;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        String Appname;
        String UserName;
        String Password;
        int id;
        System.out.println("Enter Master Password: ");
        MasterPassword mp = new MasterPassword();
        mp.inputmpwd(sc.next());
        PasswordCoding pc = new PasswordCoding();

        if (mp.verifympwd() == true) {
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "keerthan", "dbmslab");
                System.out.println("CONNECTED SUCCESSFULLY TO THE DATA BASE.");
                Statement stmt = con.createStatement();
                //ResultSet rs1 = stmt.executeQuery("create table passwordmanager(ID number,website varchar2(300),username varchar2(300),password varchar2(300), CONSTRAINT pkup PRIMARY KEY (password))");
                //System.out.println("QUERY EXECUTED!");
                while (true) {

                    System.out.println("\n1.Store a new Password");
                    System.out.println("2.View Selected Passwords.");
                    System.out.println("3.View All Passwords.");
                    System.out.println("4.Delete a Password");
                    System.out.println("5.Exit");
                    System.out.println("Enter your option: ");
                    choice = sc.nextInt();

                    switch (choice) {

                        case 1:
                            id=pc.getRandomNumberString();

                            System.out.print("Enter the name of the website/application you are using: ");
                            Appname = sc.next();

                            System.out.print("\nEnter user-name: ");
                            UserName = sc.next();

                            System.out.print("\nEnter your password: ");
                            Password = sc.next();


                            String EncryptedPassword= pc.getEncodedString(Password);

                            ResultSet result1 = stmt.executeQuery("Insert into passwordmanager (id,website,username,password) values("+id+",' " + Appname + " ',' " + UserName + " ',' " + EncryptedPassword + " ')");

                            break;
                        case 2:
                            System.out.println("Saved Names of Websites/Apps are: ");
                            ResultSet result21 = stmt.executeQuery("Select website from passwordmanager");

                            while (result21.next()) {
                                System.out.println(result21.getString(2));
                            }

                            System.out.print("Which Website/App Username and Password You Want:");
                            String wb = sc.next();



                            ResultSet result22 = stmt.executeQuery(" SELECT * from passwordmanager where website=' " + wb + " ' ");
                            while (result22.next()) {
                                System.out.print
                                        ("\nID:"+result22.getInt(1)+"\nWEBSITE:"+result22.getString(2) + "\nUSERNAME" + result22.getString(3) + "\nPASSWORD:" + pc.getDecodedString(result22.getString(4))+"\n");
                            }
                            break;
                        case 3:

                            System.out.println("SHOWING ALL PASSWORDS: ");

                            ResultSet rs3 = stmt.executeQuery("select * from passwordmanager");
                            while (rs3.next()) {
                                System.out.println("\nID:"+rs3.getInt(1)+"\nWEBSITE:"+rs3.getString(2) + "\nUSERNAME:" + rs3.getString(3) + "\nPASSWORD:" +  pc.getDecodedString(rs3.getString(4)+"\n"));
                            }
                            break;

                        case 4:
                            System.out.println("\nSaved Names of Websites/Apps are: ");
                            ResultSet result41 = stmt.executeQuery("select id,website from passwordmanager");

                            while (result41.next()) {
                                System.out.println(result41.getInt(1)+""+result41.getString(2));
                            }


                            System.out.println("\nEnter the ID of the password you want to delete: " );
                            int ch=sc.nextInt();
                            sc.nextLine();

                            PreparedStatement ps = con.prepareStatement("delete from passwordmanager where id=?");
                            ps.setInt(1,ch);
                            ps.executeUpdate();

                            //ResultSet result42 = stmt.executeQuery("delete from passwordmanager where website='"+ch+ "'");
                            System.out.println(ch+" Deleted Successfully!");
                           break;
                        case 5:
                            System.out.println("YOU SURE WANT TO EXIT (y/n):\n");
                            char c = sc.next().charAt(0);
                            if (c == 'y') {
                                System.out.println("\nNOW EXITING...");
                                exit(0);
                            } else
                                continue;
                        default:
                            System.out.println("\nSorry! You have entered wrong input.");
                            break;
                    }

                }

            } catch (Exception e) {
                System.out.println(e);
            }
        }
        else {
            exit(0);
        }

    }
}
