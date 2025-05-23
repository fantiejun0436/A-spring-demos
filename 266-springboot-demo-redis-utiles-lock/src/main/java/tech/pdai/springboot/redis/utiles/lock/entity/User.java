package tech.pdai.springboot.redis.utiles.lock.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author pdai
 */
@Getter
@Setter
public class User {

    /**
     * user id.
     */
    private Long id;

    /**
     * username.
     */
    private String userName;

}
