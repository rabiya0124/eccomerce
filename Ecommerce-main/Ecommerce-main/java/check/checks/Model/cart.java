package check.checks.Model;

import jakarta.persistence.*;

@Entity
public class cart {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @Id
   public Integer id;
@Column
   public Integer itemsincart;
@Column
public String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column
    public String name;

@Column
    public Double Price;

@Column
    public Integer quantity;
@Column
    public Double totalamount;

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    @Column
public String picurl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getItemsincart() {
        return itemsincart;
    }

    public void setItemsincart(Integer itemsincart) {
        this.itemsincart = itemsincart;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(Double totalamount) {
        this.totalamount = totalamount;
    }
}
