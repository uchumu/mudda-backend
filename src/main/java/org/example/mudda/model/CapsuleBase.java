package org.example.mudda.model;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public abstract class CapsuleBase {
    private int capsuleDesignId;
    private String title;
    private Map<String, Object> map; // Map 필드
    private long goalTime; // timestamp
    private int messageCount;
}