package com.example.test_backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "READING_LIST")
public class Reading_List {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private BigInteger ID;

    @ManyToOne
    @JoinColumn(name = "UserID", referencedColumnName = "GOOGLE_ID", foreignKey = @ForeignKey(name = "none"))
    private User ReadingList_UserId;

    @Column(name = "User_Lists", length = 20)
    private String User_Lists;

    @Column(name = "Published", length = 5)
    private String Published;

    @ManyToMany
    @JoinTable(
            name = "READING_LIST_POST",
            joinColumns = @JoinColumn(name = "Reading_List_ID"),
            inverseJoinColumns = @JoinColumn(name = "PostID")
    )
    private Set<Post> ReadingList_PostId = new HashSet<>();
}
