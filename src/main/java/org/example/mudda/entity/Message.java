package org.example.mudda.entity;

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

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private String imageUrl;


    @Builder
    public Message(Long id, String userName, String text, String imageUrl) {
        this.id = id;
        this.userName = userName;
        this.text = text;
        this.imageUrl = imageUrl;
    }

    public static Message of(Long id, String userName, String text, String imageUrl) {
        return builder()
                .id(id)
                .userName(userName)
                .text(text)
                .imageUrl(imageUrl)
                .build();
    }

    public void update(Message message) {
        this.userName = message.userName;
        this.text = message.text;
        this.imageUrl = message.imageUrl;
    }

}
