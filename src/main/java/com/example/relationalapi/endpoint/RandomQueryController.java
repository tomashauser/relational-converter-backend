package com.example.relationalapi.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RandomQueryController {
    @Autowired
    private ConverterService converterService;

    @GetMapping(value = "/getRandomQuery")
    public ResponseEntity<String> getRandomQuery() {
        return this.converterService.getRandomQuery(4);
    }
}
