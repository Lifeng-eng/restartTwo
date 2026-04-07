package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class RestartTwoApplicationTests {

    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    @Test
    void contextLoads() {
    }

    @Test
    public void contextLoads1() {

        Map<String,String> map = new HashMap<>();
        map.put("username","admin");
        map.put("password","123456");
        map.forEach((k,v)->{
            System.out.println(k);
            System.out.println(v);
        });
    }
}
