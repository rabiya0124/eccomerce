package check.checks.Model;

import jakarta.persistence.*;

@Entity
public class adminadd {

    @GeneratedValue (strategy = GenerationType.AUTO)
    @Id
    @Column
    Integer sno;

    Integer productid;
    @Column
    String name;

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Column
    Double amount;



}
