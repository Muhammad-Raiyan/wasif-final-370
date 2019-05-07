package application.service;

import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.StringReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author myname
 */
public class UrlUtils {
    private static String siteUrlBase = "https://www.nyknicksstore.com/";

    public static String unescapeHtml3( String str ) {
        try {
            HTMLDocument doc = new HTMLDocument();
            new HTMLEditorKit().read( new StringReader( "<html><body>" + str ), doc, 0 );
            return doc.getText( 1, doc.getLength() );
        } catch( Exception ex ) {
            return str;
        }
    }
    public static String getTitle(String text){
        final String regex = ".*?(title).*?(\".*?\")";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(text);
        String result = null;
        if (matcher.find()){
            String string1=matcher.group(2);
            result = unescapeHtml3(string1);
        }
        return result.substring(1, result.length()-1);
    }

    public static String getProductUrl(String text){
        final String regex = ".*?(\".*?\").*?\".*?\".*?\".*?\".*?\".*?\".*?(\".*?\")";
        String result = null;
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(text);
        if (matcher.find()){
            String string1=matcher.group(1);
            result = unescapeHtml3(string1);
        }
        return siteUrlBase + result.substring(1, result.length()-1);
    }

    public static String getImageUrl(String text){
        String re1=".*?\".*?\".*?\".*?\".*?\".*?\".*?(\".*?\")";	// Double Quote String 1
        Pattern p = Pattern.compile(re1,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher m = p.matcher(text);
        if (m.find())
        {
            String result = m.group(1);
            return "https:" + result.substring(1, result.length()-1);
        }
        return null;
    }
}
