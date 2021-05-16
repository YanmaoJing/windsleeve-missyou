package com.lin.missyou.dto;

import com.lin.missyou.validators.PasswordEqual;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@PasswordEqual
public class PersonDTO {
    private String name;
    private Integer age;

    private String password1;
    private String password2;
}
