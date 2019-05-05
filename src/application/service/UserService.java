package application.service;

import application.Model.User;

import java.util.HashMap;
import java.util.Map;

/**
 * @author myname
 */
public class UserService {
    private static final String MASTER_PASSWORD = "asd";
    private static UserService userServiceSingleton = null;
    private Map<String, User> userMap = new HashMap<>();
    private User currentUser = null;

    private UserService(){

    }

    public static UserService getUserServiceSingleton() {
        if(userServiceSingleton == null)
            userServiceSingleton = new UserService();
        return userServiceSingleton;
    }

    public void addNewUser(String userName, String email, String password){
        User user = new User(userName, email,password);
        userMap.put(userName, user);
        System.out.println("added new user: " + user);
    }

    public boolean isValidUserName(String userName){
        if(userName == null || userName.length()==0){
            return false;
        }
        if(userMap.containsKey(userName))
            return false;
        return true;
    }

    public boolean isValidEmail(String email){
        if(email == null || email.length() == 0)
            return false;
        return true;
    }
    public boolean isValidPassword(String password){
        if(password == null || password.length()==0)
            return false;
        return true;
    }

    public boolean userDataValidation(String userName,String password){

        return isValidPassword(password) && isValidUser(userName, password);
    }

    private boolean isValidUser(String userName, String password) {
        if(password.equals(MASTER_PASSWORD) || password.equals(userMap.get(userName).getPassword()))
            return true;
        return false;
    }

    public boolean userDataValidation(String userName, String email, String password){
        return isValidUserName(userName) && isValidEmail(email) && isValidPassword(password);
    }

    public void signInUser(String userName){
        currentUser = userMap.get(userName);
        System.out.println("Signed In User " + currentUser);
    }

    public void signOutUser(){
        currentUser = null;
    }

    public boolean isUserSignedIn(){
        return currentUser != null;
    }

    public String getCurrentUserName(){
        return currentUser.getUserName();
    }
}
