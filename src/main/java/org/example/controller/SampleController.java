package org.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @GetMapping("/testException")
    public String testException(@RequestParam(required = false) String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name parameter is required.");
        }
        return "Hello, " + name;
    }
}
