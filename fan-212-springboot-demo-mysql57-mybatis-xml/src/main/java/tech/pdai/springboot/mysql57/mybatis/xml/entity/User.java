package tech.pdai.springboot.mysql57.mybatis.xml.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class User implements BaseEntity{

    private Long id;

    private String userName;

    @JsonIgnore
    private String password;

    private String email;

    private long phoneNumber;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private List<Role> roles;

    private String description;
}
