package application.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author myname
 */
public class UrlServices {
    private static UrlServices urlServices = null;
    private static String searchUrlBase = "https://www.nyknicksstore.com/?query=";

    private Map<String, String> searchItemHistory;

    private UrlServices(){
        searchItemHistory = new HashMap<>();
    }

    public static UrlServices getInstance(){
        if(urlServices==null){
            urlServices = new UrlServices();
        }
        return urlServices;
    }

    public String searchForItem(String item){
        if(item == null)
            return null;
        if(searchItemHistory.containsKey(item)){
            return searchItemHistory.get(item);
        }

        String searchUrl = searchUrlBase + item.trim();
        String result = getHtmlContent(searchUrl);
        searchItemHistory.put(item, result);
        return result;
    }

    private String getHtmlContent(String searchUrl) {
        String target = null;
        try (BufferedReader reader = readUrl(searchUrl)){
            String line = reader.readLine();
            int maxLen = -1;
            while (line != null) {
                if(line.length() > maxLen){
                    target = line.trim();
                    maxLen = target.length();
                }
                line = reader.readLine();
            } // while
        } catch (Exception e) {
            e.printStackTrace();
        }
        return target;
    }


    private BufferedReader readUrl(String url) throws Exception {
        return new BufferedReader(
                new InputStreamReader(
                        new URL(url).openStream()));
    } // read
}
