package com.example.relationalapi;

import com.example.relationalapi.languages.ra.visitors.RandomExpressionGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RelationalApiApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void randExpr() {
        int i = 1000;

        while (i --> 0) {
            String expr = RandomExpressionGenerator.generateStandardStringExpr(4);
            System.out.println(expr);
        }
    }

}
