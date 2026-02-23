package com.example.nodebook_hub.notebook_hub_backend.Controller;

import com.example.nodebook_hub.notebook_hub_backend.Config.JwtUtil;
import com.example.nodebook_hub.notebook_hub_backend.DTO.StatusUpdateRequest;
import com.example.nodebook_hub.notebook_hub_backend.Entity.AdminAnalytics;
import com.example.nodebook_hub.notebook_hub_backend.Entity.CustomOrder;
import com.example.nodebook_hub.notebook_hub_backend.Entity.NoteBookDetails;
import com.example.nodebook_hub.notebook_hub_backend.Entity.OrderDetails;
import com.example.nodebook_hub.notebook_hub_backend.Service.AdminService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private AdminService adminService;
    @Autowired
    public AdminController(AdminService adminService){
        this.adminService=adminService;
    }
    @PostMapping("/add-note")
    public ResponseEntity validateAdmin(@RequestParam("paperType") String paperType,
                                        @RequestParam("size") String size,
                                        @RequestParam("ruling") String ruling,
                                        @RequestParam("cover") String cover,
                                        @RequestParam("quantity") int quantity,
                                        @RequestParam("price") int price,
                                        @RequestParam("pages") int pages,
                                        @RequestParam("note_name") String note_name,
                                        @RequestParam("note-image") MultipartFile noteImage){
        System.out.println(paperType);
        try {
            byte[] imageBytes = noteImage.getBytes();
            NoteBookDetails notebook = new NoteBookDetails();
            notebook.setPaperType(paperType);
            notebook.setSize(size);
            notebook.setRuling(ruling);
            notebook.setCover(cover);
            notebook.setQuantity(quantity);
            notebook.setPrice(price);
            notebook.setPages(pages);
            notebook.setNote_name(note_name);
            notebook.setNoteImage(imageBytes);
            adminService.saveNotebook(notebook);
            return ResponseEntity.ok("Saved Successfully");

        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);

        }

    }
    @GetMapping("/get-all-orders")
    public List<OrderDetails> getAllOrders(){
        return adminService.getAllOrders();
    }
    @GetMapping("/get-all-custom-orders")
    public List<CustomOrder> getAllCustomOrders(){
        return adminService.getAllCustomOrders();
    }

    @PutMapping("/update-status/{orderId}")
    public ResponseEntity<OrderDetails> updateOrderStatus(@PathVariable int orderId, @RequestBody StatusUpdateRequest statusUpdateRequest) {
        try {
            System.out.println("Received status update: " + statusUpdateRequest.getStatus());
            OrderDetails updatedOrder = adminService.updateOrderStatus(orderId, statusUpdateRequest.getStatus());
            return ResponseEntity.ok(updatedOrder);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    @PutMapping("/update-status-custom/{orderId}")
    public ResponseEntity<CustomOrder> updateCustomOrderStatus(@PathVariable int orderId, @RequestBody StatusUpdateRequest statusUpdateRequest) {
        try {
            System.out.println("Received status update: " + statusUpdateRequest.getStatus());
            CustomOrder updatedOrder = adminService.updateCustomOrderStatus(orderId, statusUpdateRequest.getStatus());
            return ResponseEntity.ok(updatedOrder);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }
    @GetMapping("/get-analytics")
    public AdminAnalytics getAnalytics(){
        return adminService.getAnalytics();
    }
}
