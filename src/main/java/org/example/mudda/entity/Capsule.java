package org.example.mudda.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@Table(name = "capsule")
@NoArgsConstructor
public class Capsule {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Double coodinateX;

    @Column(nullable = false)
    private Double coodinateY;

    @Column(nullable = false)
    private Long goalTime;

    @Column(nullable = false)
    private Long capsuleDesignId;

    @Column(nullable = false)
    private String password;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "CAPSULE_ID")
    private List<Message> messages;


    @Builder
    public Capsule(String title, String status, Double coodinateX, Double coodinateY, Long goalTime, Long capsuleDesignId, String password, List<Message> messages) {
        this.title = title;
        this.status = status;
        this.coodinateX = coodinateX;
        this.coodinateY = coodinateY;
        this.goalTime = goalTime;
        this.capsuleDesignId = capsuleDesignId;
        this.password = password;
        this.messages = messages;
    }

    public static Capsule of(String title, String status, Double coodinateX, Double coodinateY, Long goalTime, Long capsuleDesignId, String password, List<Message> messages) {
        return builder()
                .title(title)
                .status(status)
                .coodinateX(coodinateX)
                .coodinateY(coodinateY)
                .goalTime(goalTime)
                .capsuleDesignId(capsuleDesignId)
                .password(password)
                .messages(messages)
                .build();
    }

}
