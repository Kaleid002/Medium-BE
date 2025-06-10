package com.example.test_backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "READING_HISTORY")
public class Reading_History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private BigInteger ID;

    @ManyToOne
    @JoinColumn(name = "UserID", referencedColumnName = "GOOGLE_ID", foreignKey = @ForeignKey(name = "none"))
    private User ReadingHistory_UserId;

    @ManyToOne
    @JoinColumn(name = "PostID", referencedColumnName = "PostID")
    private Post ReadingHistory_PostId;

    @Column(name = "View_At")
    private LocalDateTime View_At;
}
