package com.example.test_backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "TAG")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private BigInteger ID;

    @Column(name = "Label",length = 40)
    private String Label;

    @ManyToMany(mappedBy = "Tags")
    private Set<Post> Posts;
}
