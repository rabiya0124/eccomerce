package check.checks;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLOutput;

@Service
public class razor {
//rzp_live_EoOQHJ1XLDYchs
   // LwER3JQ60smYnAQ8A93r0Qbd

   // rzp_test_LucDyFShm7HErs
   // VhTcxlrCv0kw6bAKLJg6EC8i
    public String ordercreate(Double amount)  {
        RazorpayClient razorpay;
        Order order;
try {
     razorpay = new RazorpayClient("rzp_live_ZlkauHtPbPQXWV", "uQuVu7VqhUNgsoVnsiFyh8yn");
}
catch (RazorpayException e){
    return null;

}

        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", amount*100);
        orderRequest.put("currency", "INR");
        orderRequest.put("receipt", "receipt#1");
        JSONObject notes = new JSONObject();
        notes.put("notes_key_1", "Tea, Earl Grey, Hot");
        orderRequest.put("notes", notes);
        try {
            order = razorpay.orders.create(orderRequest);

        } catch (RazorpayException e) {
            return null;
        }
        System.out.println("REACHED PAYMENT DONE DONE");
        System.out.println(order.toString());
        return (String)order.get("id");

    }
}
