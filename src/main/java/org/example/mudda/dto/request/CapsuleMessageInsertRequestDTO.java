package org.example.mudda.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.mudda.custom.Tuple;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
public class CapsuleMessageInsertRequestDTO {

    @NotEmpty(message = "유저 이름을 입력해주세요.")
    @Schema(description = "유저 이름", example = "유저 이름")
    private String userName;

    @NotEmpty(message = "텍스트를 입력해주세요.")
    @Schema(description = "텍스트", example = "텍스트")
    private String text;

    @NotEmpty(message = "코드를 입력해주세요")
    @Schema(description = "코드", example = "코드")
    private String code;

}
