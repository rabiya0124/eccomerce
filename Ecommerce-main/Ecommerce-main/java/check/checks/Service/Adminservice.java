package check.checks.Service;

import check.checks.Model.Orders;
import check.checks.Model.adminadd;
import check.checks.Model.cart;
import check.checks.Model.pojo;
import check.checks.Repositories.*;
import check.checks.orderdetails;
import check.checks.productadd;
import check.checks.razorpaysuccess;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;
import jakarta.transaction.Transactional;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class Adminservice {
@Autowired
cartcrud cartcrud;
    @Autowired
     JavaMailSender mailSender;
    @Autowired
    crud1 crud1;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    crud2 crud2;
    @Autowired
    crud crud;
    public Integer daily;
    public Integer monthly;

    public Double totalrevenue;

    public Double monthlyrevenue;

    Random random =new Random();


    public String addproduct(productadd productadd) {

        adminadd adminadd = new adminadd();
        adminadd.setName(productadd.getName());
        adminadd.setAmount(productadd.getAmount());
        adminadd.setProductid(productadd.getProductid());

        crud1.save(adminadd);
        return "Successfully Added Product";


    }

    public String deleteproduct(String product) {

        adminadd add = crud1.findByName(product);
        if (add == null)
            return "Already DELETED";
        else {
            crud1.delete(crud1.findByName(product));
            return "SUCCESFULLY DELETED";

        }
    }
    public  String updateproduct(productadd productadd){

        if(crud1.findByName(productadd.getName())==null)
            return "ITEM DOES NOT EXIST,KINDLY ADD IT";
        else
        {
            adminadd update= crud1.findByName(productadd.getName());
            update.setAmount(productadd.getAmount());
            update.setName(productadd.getName());
            return "ITEM SUCCESSFULLY UPDATED";

        }


}
public List allproducts(){

       return crud1.findAll();
    }


public List<Orders> recentorders(){

    System.out.println("ANSWER ="+ crud2.recentorders());
    return crud2.recentorders();
    }


    public orderdetails fetchdetails() {

        orderdetails x = new orderdetails();
        x.setTotalrevenue(crud2.findtotalrevenue());
        x.setMonthlyorders(crud2.findtotalordermonthly());
        x.setMonthlyrevenue(crud2.findmonthlyrevenue());
        // x.setMonthlyorders(dx.findtotalordermonthly());
        x.setDailyorders(crud2.findtotalordersdaily());
        //      x.setTotalrevenue(dx.findtotalrevenue());

        return x;
    }

    public Integer dailyorder(){
        daily=fetchdetails().getDailyorders();

        System.out.println(daily);
        return daily;
    }
    public Integer monthlyorder(){

        monthly= fetchdetails().getMonthlyorders();
        return monthly;


    }
    public Double totalrevenue(){

        totalrevenue=fetchdetails().getTotalrevenue();
        return totalrevenue;
    }
    public Double monthlyrevenue(){

        monthlyrevenue= fetchdetails().getMonthlyrevenue();
        return monthlyrevenue;
    }

    public List<Orders> allorders(){

        return crud2.allorders();
    }
    public String resetlink(String a) {
System.out.println(a+" DEKH RAHE HO BINDO");

random=new Random();
        String b= UUID.randomUUID().toString();
        pojo s=crud.findByEmailid(a);
        if(s==null) return "NO";
        s.setPassword(passwordEncoder.encode(b.substring(0,10)));
        crud.save(s);
        sendEmail(s.getRole(), "REQUEST FOR NEW PASSWORD", "Hello "+s.getUsername() +" , YOUR NEW PASSWORD IS: "+b.substring(0,10)+
                "\n Kindly Login with this Password and Change it in your Profile Menu");
        return "YES";
    }



    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("vishnuverma284@gmail.com"); // Sender email address
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

    public void generateotp(String name){

        Integer x= random.nextInt(100000,999999);
        pojo k= crud.findByEmailid(name);
        k.setUserotp(x);
        crud.save(k);
        sendEmail(name,"EMAIL-ID VERIFICATION","Hello "+k.getUsername()+" !!!, Your OTP (One Time Password) is" +
               " "+ x.toString()+" valid for 15 Minutes only, TEAM- BITTU");

    }
    public String otpverification(String otp){
        Integer x;pojo k;
        try {
             x = Integer.parseInt(otp);
        }
        catch(Exception e) {

            return "Invalid OTP";
        }
       if(crud.findByEmailid(SecurityContextHolder.getContext().getAuthentication().getName()).getUserotp().equals(x))
       {   k=crud.findByEmailid((SecurityContextHolder.getContext().getAuthentication().getName()));
          k.setUserverfied(true);
       crud.save(k);
           return "YES";}
       else
           return "NO";
    }
    public String username(){
       return crud.findByEmailid(SecurityContextHolder.getContext().getAuthentication().getName()).getUsername();
    }

public String isverified(){

        if(crud.findByEmailid(SecurityContextHolder.getContext().getAuthentication().getName()).isUserverfied())
            return "YES";
        else return "NO";
}
@Transactional
public boolean signatureverification(razorpaysuccess razorpaysuccess) throws RazorpayException {

// RazorpayClient  razorpay = new RazorpayClient("rzp_live_EoOQHJ1XLDYchs", "LwER3JQ60smYnAQ8A93r0Qbd");
    System.out.println(razorpaysuccess.toString());
    String secret = "uQuVu7VqhUNgsoVnsiFyh8yn";

    JSONObject options = new JSONObject();
    options.put("razorpay_order_id", razorpaysuccess.getRazorpay_order_id());
    options.put("razorpay_payment_id", razorpaysuccess.getRazorpay_payment_id());
    options.put("razorpay_signature", razorpaysuccess.getRazorpay_signature());

    if (Utils.verifyPaymentSignature(options, secret)) {
//Orders x=new Orders();
//x.setOrdernumber(Integer.parseInt(razorpaysuccess.getRazorpay_order_id()));
//x.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
//x.setOrderamount();
        List<cart> cart = cartcrud.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Orders> orders = new ArrayList<>();
        for (cart each : cart) {
            orders.add(new Orders(each.getUsername(), each.getName(), razorpaysuccess.getRazorpay_payment_id(),
                    razorpaysuccess.getRazorpay_order_id(), each.getQuantity(), each.getPicurl(), each.getTotalamount()));
        }
        cartcrud.deleteByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        crud2.saveAll(orders);
        sendEmail(SecurityContextHolder.getContext().getAuthentication().getName(), "PAYMENT RECEIVED !!!", "Thankyou for" +
                "Shopping With Bittu Menedi Arts, Your Order ID is " + razorpaysuccess.getRazorpay_order_id());
        return true;
//public String[] fetchordersuccess(){
        //      crud2.findByOrderid()

    }
    return false;
}}
