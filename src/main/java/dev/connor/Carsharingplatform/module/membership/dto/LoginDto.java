package dev.connor.Carsharingplatform.module.membership.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    @ApiModelProperty(example = "01122223333")
    private String phoneNumber;

    @ApiModelProperty(example = "flex")
    private String password;
}
