package check.checks.Model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Orders {


    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @Id
  public  Integer sno;

    @Column
  public  String username;
    @Column
  public  String productname;

    @Column
    public String paymentid;

    @Column
   public String orderid;
@Column
public Integer quantity;

@Column
public String url;


    @Column
   public Double Orderamount;

    @CreationTimestamp
   public  LocalDateTime orderdatetime;

    public Integer getSno() {
        return sno;
    }

    public void setSno(Integer sno) {
        this.sno = sno;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getPaymentid() {
        return paymentid;
    }

    public void setPaymentid(String paymentid) {
        this.paymentid = paymentid;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Double getOrderamount() {
        return Orderamount;
    }

    public void setOrderamount(Double orderamount) {
        Orderamount = orderamount;
    }

    public LocalDate getOrderdatetime() {
        return orderdatetime.toLocalDate();
    }

    public void setOrderdatetime(LocalDateTime orderdatetime) {
        this.orderdatetime = orderdatetime;
    }

    public Orders(String username, String productname, String paymentid, String orderid, Integer quantity, String url, Double orderamount) {
        this.username = username;
        this.productname = productname;
        this.paymentid = paymentid;
        this.orderid = orderid;
        this.quantity = quantity;
        this.url = url;
        Orderamount = orderamount;
    }

    public Orders(){}
}


