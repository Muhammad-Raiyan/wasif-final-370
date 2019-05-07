package application.dao;

import application.Model.Product;
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
import java.util.List;
import java.util.Map;

/**
 * @author myname
 */
public class UrlDao {

    public Map<String, List<Product>> initializeHistoryMap(){
        Map<String, List<Product>> historyMap = loadHistoryFromFile();
        if(historyMap == null){
            return new HashMap<>();
        }
        return historyMap;
    }
    private Map<String, List<Product>> loadHistoryFromFile(){
        Map<String, List<Product>> searchItemHistory = null;
        try(FileReader fileReader = new FileReader("history.json")){
            JsonReader reader = new JsonReader(fileReader);
            Gson gson = new GsonBuilder().create();
            Type typeOfHashMap = new TypeToken<Map<String, List<Product>>>(){}.getType();
            searchItemHistory = gson.fromJson(reader, typeOfHashMap);
            System.out.println(searchItemHistory.size());
        } catch (IOException e) {
            System.out.println("users.json doesn't exist and will be created at application ext");
        }
        return searchItemHistory;
    }
    public boolean saveToFile(String jsonUserMap) {
        try(FileWriter file = new FileWriter("history.json")) {
            file.write(jsonUserMap);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
