package com.example.nodebook_hub.notebook_hub_backend.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.nodebook_hub.notebook_hub_backend.DTO.BookingDTO;
import com.example.nodebook_hub.notebook_hub_backend.DTO.CheckOutDTO;
import com.example.nodebook_hub.notebook_hub_backend.DTO.OrderBookingRequest;
import com.example.nodebook_hub.notebook_hub_backend.DTO.ResponseDTO;
import com.example.nodebook_hub.notebook_hub_backend.Entity.CustomOrder;
import com.example.nodebook_hub.notebook_hub_backend.Entity.NoteBookDetails;
import com.example.nodebook_hub.notebook_hub_backend.Entity.OrderDetails;
import com.example.nodebook_hub.notebook_hub_backend.Entity.UserDetails;
import com.example.nodebook_hub.notebook_hub_backend.Service.UserService;
import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.stripe.exception.StripeException;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Value("${stripe.api.key}")
    private String stripeKey;
    @Autowired
    public UserController(UserService userService){
        this.userService=userService;
    }

    @GetMapping("/get-all-notebooks")
    public ResponseEntity getAllNoteBooks(){
        System.out.println("Notebooks");
        try{
            List<NoteBookDetails> noteBookDetails=userService.getAllNoteBooks();
            System.out.println(noteBookDetails);
           return ResponseEntity.ok(noteBookDetails);
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
@PostMapping("/book-note")
    public ResponseEntity BookNotebook(@RequestBody OrderBookingRequest bookingData){
    OrderDetails order = new OrderDetails();
    UserDetails user=userService.findByUserId(bookingData.getUserId());
    NoteBookDetails product=userService.findByProductId(bookingData.getProductId());
    order.setUser(user);
    order.setProduct(product); // Set the fetched product
    order.setQuantity(bookingData.getQuantity());
    order.setAddress(bookingData.getAddress());
    order.setTotalPrice(bookingData.getTotalPrice());
    order.setOrderedDate(LocalDateTime.now());
    ResponseDTO responseDTO=userService.saveOrderDetails(order);
        System.out.println(responseDTO);
        if(responseDTO.isSuccess()){

            return ResponseEntity.ok(responseDTO);
        }
        else{
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }
}
    @GetMapping("get-order/{user_id}")
    public List<OrderDetails> getOrdersByUserId(@PathVariable int user_id) {
        return userService.getOrdersByUserId(user_id);
    }
    @GetMapping("get-custom-order/{user_id}")
    public List<CustomOrder> getCustomerOrdersByUserId(@PathVariable int user_id) {
        return userService.getCustomOrdersByUserId(user_id);
    }
    @PostMapping("save-custom-order")
    public ResponseEntity<String> saveCustomOrders(
            @RequestParam("paper-type") String paperType,
            @RequestParam("size") String size,
            @RequestParam("ruling") String ruling,
            @RequestParam("cover") String cover,
            @RequestParam("quantity") int quantity,
            @RequestParam("pages") int pages,
            @RequestParam("notebookname") String notebookName,
            @RequestParam("price") int price,
            @RequestParam("user-id") int userId,
            @RequestParam("city") String city,
            @RequestParam("street") String street,
            @RequestParam("zipcode") int zipcode,
            @RequestParam("image") MultipartFile noteImageFile,
            @RequestParam("logo-image") MultipartFile logoImageFile
    ) {
        try {
            // Convert MultipartFile to byte[]
            byte[] noteImage = noteImageFile.getBytes();
            byte[] logoImage = logoImageFile.getBytes();

            UserDetails user = userService.findByUserId(userId);
            CustomOrder order = new CustomOrder();
            order.setNote_name(notebookName);
            order.setPaperType(paperType);
            order.setSize(size);
            order.setRuling(ruling);
            order.setCover(cover);
            order.setQuantity(quantity);
            order.setPrice(price); // Example pricing logic
            order.setPages(pages);
            order.setUser(user);
            order.setNoteImage(noteImage);
            order.setLogoImage(logoImage);
            order.setOrderedDate(LocalDateTime.now());

            order.setStreet(street);
            order.setCity(city);
            order.setZip(zipcode);
            // You can also calculate expectedDeliveryDate based on business logic
            order.setExpectedDeliveryDate(LocalDateTime.now().plusDays(7));

           String response=userService.saveCustomOrder(order);
           return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        }
        catch(IOException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error processing image");
        }
    }
    @PostMapping("/create-checkout-session")
    public Map<String, String> createCheckoutSession(@RequestBody CheckOutDTO request) throws StripeException {
        BookingDTO bookingData = request.getBookingData();
        String successUrl = request.getSuccess_url();
        logger.info("Received booking data: {}", bookingData);
        logger.info("Success Url {}",successUrl);
        System.out.println(request.getSuccess_url());
        String cancelUrl = request.getCancel_url();
        Map<String, Object> params = new HashMap<>();
        params.put("payment_method_types", List.of("card"));
        params.put("mode", "payment");
        params.put("success_url", successUrl);
        params.put("cancel_url", "http://localhost:5173/payment-cancel");

        List<Object> lineItems = new ArrayList<>();
        Map<String, Object> item = new HashMap<>();
        item.put("price_data", Map.of(
                "currency", "usd",
                "unit_amount", (int) (bookingData.getTotalPrice()),// in cents
                "product_data", Map.of("name", "Notebook Order")
        ));
        item.put("quantity", 1);
        lineItems.add(item);

        params.put("line_items", lineItems);

        Session session = Session.create(params);

        return Map.of("url", session.getUrl());
    }
//    @PostMapping("/create-custom-checkout-session")
//    public Map<String, String> createCustomCheckoutSession(@RequestBody BookingDTO bookingData) throws StripeException {
//        Stripe.apiKey = stripeKey; // Secret Key
//
//        Map<String, Object> params = new HashMap<>();
//        params.put("payment_method_types", List.of("card"));
//        params.put("mode", "payment");
//        params.put("success_url", "http://localhost:5173/custom?session_id={CHECKOUT_SESSION_ID}");
//
//        params.put("cancel_url", "http://localhost:5173/payment-cancel");
//
//        List<Object> lineItems = new ArrayList<>();
//        Map<String, Object> item = new HashMap<>();
//        item.put("price_data", Map.of(
//                "currency", "usd",
//                "unit_amount", (int) (bookingData.getTotalPrice()),// in cents
//                "product_data", Map.of("name", "Notebook Order")
//        ));
//        item.put("quantity", 1);
//        lineItems.add(item);
//
//        params.put("line_items", lineItems);
//
//        Session session = Session.create(params);
//
//        return Map.of("url", session.getUrl());
//    }

}
