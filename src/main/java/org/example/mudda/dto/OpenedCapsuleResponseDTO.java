package org.example.mudda.dto;

import lombok.Getter;
import org.example.mudda.custom.Tuple;
import org.example.mudda.entity.Capsule;
import org.example.mudda.entity.Message;
import org.example.mudda.model.CapsuleResponseInterface;

import java.util.List;

@Getter
public class OpenedCapsuleResponseDTO implements CapsuleResponseInterface {
    private final Long capsuleDesignId;
    private final String title;
    private final Long goalTime;
    private Tuple<Double, Double> map;
    private final String status = "opened";
    private final int messageCount;
    private final List<Message> messages;

    public OpenedCapsuleResponseDTO(Capsule capsule) {
        this.capsuleDesignId = capsule.getCapsuleDesignId();
        this.title = capsule.getTitle();
        this.goalTime = capsule.getGoalTime();
        this.map = new Tuple<>(capsule.getCoodinateX(), capsule.getCoodinateY());
        this.messageCount = capsule.getMessages().size();
        this.messages = capsule.getMessages();
    }
}