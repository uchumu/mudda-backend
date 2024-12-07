package org.example.mudda.dto;

import lombok.Getter;
import org.example.mudda.entity.Capsule;
import org.example.mudda.model.CapsuleResponseInterface;

@Getter
public class UndiggedCapsuleResponseDTO implements CapsuleResponseInterface {
    private final Long capsuleDesignId;
    private final String title;
    private final Long goalTime;
    private final String status = "undigged";
    private final int messageCount;

    public UndiggedCapsuleResponseDTO(Capsule capsule) {
        this.capsuleDesignId = capsule.getCapsuleDesignId();
        this.title = capsule.getTitle();
        this.goalTime = capsule.getGoalTime();
        this.messageCount = capsule.getMessages().size();
    }
}