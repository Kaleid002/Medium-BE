package com.example.test_backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Entity
@Getter
@Setter
@Table(name = "RELEVANCY_POST_TAG")
public class relevancy_Post_Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private BigInteger ID;

    @Column(name = "PostID")
    private BigInteger PostID;

    @Column(name = "TagID")
    private BigInteger TagID;
}
