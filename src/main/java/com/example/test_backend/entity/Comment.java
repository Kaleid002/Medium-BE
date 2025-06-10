package com.example.test_backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "Comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CommentID")
    private BigInteger CommentID;

    @ManyToOne
    @JoinColumn(name = "PostID", referencedColumnName = "PostID")
    private Post Comment_PostId;

    @ManyToOne
    @JoinColumn(name = "UserID", referencedColumnName = "GOOGLE_ID", foreignKey = @ForeignKey(name = "none"))
    private User Comment_UserId;

    @Column(name = "Content", length = 2000)
    private String Content;

    @Column(name = "Created_At")
    private LocalDateTime Created_At;
}
