package application.service;

/**
 * @author myname
 */
public class UserService {

    public static boolean isValidUserName(String userName){
        if(userName == null || userName.length()==0){
            return false;
        }
        return true;
    }

    public static boolean isValidEmail(String email){
        if(email == null || email.length() == 0)
            return false;
        return true;
    }
    public static boolean isValidPassword(String password){
        if(password == null || password.length()==0)
            return false;
        return true;
    }
    public static boolean userDataValidation(String userName, String email, String password){

        return isValidUserName(userName) && isValidEmail(email) && isValidPassword(password);
    }
}
