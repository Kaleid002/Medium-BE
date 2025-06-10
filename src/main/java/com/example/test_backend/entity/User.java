package com.example.test_backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "\"Users\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_sequence", allocationSize = 1)
    @Column(name = "ID")
    private BigInteger id;

    @Column(name = "GOOGLE_ID", unique = true)
    private String googleId;

    @Column(name = "NAME", length = 20)
    private String name;

    @Column(name = "EMAIL", unique = true, length = 30)
    private String email;

    @Column(name = "PROVIDER", length = 10)
    private String provider;

    @Column(name = "REFRESH_TOKEN", length = 1000)
    private String refreshToken;

    @Column(name = "CREATED_AT")
    private LocalDateTime createAt;

    @OneToMany(mappedBy = "Post_UserId")
    private List<Post> posts;

    @OneToMany(mappedBy = "BlackList_UserId")
    private Set<BlackList> blacklists;

    @OneToMany(mappedBy = "Comment_UserId")
    private List<Comment> comments;

    @OneToMany(mappedBy = "Follower_UserId")
    private Set<Follower> followers;

    @OneToMany(mappedBy = "Follower_FollowedId")
    private Set<Follower> following;

    @OneToMany(mappedBy = "Like_UserId", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Like> Likes = new HashSet<>();

    @OneToMany(mappedBy = "ReadingHistory_UserId", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Reading_History> reading_History = new HashSet<>();

    @OneToMany(mappedBy = "ReadingList_UserId", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Reading_List> readingLists = new HashSet<>();

    @OneToOne(mappedBy = "Setting_UserId")
    private Setting setting;

    @OneToOne(mappedBy = "personalizationUserId", cascade = CascadeType.ALL, orphanRemoval = true)
    private Personalization personalization;
}
