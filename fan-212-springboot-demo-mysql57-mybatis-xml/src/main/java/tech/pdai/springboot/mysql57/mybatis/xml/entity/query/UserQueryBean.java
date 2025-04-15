package tech.pdai.springboot.mysql57.mybatis.xml.entity.query;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserQueryBean {

    private String userName;

    private String email;

    private String phoneNumber;

    private String description;
}
