package dev.connor.Carsharingplatform.module.membership.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    @ApiModelProperty(example = "admin")
    private String phoneNumber;

    @ApiModelProperty(example = "admin")
    private String password;
}
