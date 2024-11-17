package com.sophra.unistone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class testController {

    @Autowired
    private TestRepository testRepository;

    @GetMapping("/api/test")
    public String hello() {
        return testRepository.findById(1L)
                .map(TestEntity::getMessage)
                .orElse("데이터 없음");
    }
    
}
