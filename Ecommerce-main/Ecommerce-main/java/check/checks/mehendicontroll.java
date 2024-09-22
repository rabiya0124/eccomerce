package check.checks;

import check.checks.Model.Orders;
import check.checks.Model.cart;
import check.checks.Model.pojo;
import check.checks.Service.Adminservice;
import check.checks.Service.Service;
import check.checks.Service.cartservice;
import jakarta.persistence.Access;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class mehendicontroll {


    @Autowired
    cartservice cartservice;
    @Autowired
    Adminservice adminservice;
    @Autowired
    Service service;

   public static String sessionid;

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    @GetMapping("/")

    public String res(Model model) {

        if (cartservice.items()!=null)
            model.addAttribute("items",cartservice.items());
        else
            model.addAttribute("items",0);

        return "indexhome";
    }

    @GetMapping("/contact")

    public String contact(Model model) {
        if (cartservice.items()!=null)
            model.addAttribute("items",cartservice.items());
        else
            model.addAttribute("items",0);

        return "contact";
    }

    @GetMapping("/shop")
    public String shop(Model model) {
if (cartservice.items()!=null)
    model.addAttribute("items",cartservice.items());
else
    model.addAttribute("items",0);

return "shop";
    }

    @GetMapping("/about")
    public String about(Model model) {
        if (cartservice.items()!=null)
            model.addAttribute("items",cartservice.items());
        else
            model.addAttribute("items",0);


        return "about";
    }

    @GetMapping("/cart")
    public String cart(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(username);
        model.addAttribute("cartdetails", cartservice.fetchcart(username));
if(cartservice.items()!=null){
        model.addAttribute("total", cartservice.totalamount());
        model.addAttribute("items", cartservice.items());
}
else {
    model.addAttribute("total", 0);
    model.addAttribute("items", 0);
}   return "cart";
    }

    @GetMapping("/package")
    public String packages(Model model) {
        if (cartservice.items()!=null)
            model.addAttribute("items",cartservice.items());
        else
            model.addAttribute("items",0);


        return "package";
    }

    @GetMapping("/productsingle")
    public String productsingle() {
        return "product-single";
    }

    @GetMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute("total", cartservice.totalamount());
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().startsWith("anonymous"))
            model.addAttribute("logged",1);
        if(adminservice.isverified().equals("NO"))
            model.addAttribute("verify",1);
        if (cartservice.items()!=null)
            model.addAttribute("items",cartservice.items());
        else
            model.addAttribute("items",0);
        return "checkout";
    }

    @GetMapping("/blog")
    public String blog() {
        return "blog";
    }

    @PostMapping("/addtocart")

    public @ResponseBody ResponseEntity<String> addtocart(tempcart tempcart) {
        System.out.println(tempcart.toString());

       String check= cartservice.addtocart(tempcart);

       if (!check.equalsIgnoreCase("successful"))
           return ResponseEntity.badRequest().body(check);
       return ResponseEntity.ok(cartservice.items().toString());

    }

    @PostMapping("/")
    public String contactus(@ModelAttribute temp temp, Model model) {

        adminservice.sendEmail(temp.getEmailid(), "THANK-YOU FOR CONTACTING BITTU MEHENDI", "To Know More about our" +
                " Services and Feedback kindly call us on 9044,  24X7, Thank You, Team BITTU");

        model.addAttribute("success", "YES");
        model.addAttribute("successMessage", "We Will Contact You Shortly, THANK YOU");
        return "indexhome";

    }

@PostMapping("/deletecart")
    public @ResponseBody delete deletecart(delete delete ) {
        System.out.println(delete.toString());
        //System.out.println(deletes.toString()+);
   //     System.out.println(delete.getUsername() + delete.getName());
        cartservice.cartdelete(delete);
        return delete;
    }

    @GetMapping("/myaccount")
    public String myaccount(Model model) {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() == "anonymousUser") {
            return "redirect:/";
        }
     List<Orders> k=   service.fetchuserorders();
        model.addAttribute("user",service.fetch()[0]);
        model.addAttribute("userorders",k);
        return "myaccount";
    }

    @GetMapping("/terms")
    public String terms(Model model){

        if (cartservice.items()!=null)
            model.addAttribute("items",cartservice.items());
        else
            model.addAttribute("items",0);

        return "terms";
    }

    @GetMapping("/privacy")
    public String privacy(Model model) {
        if (cartservice.items()!=null)
            model.addAttribute("items",cartservice.items());
        else
            model.addAttribute("items",0);

        return "privacy";
    }
@GetMapping("/faq")
    public String faq(Model model)
{
    if (cartservice.items()!=null)
        model.addAttribute("items",cartservice.items());
    else
        model.addAttribute("items",0);

    return "faq";
}
@GetMapping("/cancellation")

    public String cancel(){
        return "booking&cancellation";
}
@GetMapping("/searchorder")
    public String search(){
        return "searchorders";
}
@GetMapping("/userprofile")
    public String profile(Model model){
    if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() == "anonymousUser") {
        return "redirect:/";
    }
    String[] a =service.fetch();
    model.addAttribute("name",a[0]);
    model.addAttribute("emailid",a[1]);
    model.addAttribute("password","NOT SHOWN DUE TO SECURITY REASONS");
    model.addAttribute("status",adminservice.isverified());
    return "userprofile";

}
@PostMapping("/updateemailid")

    public @ResponseBody ResponseEntity<String> updateemailid(String emailid){
        System.out.println(emailid);
        boolean check=service.updateemailid(emailid);
if(check)
    return ResponseEntity.ok("UPDATED SUCCESSFULLY");
else
    return ResponseEntity.badRequest().body("EMAIL ID ALREADY IN USE");

}

@PostMapping("/updatepassword")
    public @ResponseBody ResponseEntity<String> updatepassword(String oldpassword,String newpassword){
        System.out.println(oldpassword+"."+newpassword);
        if(service.updatepassword(oldpassword,newpassword))
            return ResponseEntity.ok("Password Changed Successfully");
        else return ResponseEntity.badRequest().body("Incorrect Password");

}
@PostMapping("/updatename")
    public @ResponseBody ResponseEntity<String> updatename(String name){
        System.out.println(name);
       if(service.updatename(name))
           return ResponseEntity.ok("Successfully Changed");
       return ResponseEntity.badRequest().body("Internal Error, Try Again");

}
@PostMapping("/updatecart")
public  String updatecart(@ModelAttribute updatecart updatecart){

        System.out.println(updatecart.toString());
        cartservice.updatecart(Integer.valueOf(updatecart.getMehendi()),updatecart.getUsername(),updatecart.getName());
        return "redirect:/cart";
    }


public void sessionid(HttpSession httpSession){


}
@GetMapping("/orderisplaced")
public String orderplaced(){
            service.orderplaced();
    return "okay";
        }

@GetMapping("/booking")
    public String booking(@RequestParam String name){
System.out.println(name);
        return "book";
}

@GetMapping("/3Dbridal")
    public String t3dbridal(){
        return "3D bridal";
    }
@GetMapping("/3D Leg")
public String t3dleg(){
        return "3D leg";
}

@GetMapping("/arabicstyle")
    public String arabicstyle(){
        return "arabicstyle";
}
@GetMapping("/babyshower")
    public String babyshower(){
        return "baby shower";
}
@GetMapping("/bridal")
    public String bridal(){
        return "bridal";
}
@GetMapping("/elegantbridal")
    public String eleganbridal(){
        return "elegant Bridal";
}
}



