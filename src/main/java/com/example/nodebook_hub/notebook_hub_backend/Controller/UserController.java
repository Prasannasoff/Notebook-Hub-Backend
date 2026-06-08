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
import org.springframework.beans.factory.annotation.Autowired;
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
}
