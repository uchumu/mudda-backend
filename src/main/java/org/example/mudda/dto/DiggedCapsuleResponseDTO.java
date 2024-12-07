package org.example.mudda.dto.request;

import lombok.Getter;
import org.example.mudda.model.CapsuleResponseInterface;
import org.example.mudda.entity.Capsule;

@Getter
public class DiggedCapsuleResponseDTO implements CapsuleResponseInterface {
    private final Long capsuleDesignId;
    private final String title;
    private final Long goalTime;
    private final String status = "digged";
    private final int messageCount;
    private final Long createAt;

    public DiggedCapsuleResponseDTO(Capsule capsule) {
        this.capsuleDesignId = capsule.getCapsuleDesignId();
        this.title = capsule.getTitle();
        this.goalTime = capsule.getGoalTime();
        this.messageCount = capsule.getMessages().size();
        this.createAt = capsule.getCreateAt();
    }
}