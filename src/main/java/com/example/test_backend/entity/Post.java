package com.example.test_backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "POST")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PostID")
    private BigInteger PostID;

    @ManyToOne
    @JoinColumn(name = "UserID", referencedColumnName = "GOOGLE_ID", foreignKey = @ForeignKey(name = "none"))
    private User Post_UserId;

    @Column(name = "Title", length = 510)
    private String Title;

    @Lob
    @Column(name = "Content")
    private String Content;

    @Column(name = "Post_At")
    private LocalDateTime Post_At;

    @OneToMany(mappedBy = "Comment_PostId")
    private Set<Comment> Comments = new HashSet<>();

    @OneToMany(mappedBy = "Like_PostId", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Like> Likes = new HashSet<>();

    @OneToMany(mappedBy = "ReadingHistory_PostId", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Reading_History> reading_History = new HashSet<>();

    @ManyToMany(mappedBy = "ReadingList_PostId")
    private Set<Reading_List> ReadingLists = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "relevancy_Post_Tag",
            joinColumns = @JoinColumn(name = "PostID"),
            inverseJoinColumns = @JoinColumn(name = "ID")
    )
    private Set<Tag> Tags;

    @OneToMany(mappedBy = "test1")
    private Set<test> tests = new HashSet<>();
}
