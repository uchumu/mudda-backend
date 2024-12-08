package org.example.mudda.dto;

import lombok.Getter;
import org.example.mudda.custom.Tuple;
import org.example.mudda.entity.Capsule;
import org.example.mudda.model.CapsuleResponseInterface;

@Getter
public class UndiggedCapsuleResponseDTO implements CapsuleResponseInterface {
    private final Long capsuleDesignId;
    private final String title;
    private final Long goalTime;
    private Tuple<Double, Double> map;
    private final String status = "undigged";
    private final int messageCount;

    public UndiggedCapsuleResponseDTO(Capsule capsule) {
        this.capsuleDesignId = capsule.getCapsuleDesignId();
        this.title = capsule.getTitle();
        this.goalTime = capsule.getGoalTime();
        this.map = new Tuple<>(capsule.getCoodinateX(), capsule.getCoodinateY());
        this.messageCount = capsule.getMessages().size();
    }
}