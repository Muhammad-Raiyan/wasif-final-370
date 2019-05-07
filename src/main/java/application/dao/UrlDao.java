package application.dao;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author myname
 */
public class UrlDao {

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
