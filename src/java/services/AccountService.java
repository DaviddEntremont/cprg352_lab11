package services;

import dataaccess.UserDB;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.User;

public class AccountService {
    
    public User login(String email, String password, String path) {
        UserDB userDB = new UserDB();
        
        try {
            User user = userDB.get(email);
            if (password.equals(user.getPassword())) {
                Logger.getLogger(AccountService.class.getName()).log(Level.INFO, "Successful login by {0}", email);
                
                //Simple bottom text email
                //GmailService.sendMail(email, "New Login to Notes App",  "User has logged in... Bottom text", false);

                String to = user.getEmail();
                String subject = "Notes App Login";
                String template = path + "/emailtemplates/login.html";
                
                HashMap<String, String> tags = new HashMap<>();
                tags.put("firstname", user.getFirstName());
                tags.put("lastname", user.getLastName());
                tags.put("date", (new java.util.Date()).toString());
                
                GmailService.sendMail(to, subject, template, tags);

                return user;
            }
        } catch (Exception e) {
        }
        
        return null;
    }
    
    public User resetPassword(String email, String path, String url) {
        //create a uuid (universally unique id) and store in the database for the user
               
        UserDB userDB = new UserDB();
        
        try {   
                //Get user and set a new UUID
                User user = userDB.get(email);             
                String uuid = UUID.randomUUID().toString();               
                user.setResetPasswordUuid(uuid);
                userDB.update(user);
        
                String link = url + "?uuid=" + uuid;
                
                String to = user.getEmail();
                String subject = "Reset Password";
                String template = path + "/emailtemplates/resetpassword.html";
                
                HashMap<String, String> tags = new HashMap<>();
                tags.put("firstname", user.getFirstName());
                tags.put("lastname", user.getLastName());
                tags.put("link", link);
                
                GmailService.sendMail(to, subject, template, tags);

                return user;
            }
            
             catch (Exception e) {
        }
        
        return null;
    }
    
    public boolean changePassword(String uuid, String password) {
        UserDB userDB = new UserDB();
        try { 
            User user = userDB.getByUUID(uuid);
            user.setResetPasswordUuid(null);
            user.setPassword(password);
            user.setResetPasswordUuid(null);
            userDB.update(user);
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }
}
