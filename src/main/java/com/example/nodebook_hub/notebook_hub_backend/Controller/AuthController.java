package com.example.nodebook_hub.notebook_hub_backend.Controller;

import com.example.nodebook_hub.notebook_hub_backend.DTO.ResponseDTO;
import com.example.nodebook_hub.notebook_hub_backend.Entity.UserDetails;
import com.example.nodebook_hub.notebook_hub_backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private UserService userService;
    @Autowired
    public AuthController(UserService userService){
        this.userService=userService;
    }
    @PostMapping("save-user")
    public ResponseEntity saveUser(@RequestParam("user_name") String name,
                                   @RequestParam("address") String address,
                                   @RequestParam("email") String email,
                                   @RequestParam("password") String password,
                                   @RequestParam("phone") String phone_number,
                                   @RequestParam("image") MultipartFile image)
    {
        try {
            byte[] imageBytes = image.getBytes();
            UserDetails user = new UserDetails();
            user.setName(name);
            user.setAddress(address);
            user.setEmail(email);
            user.setPassword(password);
            user.setPhone_number(phone_number);
            user.setImage(imageBytes);  // Set the image data

            user.setImage(imageBytes);
            ResponseDTO response = userService.save(user);
            if (response.isSuccess()) {
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }

        }
        catch(IOException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO("Error processing image", false));

        }


    }
    @PostMapping("/login")
    public ResponseEntity LoginUser(@RequestBody Map<String,String> credentials){
        String email=credentials.get("email");
        String password=credentials.get("password");

        ResponseDTO responseDTO=userService.validate(credentials.get("email"),credentials.get("password"));
        System.out.println(responseDTO.getToken());
        System.out.println(responseDTO.getUserDetails());
        if (responseDTO.isSuccess()) {
            return ResponseEntity.ok(responseDTO);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }
    }
}
