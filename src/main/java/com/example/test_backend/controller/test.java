package com.example.test_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class test {
    @GetMapping("/home")
    public String home() {
        return "系統首頁";
    }
}