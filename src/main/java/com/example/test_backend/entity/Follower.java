package com.example.test_backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "FOLLOWER")
public class Follower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private BigInteger ID;

    @ManyToOne
    @JoinColumn(name = "UserID", referencedColumnName = "GOOGLE_ID", foreignKey = @ForeignKey(name = "none"))
    private User Follower_UserId;

    @ManyToOne
    @JoinColumn(name = "FollowedID", referencedColumnName = "GOOGLE_ID", foreignKey = @ForeignKey(name = "none"))
    private User Follower_FollowedId;

    @Column(name = "Followed_At")
    private LocalDateTime Followed_At;
}
