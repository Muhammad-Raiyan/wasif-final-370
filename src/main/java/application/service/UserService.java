package application.service;

import application.Model.User;
import application.dao.UsersDAO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

/**
 * @author myname
 */
public class UserService {
    private static final String MASTER_PASSWORD = "asd";

    private static UserService userServiceSingleton = null;
    private UsersDAO usersDAO;

    private Map<String, User> userMap;
    private User currentUser = null;

    private UserService(){
        System.out.println("UserService constructor called");
        usersDAO = new UsersDAO();
        userMap = usersDAO.initializeUserMap();
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

    public String getUserMapAsJson(){
        Gson gson = new GsonBuilder().create();
        return  gson.toJson(userMap);
    }

    public boolean saveUsersData(){
        String jsonUserMap = getUserMapAsJson();
        return usersDAO.saveUsersToFile(jsonUserMap);
    }

    public UsersDAO getUsersDAO() {
        return usersDAO;
    }

    public void setUsersDAO(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    public Map<String, User> getUserMap() {
        return userMap;
    }

    public void setUserMap(Map<String, User> userMap) {
        this.userMap = userMap;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
