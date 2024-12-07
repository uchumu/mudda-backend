package org.example.mudda.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@Table(name = "message")
@NoArgsConstructor
public class Message {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CAPSULE_ID", nullable = false)
    @JsonIgnore
    private Capsule capsule;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private String imageUrl;


    @Builder
    public Message(Capsule capsule, String userName, String text, String imageUrl) {
        this.capsule = capsule;
        this.userName = userName;
        this.text = text;
        this.imageUrl = imageUrl;
    }

    public static Message of(Capsule capsule, String userName, String text, String imageUrl) {
        return builder()
                .capsule(capsule)
                .userName(userName)
                .text(text)
                .imageUrl(imageUrl)
                .build();
    }

    public void update(String text, String imageUrl) {
        this.text = text;
        this.imageUrl = imageUrl;
    }

}
