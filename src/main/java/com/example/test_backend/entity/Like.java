package com.example.test_backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "\"Like\"")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LikeID")
    private BigInteger LikeID;

    @ManyToOne
    @JoinColumn(name = "PostID", referencedColumnName = "PostID")
    private Post Like_PostId;

    @ManyToOne
    @JoinColumn(name = "UserID", referencedColumnName = "GOOGLE_ID", foreignKey = @ForeignKey(name = "none"))
    private User Like_UserId;

    @Column(name = "Created_At")
    private LocalDateTime Created_At;
}
