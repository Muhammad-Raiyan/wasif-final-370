package application.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public List<String> searchForItem(String item){
        if(item == null)
            return null;
        if(searchItemHistory.containsKey(item)){
            return regexMatchHtml(searchItemHistory.get(item));
        }

        String searchUrl = searchUrlBase + item.trim();
        String result = getHtmlContentAsString(searchUrl);
        searchItemHistory.put(item, result);
        List<String> productList = regexMatchHtml(result);
        System.out.println(productList.size());

        return productList;
    }

    public List<String> regexMatchHtml(String context){
        System.out.println(context);
        final String regex = "<div class=\\\"product-image-container\\\">(.*?)<\\/div>";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(context);

        List<String> res = new ArrayList<>();
        while (matcher.find()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                res.add(matcher.group(i));
            }
        }
        return res;
    }


    private String getHtmlContentAsString(String searchUrl) {
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
