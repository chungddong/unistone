package com.sophra.unistone;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class testController {

    @GetMapping("/api/test")
    public String test() {
        return "Hello, world!";
    }

    //pull request 테스트
    
}
