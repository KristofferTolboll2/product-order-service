package com.example.productservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class ProductServiceApplicationTests {

    @Test
    void contextLoads() {
        String test = "hey";
        String test1 = "hoy";
        assertEquals(test.length() , test1.length());
    }

    @Test
    public void stringTest(){
        String test = "hey";
        String test1 = "hoy";
        assertEquals(test.length(), test1.length());
    }

}
