package main.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "email_sent_cistory")
public class EmailSentHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime localDateTime=LocalDateTime.now();

    @ManyToOne
    @JoinColumn()
    private ProfileEntity profile;
}
