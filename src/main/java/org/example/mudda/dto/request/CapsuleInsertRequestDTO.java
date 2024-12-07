package org.example.mudda.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.mudda.custom.Tuple;

import java.util.Set;

@Getter
@Setter
public class CapsuleInsertRequestDTO {

    @NotEmpty(message = "캡슐 이름을 입력해주세요.")
    @Schema(description = "캡슐 이름", example = "캡슐 이름")
    private String title;

    @Schema(description = "지도 좌표")
    private Tuple<Double, Double> map;

    @NotNull(message = "열람 시간을 입력해주세요.")
    @Min(value = 1733563233, message = "열람 시간은 현재 이후여야 합니다.")
    @Schema(description = "열람 시간", example = "1733593233")
    private Long goalTime;

    @NotNull(message = "디자인을 선택해주세요.")
    @Min(value = 1, message = "디자인을 선택해주세요.")
    @Schema(description = "캡슐 디자인 ID", example = "1")
    private Long capsuleDesignId;

    @NotEmpty(message = "비밀번호를 입력해주세요.")
    @Schema(description = "비밀번호", example = "비밀번호")
    private String password;

}
