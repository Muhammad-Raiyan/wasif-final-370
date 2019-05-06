package application.Model;

import javafx.beans.property.SimpleStringProperty;

/**
 * @author myname
 */
public class Product {
    private SimpleStringProperty name;
    private SimpleStringProperty price;
    private SimpleStringProperty imageUrl;
    private SimpleStringProperty url;



    public Product(String name, String price, String imageUrl, String url) {
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleStringProperty(price);
        this.imageUrl = new SimpleStringProperty(imageUrl);
        this.url = new SimpleStringProperty(url);
    }


    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getPrice() {
        return price.get();
    }

    public void setPrice(String price) {
        this.price.set(price);
    }

    public String getImageUrl() {
        return imageUrl.get();
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl.set(imageUrl);
    }

    public String getUrl() {
        return url.get();
    }

    public void setUrl(String url) {
        this.url.set(url);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
