package check.checks.Controller;

import check.checks.*;
import check.checks.Repositories.crud;
import check.checks.Service.Adminservice;
import check.checks.Service.Service;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.http.HttpResponse;
import java.util.List;

@Controller
//@RequestMapping("/mehendi")
public class hitapi {
    @Autowired

    Service x;
    Integer count = 0;
    // @Autowired
    //  jwtbuilder jwtbuilder;
    @Autowired
    Adminservice adminservice;
    @Autowired
    crud crud;
    @Autowired
    razor razor;

    @Autowired
    AuthenticationManager authenticationManager;
    // @Autowired
    //  check.checks.jwtbuilder jwtbuilder;

    //  @GetMapping("/")
    //    public String we() {
    //  return "indexhome";
    //}
    @GetMapping("/user")
    public String vf() {
        return "HI";
    }

    @GetMapping("/admin")
    public String ds(Model model) {

        //   for (Orders  order : adminservice.recentorders())
        //     System.out.println(order.toString());
        model.addAttribute("recent", adminservice.recentorders());
        model.addAttribute("daily", adminservice.dailyorder());
        model.addAttribute("monthly", adminservice.monthlyorder());
        model.addAttribute("totalrevenue", adminservice.totalrevenue());
        model.addAttribute("monthlyrevenue", adminservice.monthlyrevenue());

        return "index";
    }

    @GetMapping("/secured")
    public String index() {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != "anonymousUser") {
            System.out.println("YES ALREADY LOGGED IN " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());

            return "redirect:/";
        } else
            return "login";

    }


    @PostMapping("/signup")
    public String sigin(@RequestBody local l) {
        return x.check(l);
    }


    @PostMapping("/admin/add")
    public String add(@RequestBody productadd productadd) {
        return adminservice.addproduct(productadd);
    }


    @GetMapping("/admin/fetch")
    public String adminfetch() {
        return "index";
//        return adminservice.fetchdetails();
    }


    @PostMapping("/admin/delete")
    public String delete() {
        return "done";
    }

    @PostMapping("/admin/update")
    public String update(@RequestBody productadd productadd) {
        return adminservice.updateproduct(productadd);

    }

    @PostMapping("/user/placeorder")
    public String place(@RequestBody String name) {

        return x.Orderplaced(name);

    }

    @GetMapping("/admin/allproducts")
    public List all() {
        return adminservice.allproducts();
    }

    // @GetMapping("/users/getdetails")

    @GetMapping("/okay")
    public String okays() {
        return "okay";
    }

    @GetMapping("/admin/check")
    public String chec() {
        return "CHECKED";
    }

    @GetMapping("/resetpassword")

    public String gg() {
        return "reset-password";

    }

    @GetMapping("/register")
    public String oka(Model model) {

        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != "anonymousUser") {
            System.out.println("YES ALREADY LOGGED IN " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());

            return "redirect:/";
        } else
//if(count==1){
//    model.addAttribute("success","ONE");
//     //   model.addAttribute("done","YES");
//      count=0;
//      System.out.println("RAM NAAM");
//      for(int i=0;i<1;i++){
//          try{
//
//              Thread.sleep(6000);
//          } catch (InterruptedException e) {
//              throw new RuntimeException(e);
//          }
//      System.out.println("DUGNA");
//  return "redirect:/secured";
//  }}
            return "register";
    }

    @GetMapping("/checking")
    public String ew() {

        return "okayg";
    }

    @PostMapping("/register")
    public @ResponseBody ResponseEntity<String> dones(@ModelAttribute local local, Model model) {
        // model.addAttribute("success","ONE");
        System.out.println(local.toString());
        local.setEmailid(local.getEmailid().toLowerCase());

        String check = x.newuser(local);
        if (check.equals("ERROR"))
            return ResponseEntity.badRequest().body("This Email-ID already exists");

        return ResponseEntity.ok("YES");
    }


    @GetMapping("/orderdetails")
    public String wis(Model model) {

        model.addAttribute("recent", adminservice.recentorders());
        return "okay";
    }

    @GetMapping("/tables")
    public String table(Model model) {

        model.addAttribute("fetchall", adminservice.allorders());
        return "tables";
    }

    @PostMapping("/authenticate")
    public String authenticate(@ModelAttribute local local) {

        String username = local.getUsername();
        String password = local.getPassword();

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
        try {
            authenticationManager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Credentials Invalid !!");
        }
        if (username.equals("taliya"))
            return "redirect:/admin";
        else
            return "redirect:/";
    }

    @PostMapping("/resetpassword")
    public String resetlink(@ModelAttribute temp temp, Model model) {
        System.out.println("RAMDEV" + temp.getEmailid());
        String b = adminservice.resetlink(temp.getEmailid());
        model.addAttribute("success", "YES");

        return "reset-password";


    }

    @PostMapping("/upload")
    public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file) {

        String fileName = file.getOriginalFilename();
        try {
            file.transferTo(new File("D:\\upload\\" + fileName));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok("File uploaded successfully.");
    }


    @GetMapping("/upload")
    public String upload() {
        return "upload";
    }

    @PostMapping("/otpverification")
    public @ResponseBody ResponseEntity<String> otpverification(String otp) {
        System.out.println("MY OTP IS " + otp);
        String check = adminservice.otpverification(otp);
        if (check.equals("YES"))
            return ResponseEntity.ok("OTP IS VERIFIED");
        if (check.equals("NO")) return ResponseEntity.badRequest().body("OTP DOES NOT MATCH");
        if (check.startsWith("Invalid")) {
            System.out.println("final " + check);
            return ResponseEntity.badRequest().body("INVALID OTP");
        }
        return ResponseEntity.badRequest().body("Internal Error");
    }

    @GetMapping("/verification")
    public String verification(Model model) {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() == "anonymousUser" ||
                crud.findByEmailid(SecurityContextHolder.getContext().getAuthentication().getName()).isUserverfied())
            return "redirect:/";

        model.addAttribute("username", adminservice.username());
        model.addAttribute("emailid", SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("status", adminservice.isverified());
        return "verification";

    }

    @GetMapping("/sendotp")
    public @ResponseBody ResponseEntity<String> sendotp() {

        adminservice.generateotp(SecurityContextHolder.getContext().getAuthentication().getName());
        return ResponseEntity.ok("OTP SENT SUCCESSFULLY");

    }

    @GetMapping("/payment")
    public String payment() {

        return "payment";
    }

    @PostMapping("/razorpay")
    public @ResponseBody ResponseEntity<String> razorpay(String amount) {

        String abc = razor.ordercreate(Double.parseDouble(amount));
        if (abc != null)
            return ResponseEntity.ok(abc);
        return
                ResponseEntity.badRequest().body("NO");

    }

    @GetMapping("/orderplaced")
    public String orderplace() {

        return "orderplaced";
    }

    //    @PostMapping("/orderplaced")
//    public String orderplaced(@RequestBody razorpaysuccess razorpaysuccess) throws RazorpayException {
//System.out.println(razorpaysuccess.toString());
//        if (adminservice.signatureverification(razorpaysuccess))
//            return "redirect:/orderplaced";
//        return "paymentfailed";
//    }
//
    @RequestMapping(value = "/orderplaced", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String orderplaced(razorpaysuccess razorpaysuccess) throws RazorpayException {
        System.out.println(razorpaysuccess.toString());
String orderid=razorpaysuccess.getRazorpay_order_id();
        if (adminservice.signatureverification(razorpaysuccess)) {
            return "redirect:/orderplaced?orderid="+orderid;
        }
            return "paymentfailed";

    }
}


