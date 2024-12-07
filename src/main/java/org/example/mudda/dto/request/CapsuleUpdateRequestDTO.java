package org.example.mudda.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CapsuleUpdateRequestDTO {

    @NotEmpty(message = "코드를 입력해주세요")
    @Schema(description = "코드", example = "코드")
    private String code;

    @NotEmpty(message = "패스워드를 입력해주세요.")
    @Schema(description = "패스워드", example = "패스워드")
    private String password;

}
