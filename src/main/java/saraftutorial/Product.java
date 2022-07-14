package saraftutorial;

import java.util.Date;

public class Product {
    private int id;
    private String movieName;
    private String releaseDate;
    private double price;
    private String image;

    public Product() {
    }

    public Product(int id, String movieName, String releaseDate, double price, String image) {
        this.id = id;
        this.movieName = movieName;
        this.releaseDate = releaseDate;
        this.price = price;
        this.image = image;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Product [" +
                "id=" + id +
                ", movieName='" + movieName + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", price='" + price + '\'' +
                ", image='" + image + '\'' +
                ']';
    }
}
