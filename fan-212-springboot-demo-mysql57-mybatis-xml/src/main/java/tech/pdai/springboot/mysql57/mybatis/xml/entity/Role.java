package tech.pdai.springboot.mysql57.mybatis.xml.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author ftj
 */
@Getter
@Setter
public class Role implements BaseEntity{

    private Long id;

    private String name;

    private String roleKey;

    private String description;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
