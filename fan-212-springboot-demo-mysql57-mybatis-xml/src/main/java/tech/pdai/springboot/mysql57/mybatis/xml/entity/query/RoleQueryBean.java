package tech.pdai.springboot.mysql57.mybatis.xml.entity.query;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoleQueryBean {
    private String name;

    private String description;

    private String roleKey;
}
