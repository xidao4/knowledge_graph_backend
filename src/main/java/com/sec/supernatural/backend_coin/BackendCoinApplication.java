package com.sec.supernatural.backend_coin;

import com.sec.supernatural.backend_coin.constant.MyResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BackendCoinApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendCoinApplication.class, args);
    }

    @GetMapping("/test")
    public MyResponse test(){
        return MyResponse.ok("Hi, this is supernatural team.");
    }

}
