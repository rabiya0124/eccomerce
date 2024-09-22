package check.checks;

public class tempcart {

    public String product;
    public Double price;
  //  public Integer quantity;
    public String image;

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
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
        return "tempcart{" +
                "product='" + product + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                '}';
    }
}
