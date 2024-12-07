package org.example.mudda.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.example.mudda._global.response.BaseResponse;
import org.example.mudda.custom.Tuple;
import org.example.mudda.dto.request.CapsuleInsertRequestDTO;
import org.example.mudda.dto.request.CapsuleMessageInsertRequestDTO;
import org.example.mudda.service.MuddaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Tag(name = "Mudda", description = "Mudda API.")
@RestController
@RequiredArgsConstructor
public class MuddaController {

    private final MuddaService muddaService;

    @GetMapping("/api/capsule")
    @Operation(
            summary = "캡슐 조회",
            description = "" +
                    "# request\n" +
                    "- `title`: 문자열 (문자열)\n" +
                    "\n" +
                    "# response\n" +
                    "- `title`: 문자열 (문자열)\n"
    )
    ResponseEntity<BaseResponse> selectCapsuleList(
            @RequestParam(value = "code", required = false) String code
    ) throws Exception {
        return new ResponseEntity<>(muddaService.selectCapsuleList(code), HttpStatus.OK);
    }

    @PostMapping("/api/capsule")
    @Operation(
            summary = "캡슐 생성",
            description = "" +
                    "# request\n" +
                    "- `title`: 문자열 (문자열)\n" +
                    "- `map`: 지도 좌표\n" +
                    "  - `x`: x 좌표(실수)\n" +
                    "  - `y`: y 좌표(실수)\n" +
                    "- `goalTime`: 열람 시간(정수)\n" +
                    "- `capsuleDesignId`: 디자인 ID(정수)\n" +
                    "- `password`: 비밀번호(문자열)\n" +
                    "\n" +
                    "# response\n" +
                    "- `code`: 캡슐 코드 (문자열)\n"
    )
    ResponseEntity<BaseResponse> createCapsule(
            @RequestBody @Valid CapsuleInsertRequestDTO dto
            ) throws Exception {
        return new ResponseEntity<>(muddaService.createCapsule(dto), HttpStatus.OK);
    }


    @PostMapping("/api/capsule/message")
    @Operation(
            summary = "캡슐 메시지 생성",
            description = "" +
                    "# request\n" +
                    "- `code`: 캡슐 코드(문자열)\n" +
                    "- `dto`: dto\n" +
                    "  - `userName`: 유저 이름(문자열)\n" +
                    "  - `text`: 텍스트(문자열)\n"+
                    "- `file` : Multipart파일 (파일)\n"

    )

    ResponseEntity<BaseResponse> createCapsuleMessage(
            @RequestParam(value = "code") String code,
            @RequestPart @Valid CapsuleMessageInsertRequestDTO dto,
            @RequestPart @Parameter(hidden = true) MultipartFile file
    ) throws Exception {
        dto.setCode(code);
        return new ResponseEntity<>(muddaService.createCapsuleMessage(dto, file), HttpStatus.OK);
    }


}
