package com.example.jpamapids;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//@SpringBootTest
class JpaMapidsApplicationTests {

    @Getter
    @Setter
    public static class Tuhucon {
        private Integer age;
        private String name;
    }

    @Test
    void contextLoads() {

        Tuhucon tuhucon = new Tuhucon();
        Set<Tuhucon> set = new HashSet();
        set.add(tuhucon);

    }

}
