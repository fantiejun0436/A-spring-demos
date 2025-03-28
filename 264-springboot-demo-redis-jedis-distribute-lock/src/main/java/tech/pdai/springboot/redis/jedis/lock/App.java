package tech.pdai.springboot.redis.jedis.lock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author pdai
 */
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        System.out.println("小胖猪爱你");
        SpringApplication.run(App.class, args);
    }
}