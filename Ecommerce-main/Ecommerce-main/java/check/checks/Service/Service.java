package check.checks.Service;


import check.checks.Model.Orders;
import check.checks.Model.adminadd;
import check.checks.Model.pojo;
import check.checks.Model.timedate;
import check.checks.Repositories.crud;
import check.checks.Repositories.crud1;
import check.checks.Repositories.crud2;
import check.checks.Repositories.cartcrud;
import check.checks.local;
import check.checks.orderdetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
    crud cx;
    @Autowired Adminservice adminservice;
    @Autowired
    crud2 dx;
    @Autowired
    cartcrud cartcrud;
    @Autowired
    crud1 crud1;
    @Autowired
    crud2 crud2;
   @Autowired
    PasswordEncoder dd;
    Random random = new Random();
    int reference;

    public String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String check(local h) {
        pojo pj = new pojo();
        pj.setRole(h.getEmailid());
        String f = UUID.randomUUID().toString();
        String re = dd.encode(h.getPassword());
        //   Model model cdd=
        pj.setPassword(re);
        pj.setUsername(h.getUsername());

        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime currentDate = LocalDateTime.now();
        timedate timedate = new timedate();
  //      dx.save(timedate);

//        cx.find

        cx.save(pj);

        return "Successfully SAVED ";


    }




    public String Orderplaced(String name) {
//        adminadd adminadd;
//        if (crud1.findByName(name) == null)
//            return "This Item Does Not Exist";
//        else {
//            reference = random.nextInt(1000);
//            String username= SecurityContextHolder.getContext().getAuthentication().getName();
//
//            adminadd = crud1.findByName(name);
//          //  Orders orders = new Orders();
//            orders.setUsername(username);
//            orders.setOrderamount(adminadd.getAmount());
//            orders.setOrderid(String.valueOf(reference));
//            orders.setPaymentid(String.valueOf(adminadd.getProductid()));
//            orders.setProductname(name);
//            dx.save(orders);
        return "SUCCESSFULLY ORDER PLACED, THANK YOU , Your ORDER ID IS " + String.valueOf(reference);
    //}
    }

    public String newuser(local local){

        if(cx.findByEmailid(local.getEmailid())!=null)
            return "ERROR";

        pojo y=new pojo();

        y.setEmailid(local.getEmailid());
        y.setPassword(dd.encode(local.getPassword()));
        y.setUsername((local.getUsername()));
        y.setRole("customer");
        y.setUserverfied(false);
    cx.save(y);
return "Successful";
    }
    public String[] fetch() {
pojo x= cx.findByEmailid(SecurityContextHolder.getContext().getAuthentication().getName());
String[] a =new String[2];
a[0]=x.getUsername();
a[1]=x.getEmailid();
return a;
    }
public boolean updatename(String name){
pojo x=cx.findByEmailid(SecurityContextHolder.getContext().getAuthentication().getName());
        x.setUsername(name);
        cx.save(x);
        return true;
}

public boolean updateemailid(String emailid) {
        System.out.println("REACHED HERE");
    if (cx.findByEmailid(emailid) != null)
    { System.out.println("REACHED HERE TOO");
        return false;}
    pojo x = cx.findByEmailid(SecurityContextHolder.getContext().
            getAuthentication().getName());
    x.setEmailid(emailid);
    x.setUserverfied(false);
    cx.save(x);
    Authentication obj= SecurityContextHolder.getContext().getAuthentication();
    Object password=x.getPassword();
    UsernamePasswordAuthenticationToken upat=new UsernamePasswordAuthenticationToken(emailid,password,obj.getAuthorities());
    SecurityContextHolder.getContext().setAuthentication(upat);
    return true;
}


public boolean updatepassword(String oldpassword,String newpassword){
       //String old= dd.encode(oldpassword);
       pojo x= cx.findByEmailid(SecurityContextHolder.getContext().getAuthentication().getName());
  if(dd.matches(oldpassword, x.getPassword())){
      x.setPassword(dd.encode(newpassword));
      cx.save(x);
       return true;}
  return false;
}

public String orderplaced(){

//Integer ordernumber =random.nextInt(100000,1000000);
//while(crud2.findByOrdernumber(ordernumber)!=null){
//    ordernumber=random.nextInt(100000,1000000);}
//
//Double amount= cartcrud.totalamount(SecurityContextHolder.getContext().getAuthentication().getName());
//Orders orders=new Orders();
//orders.setOrdernumber(ordernumber);
//orders.setOrderamount(amount);
//orders.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
////orders.setProductname();
return "oa";
    }

    public List<Orders> fetchuserorders(){
       return crud2.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

    }
}
