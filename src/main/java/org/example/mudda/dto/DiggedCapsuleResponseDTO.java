package org.example.mudda.dto.request;

import lombok.Getter;
import org.example.mudda.custom.Tuple;
import org.example.mudda.model.CapsuleResponseInterface;
import org.example.mudda.entity.Capsule;

@Getter
public class DiggedCapsuleResponseDTO implements CapsuleResponseInterface {
    private final Long capsuleDesignId;
    private final String title;
    private final Long goalTime;
    private Tuple<Double, Double> map;
    private final String status = "digged";
    private final int messageCount;
    private final Long createTime;

    public DiggedCapsuleResponseDTO(Capsule capsule) {
        this.capsuleDesignId = capsule.getCapsuleDesignId();
        this.title = capsule.getTitle();
        this.goalTime = capsule.getGoalTime();
        this.map = new Tuple<>(capsule.getCoodinateX(), capsule.getCoodinateY());
        this.messageCount = capsule.getMessages().size();
        this.createTime = capsule.getCreateTime();
    }
}