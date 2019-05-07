package application.dao;

import application.Model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * @author myname
 */
public class UsersDAO {

    public Map<String, User> initializeUserMap(){
        Map<String, User> userMapFromFile = loadUsersFromFile();
        if(userMapFromFile == null){
            userMapFromFile = new HashMap<>();
            User user =  new User("admin", "admin", "asd");
            userMapFromFile.put("admin", user);
            return userMapFromFile;
        }
        return userMapFromFile;
    }

    private Map<String, User> loadUsersFromFile(){
        Map<String, User> newMap = null;

        try(FileReader fileReader = new FileReader("users.json")){
            JsonReader reader = new JsonReader(fileReader);
            Gson gson = new GsonBuilder().create();
            Type typeOfHashMap = new TypeToken<Map<String, User>>(){}.getType();
            newMap = gson.fromJson(reader, typeOfHashMap);
        } catch (IOException e) {
            System.out.println("users.json doesn't exist and will be created at application ext");
        }

        return newMap;
    }

    public boolean saveUsersToFile(String jsonUserMap) {
        try(FileWriter file = new FileWriter("users.json")) {
            file.write(jsonUserMap);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
