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
import org.example.mudda.dto.request.CapsuleUpdateRequestDTO;
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
                    "- `code`: 코드 (문자열)\n" +
                    "\n" +
                    "# response\n" +
                    "- `capsuleDesignId`: 디자인 ID (정수)\n" +
                    "- `title`: 제목 (문자열)\n" +
                    "- `goalTime`: 열람 시간 (정수)\n" +
                    "- `messageCount`: 메시지 개수 (정수)\n" +
                    "- `createAt`: 생성 시간 (정수)\n" +
                    "- `messages`: 메시지 리스트 (배열)\n" +
                    "  - `userName`: 유저 이름 (문자열)\n" +
                    "  - `text`: 텍스트 (문자열)\n" +
                    "  - `fileUrl`: 파일 URL (문자열)\n"

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
                    "- `title`: 제목 (문자열)\n" +
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
                    "- `dto`: dto\n" +
                    "  - `code`: 캡슐 코드(문자열)\n" +
                    "  - `userName`: 유저 이름(문자열)\n" +
                    "  - `text`: 텍스트(문자열)\n"+
                    "- `file` : Multipart파일 (파일)\n"
    )
    ResponseEntity<BaseResponse> createCapsuleMessage(
            @RequestPart @Valid CapsuleMessageInsertRequestDTO dto,
            @RequestPart @Parameter(hidden = true) MultipartFile file
    ) throws Exception {
        return new ResponseEntity<>(muddaService.createCapsuleMessage(dto, file), HttpStatus.OK);
    }

    @PutMapping("/api/capsule")
    @Operation(
            summary = "캡슐 수정",
            description = "" +
                    "# request\n" +
                    "- `code`: 캡슐 코드(문자열)\n"+
                    "- `password`: 비밀번호(문자열)\n"
    )
    ResponseEntity<BaseResponse> updateCapsule(
            @RequestBody @Valid CapsuleUpdateRequestDTO dto
            ) throws Exception {
        return new ResponseEntity<>(muddaService.updateCapsule(dto), HttpStatus.OK);
    }

}
