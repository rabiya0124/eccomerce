//package check.checks.payments;
//import com.phonepe.sdk.pg.common.http.PhonePeResponse;
//import com.phonepe.sdk.pg.payments.v1.models.request.PgPayRequest;
//import com.phonepe.sdk.pg.payments.v1.models.response.PayPageInstrumentResponse;
//import com.phonepe.sdk.pg.payments.v1.models.response.PgPayResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import check.checks.payments.*;
//
//import java.util.UUID;
//public class page {
//
//    @Autowired phonepe phonepe;
//
//    String merchantId="merchantId";
//    String merchantTransactionId = UUID.randomUUID().toString().substring(0,34);
//    long amount=100;
//    String merchantUserId="merchantUserId";
//    String callbackurl="https://www.merchant.com/callback";
//    String redirecturl="https://www.merchant.com/redirect";
//    String redirectMode="REDIRECT";
//
//
//
//    PgPayRequest pgPayRequest=PgPayRequest.PayPagePayRequestBuilder()
//            .amount(amount)
//            .merchantId(merchantId)
//            .merchantTransactionId(merchantTransactionId)
//            .callbackUrl(callbackurl)
//            .merchantUserId(merchantUserId)
//            .redirectUrl(redirecturl)
//            .redirectMode(redirectMode)
//            .build();
//
//    PhonePeResponse<PgPayResponse> payResponse= phonepe.phonepeClient.pay(pgPayRequest);
//    PayPageInstrumentResponse payPageInstrumentResponse=(PayPageInstrumentResponse)payResponse.getData().getInstrumentResponse();
//    String url=payPageInstrumentResponse.getRedirectInfo().getUrl();
//
//
//}
