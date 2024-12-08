package org.example.mudda.service;


import lombok.RequiredArgsConstructor;
import org.example.mudda._global.exception.CustomException;
import org.example.mudda.dto.request.CapsuleInsertRequestDTO;
import org.example.mudda.dto.request.CapsuleMessageInsertRequestDTO;
import org.example.mudda.dto.request.CapsuleUpdateRequestDTO;
import org.example.mudda.entity.Capsule;
import org.example.mudda.entity.Message;
import org.example.mudda.model.CapsuleResponseInterface;
import org.example.mudda.repository.CapsuleRepository;
import org.example.mudda._global.response.BaseResponse;
import org.example.mudda._global.response.MsgType;
import org.example.mudda.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.UUID;

import static org.example.mudda._global.common.CommonService.decrypt;
import static org.example.mudda._global.common.CommonService.encrypt;

@Service
@RequiredArgsConstructor
public class MuddaService {


    private static final Logger log = LoggerFactory.getLogger(MuddaService.class);
    private final CapsuleRepository capsuleRepository;

    private final MessageRepository messageRepository;

    public BaseResponse selectCapsuleList(String code) throws Exception {

        Long capsuleId = null;
        try {
            capsuleId = Long.parseLong(decrypt(code));
            System.out.println("capsuleId = " + capsuleId);
        } catch (Exception e) {
            throw new CustomException(MsgType.CAPSULE_INFO_EMPTY);
        }

        Capsule capsule = capsuleRepository.findById(capsuleId)
                .orElseThrow(() -> new CustomException(MsgType.CAPSULE_INFO_EMPTY));

        if (capsule.getStatus().equals("undigged")) {

        }
        CapsuleResponseInterface dto = CapsuleResponseInterface.of(capsule);

        return BaseResponse.of(MsgType.SEARCH_SUCCESSFULLY, dto);

    }

    @Transactional
    public BaseResponse createCapsule(CapsuleInsertRequestDTO dto) throws Exception {

        long currentTime = Instant.now().getEpochSecond(); // 현재 시간(초 단위)

        if (dto.getGoalTime() < currentTime) {
            throw new CustomException(MsgType.CAPSULE_GOAL_TIME_NOT_ALLOWED);
        }

        Capsule capsule = Capsule.of(dto.getTitle(), "undigged", dto.getMap().getX(), dto.getMap().getY(), dto.getGoalTime(), dto.getCapsuleDesignId(), dto.getPassword(), null);

        capsuleRepository.save(capsule);

        return BaseResponse.of(MsgType.INSERT_SUCCESSFULLY, encrypt(capsule.getId().toString()));

    }


    @Transactional
    public BaseResponse createCapsuleMessage(CapsuleMessageInsertRequestDTO dto, MultipartFile file) throws Exception {

        System.out.println("dto = " + dto);

        System.out.println("file = " + file);

        Long capsuleId = null;
        try {
            capsuleId = Long.parseLong(decrypt(dto.getCode()));
        } catch (Exception e) {
            throw new CustomException(MsgType.CAPSULE_INFO_EMPTY);
        }

        Capsule capsule = capsuleRepository.findById(capsuleId)
                .orElseThrow(() -> new CustomException(MsgType.CAPSULE_INFO_EMPTY));

        String savedFilePath = "";

        if (file != null && !file.isEmpty()) {
            savedFilePath = uploadImage(file);
        }

        int cnt = messageRepository.countByCapsuleId(capsule.getId());

        System.out.println("cnt = " + cnt);
        if (cnt > 255)
            throw new CustomException(MsgType.CAPSULE_MESSAGE_LIMIT);

        Message message = Message.of(capsule, dto.getUserName(), dto.getText(), savedFilePath);

        messageRepository.save(message);

        return BaseResponse.of(MsgType.INSERT_SUCCESSFULLY, encrypt(capsule.getId().toString()));
    }

    private String uploadImage(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new CustomException(MsgType.IMAGE_FILE_EMPTY);
        }

        // 2. 이미지인지 확인 (MIME 타입 체크)
        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image")) {
            System.out.println("contentType = " + contentType);
            throw new CustomException(MsgType.IMAGE_FILE_TYPE_ERROR);
        }

        // 3. 이미지 파일인지 실제 데이터로 확인 (선택)
        try {
            BufferedImage image = ImageIO.read(file.getInputStream());
            if (image == null) {
                System.out.println("이미지 파일이 아닙니다1.");
                throw new CustomException(MsgType.IMAGE_FILE_TYPE_ERROR);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new CustomException(MsgType.IMAGE_FILE_TYPE_ERROR);
        }

        Path imageDir = Paths.get(System.getProperty("user.dir"), "/images");
        try {
            // 4. 저장 디렉토리 생성
            if (!Files.exists(imageDir)) {
                Files.createDirectories(imageDir);
            }

        } catch (Exception e) {
            throw new CustomException(MsgType.FILE_DIR_CREATE_ERROR);

        }

        String savedFilename = null;

        try {
            // 5. 파일명 생성 및 저장
            String originalFilename = file.getOriginalFilename();

            System.out.println("originalFilename = " + originalFilename);

            String extension = originalFilename != null ? originalFilename.substring(originalFilename.lastIndexOf(".")) : "";

            System.out.println("extension = " + extension);

            savedFilename = UUID.randomUUID() + extension; // 고유한 파일명 생성

            System.out.println("savedFilename = " + savedFilename);

            Path savedFilePath = imageDir.resolve(savedFilename);

            System.out.println("savedFilePath = " + savedFilePath);

            file.transferTo(savedFilePath.toFile()); // 파일 저장

        } catch (Exception e) {
            throw new CustomException(MsgType.FILE_DIR_CREATE_ERROR);
        }

        return savedFilename;
    }

    @Transactional
    public BaseResponse updateCapsule(CapsuleUpdateRequestDTO dto) {

        long currentTime = Instant.now().getEpochSecond(); // 현재 시간(초 단위)

        Long capsuleId = null;
        try {
            capsuleId = Long.parseLong(decrypt(dto.getCode()));
        } catch (Exception e) {
            throw new CustomException(MsgType.CAPSULE_INFO_EMPTY);
        }

        Capsule capsule = capsuleRepository.findById(capsuleId)
                .orElseThrow(() -> new CustomException(MsgType.CAPSULE_INFO_EMPTY));

        if (!capsule.getPassword().equals(dto.getPassword())) {
            throw new CustomException(MsgType.PASSWORD_DIFF_ERROR);
        }

        if (!capsule.getStatus().equals("undigged")) {
            throw new CustomException(MsgType.ALREADY_DIGGED);
        }

        if (capsule.getGoalTime() < currentTime) {
            throw new CustomException(MsgType.CAPSULE_GOAL_TIME_NOT_ALLOWED);
        }

        capsule.updateStatus("digged");

        capsuleRepository.save(capsule);

        return BaseResponse.of(MsgType.UPDATE_SUCCESSFULLY, "성공");

    }
}
