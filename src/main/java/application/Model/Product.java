package application.Model;

/**
 * @author myname
 */
public class Product {
    private String name;
    private double price;
    private String imageUrl;
    private String url;

    public Product(String name, int price, String imageUrl, String url) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.url = url;
    }

    public Product(String name, String price, String imageUrl, String url) {
        this.name = name;
        this.price = Double.valueOf(price);
        this.imageUrl = imageUrl;
        this.url = url;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
