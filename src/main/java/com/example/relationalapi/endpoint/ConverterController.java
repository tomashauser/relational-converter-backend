package com.example.relationalapi.endpoint;

import com.example.relationalapi.languages.ra.visitors.RandomExpressionGenerator;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/convert")
public class ConverterController {
    @Autowired
    private ConverterService converterService;

    @RequestMapping("/")
    public ResponseEntity<String> healthCheck() {
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PostMapping(value = "/standardToSimplified", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> standardToSimplified(@RequestBody String input) {
        InputWrapper inputWrapper;

        try {
            inputWrapper = new InputWrapper(input);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return this.converterService.convertStandardToSimplified(inputWrapper);
    }

    @PostMapping(value = "/simplifiedToStandard", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> simplifiedToStandard(@RequestBody String input) {
        InputWrapper inputWrapper;

        try {
            inputWrapper = new InputWrapper(input);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return this.converterService.convertSimplifiedToStandard(inputWrapper);
    }

    @PostMapping(value = "/standardToTRC", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> standardToTRC(@RequestBody String input) throws JSONException {
        InputWrapper inputWrapper;

        try {
            inputWrapper = new InputWrapper(input);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return this.converterService.convertStandardToTRC(inputWrapper);
    }

    @PostMapping(value = "/simplifiedToTRC", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> simplifiedToTRC(@RequestBody String input) throws JSONException {
        InputWrapper inputWrapper;

        try {
            inputWrapper = new InputWrapper(input);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return this.converterService.convertSimplifiedToTRC(inputWrapper);
    }
}
