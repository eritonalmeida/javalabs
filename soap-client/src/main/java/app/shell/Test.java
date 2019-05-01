package app.shell;

import com.amazon.ws.model.AWSECommerceService;
import com.amazon.ws.model.AWSECommerceServicePortType;
import com.amazon.ws.model.Cart;
import com.amazon.ws.model.CartGet;
import com.amazon.ws.model.CartGetResponse;
import com.google.gson.Gson;
import java.util.List;

public class Test {

    public static void main(String[] args) {

        AWSECommerceService service = new AWSECommerceService();

        AWSECommerceServicePortType server = service.getAWSECommerceServicePort();

        CartGet request = new CartGet();
        
        CartGetResponse response = server.cartGet(request);

        List<Cart> carts = response.getCart();

        Gson gson = new Gson();
        System.out.println(gson.toJson(carts));
    }
}
