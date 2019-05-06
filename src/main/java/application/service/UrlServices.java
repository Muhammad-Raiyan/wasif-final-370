package application.service;

import application.Model.Product;

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

    private Map<String, List<Product>> searchItemHistory;

    private UrlServices(){
        searchItemHistory = new HashMap<>();
    }

    public static UrlServices getInstance(){
        if(urlServices==null){
            urlServices = new UrlServices();
        }
        return urlServices;
    }

    public List<Product> searchForItem(String item){
        if(item == null)
            return null;
        if(searchItemHistory.containsKey(item)){
            return searchItemHistory.get(item);
        }

        String searchUrl = searchUrlBase + item.trim();
        String htmlString = getHtmlContentAsString(searchUrl);

        List<String> productUrlList = regexMatchHtml(htmlString, productUrlRegex);
        List<String> productNameList = regexMatchHtml(htmlString, titleRegex);
        List<String> productPriceList = regexMatchHtml(htmlString, priceRegex);

        List<Product> productList = new ArrayList<>();
        for(int i=0; i<productNameList.size() ; i++){
            String productName = productNameList.get(i);
            String productPrice = productPriceList.size() > i ? productPriceList.get(i) : null;
            String productUrl = productUrlList.size() > i ? productUrlList.get(i) : null;
            if(productPrice == null || productUrl == null)
                break;
            Product product = new Product(productName, productPrice.trim().replace("$", ""), productUrl, productUrl);
            productList.add(product);
        }
        return productList;
    }

    public List<String> regexMatchHtml(String context, String regex){
        System.out.println(context);
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
