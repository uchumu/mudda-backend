package org.example.mudda.model;

import org.example.mudda.dto.OpenedCapsuleResponseDTO;
import org.example.mudda.dto.UndiggedCapsuleResponseDTO;
import org.example.mudda.dto.request.DiggedCapsuleResponseDTO;
import org.example.mudda.entity.Capsule;

public interface CapsuleResponseInterface {
    static CapsuleResponseInterface of(Capsule capsule) {
        switch (capsule.getStatus()) {
            case "undigged":
                return new UndiggedCapsuleResponseDTO(capsule);
            case "digged":
                return new DiggedCapsuleResponseDTO(capsule);
            case "opened":
                return new OpenedCapsuleResponseDTO(capsule);
            default:
                throw new IllegalArgumentException("Unknown status: " + capsule.getStatus());
        }
    }
}