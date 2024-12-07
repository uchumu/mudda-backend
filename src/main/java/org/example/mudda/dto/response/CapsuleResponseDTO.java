package org.example.mudda.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
public class CapsuleResponseDTO {

    private String name;


    @Builder
    public CapsuleResponseDTO(String name, String author, boolean status, Set<Long> categoryIdList) {
        this.name = name;
    }

    public static CapsuleResponseDTO of(String name, String author, boolean status, Set<Long> categoryIdList) {
        return builder()
                .name(name)
                .build();
    }


}
