package com.example.ImageUploadExample;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/test")
public class TestController {


    @PostMapping("/uploadImage")
    public ResponseEntity<String> signup(@RequestPart(value = "image" ,required = false) MultipartFile testImage){
        String responseMessage = "test ok";
        return ResponseEntity.ok(responseMessage);
    }

}
