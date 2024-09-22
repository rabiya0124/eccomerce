package check.checks.Service;

import check.checks.Model.cart;
import check.checks.Repositories.cartcrud;
import check.checks.delete;
import check.checks.mehendicontroll;
import check.checks.tempcart;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class cartservice {


    @Autowired
    cartcrud cartcrud;
mehendicontroll mehendicontroll=new mehendicontroll();

    public List<cart> fetchcart(String username){

      return  cartcrud.findByUsername(username);


    }
    public String addtocart(tempcart tempcart){
cart x=new cart(); String temp;
if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().startsWith("anonymous")){
    temp="anonymous"+mehendicontroll.getSessionid();
    System.out.println(temp);
    if (cartcrud.findByUsernameAndName(temp, tempcart.getProduct())==null) {
        x.setName(tempcart.getProduct());
        x.setPrice(tempcart.getPrice());
        x.setPicurl(tempcart.getImage());
        x.setTotalamount(tempcart.getPrice());
        x.setQuantity(1);
        x.setUsername(temp);
    }
    else{
        cart y=cartcrud.findByUsernameAndName(temp, tempcart.getProduct());
        if(y.getQuantity()==10) return y.getName() +" Exceeded More than 10";
        y.setQuantity(y.getQuantity()+1);
        y.setTotalamount(y.getTotalamount()+y.getPrice());
        cartcrud.save(y);
    }
    cartcrud.save(x);
return "Successful";
}
String b=SecurityContextHolder.getContext().getAuthentication().getName();
if (cartcrud.findByUsernameAndName(b, tempcart.getProduct())==null) {
    x.setName(tempcart.getProduct());
    x.setPrice(tempcart.getPrice());
    x.setPicurl(tempcart.getImage());
    x.setTotalamount(tempcart.getPrice());
    x.setQuantity(1);
    x.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
}
else{
    cart y=cartcrud.findByUsernameAndName(b, tempcart.getProduct());
    if(y.getQuantity()==10) return y.getName() +" Exceeded More than 10";
    y.setQuantity(y.getQuantity()+1);
    y.setTotalamount(y.getTotalamount()+y.getPrice());
    cartcrud.save(y);
}
cartcrud.save(x);
return "SUCCESSFUL";
//cartcrud.save()
    }
    @Transactional
public void cartdelete(delete delete){
System.out.println(delete.toString());
        String username=delete.getUsername();
        String name=delete.getName();
            cartcrud.deleteByUsernameAndName(username,name);

        

}
public double totalamount(){

        return cartcrud.totalamount(SecurityContextHolder.getContext().getAuthentication().getName());

}
public Integer items(){

String temp;
    if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().startsWith("anonymous")) {
        temp = "anonymous" + mehendicontroll.getSessionid();
        cartcrud.findByUsername(temp);
    }
        return cartcrud.items(SecurityContextHolder.getContext().getAuthentication().getName());
}
public String updatecart(Integer quantity,String username,String name){
        cart x=new cart();
       x= cartcrud.findByUsernameAndName(username,name);
       x.setQuantity(quantity);
 x.setTotalamount(x.getPrice()*x.getQuantity());
 cartcrud.save(x);
       return "DONE";
}
}
