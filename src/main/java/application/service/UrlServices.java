package application.service;

import application.Model.Product;
import application.dao.UrlDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

    private static final String productUrlRegex = "<div class=\\\"product-image-container\\\">(.*?)<\\/div>";
    private static final String titleRegex = "<h4 class=\\\"product-card-title\\\">(.*?)<\\/h4>";
    private static final String priceRegex = "<div class=\\\"regular-price\\\">(.*?)<\\/div>";
    private static final String productImageUrlRegex = "<div class=\"product-image-container\">(.*?)</div>";

    private Map<String, List<Product>> searchItemHistory;
    private List<Product> currentItem;
    private UrlDao urlDao;

    private UrlServices(){
        System.out.println("UrlServices constructor called");
        urlDao = new UrlDao();
        searchItemHistory = urlDao.initializeHistoryMap();
    }

    public static UrlServices getInstance(){
        if(urlServices==null){
            urlServices = new UrlServices();
        }
        return urlServices;
    }

    public void searchForItem(String item){
        if(item == null)
            return;
        if(searchItemHistory.containsKey(item)){
            currentItem = searchItemHistory.get(item);
            return;
        }

        String searchUrl = searchUrlBase + item.trim();
        String htmlString = getHtmlContentAsString(searchUrl);

        List<String> productUrlList = regexMatchHtml(htmlString, productUrlRegex);
        List<String> productNameList = regexMatchHtml(htmlString, titleRegex);
        List<String> productPriceList = regexMatchHtml(htmlString, priceRegex);
        List<String> productImageUrlList = regexGetImageUrlList(htmlString);

        List<Product> productList = new ArrayList<>();
        for(int i=0; i<productNameList.size() ; i++){
            String productName = productNameList.get(i);
            String productPrice = productPriceList.size() > i ? productPriceList.get(i) : null;
            String productUrl = productUrlList.size() > i ? productUrlList.get(i) : null;
            String productImageUrl = productImageUrlList.size()>i ? productImageUrlList.get(i):null;
            if(productPrice == null || productUrl == null)
                break;
            Product product = new Product(productName, productPrice.trim().replace("$", ""), productUrl, productImageUrl);
            productList.add(product);
        }
        searchItemHistory.put(item, productList);
        currentItem = productList;
    }

    private List<String> regexGetImageUrlList(String htmlString) {
        final Pattern pattern = Pattern.compile(productImageUrlRegex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(htmlString);

        List<String> res = new ArrayList<>();
        while (matcher.find()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                res.add(UrlUtils.getImageUrl(matcher.group(i)));
            }
        }
        return res;
    }

    public List<String> regexMatchHtml(String htmlString, String regex){
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(htmlString);

        List<String> res = new ArrayList<>();
        while (matcher.find()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                if (regex.equals(titleRegex)) {
                    res.add(UrlUtils.getTitle(matcher.group(i)));
                } else if(regex.equals(productUrlRegex)){
                    res.add(UrlUtils.getProductUrl(matcher.group(i)));
                }
                else {
                    res.add(matcher.group(i));
                }
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

    public List<Product> getCurrentProduct() {
        return currentItem;
    }

    public String getSearchHistoryAsJson(){
        Gson gson = new GsonBuilder().create();
        return  gson.toJson(searchItemHistory);
    }

    public boolean saveSearchHistoryToFile(){
        String jsonSearchHistory = getSearchHistoryAsJson();
        return  urlDao.saveToFile(jsonSearchHistory);
    }
}
