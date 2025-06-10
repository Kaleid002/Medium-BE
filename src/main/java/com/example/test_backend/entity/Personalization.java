package com.example.test_backend.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Entity
@Getter
@Setter
@Table(name = "PERSONALIZATION")
public class Personalization {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "Personalization_seq")
    @SequenceGenerator(name = "Personalization_seq", sequenceName = "Personalization_sequence",allocationSize = 1)
    @Column(name = "ID")
    private BigInteger ID;

    @OneToOne
    @JoinColumn(name = "UserID", referencedColumnName = "GOOGLE_ID", foreignKey = @ForeignKey(name = "none"))
    private User personalizationUserId;

    @Column(name = "Avatar")
    private String avatar;

    @Column(name = "Name", length = 50)
    private String Name;

    @Column(name = "Pronouns", length = 50)
    private String Pronouns;

    @Column(name = "Short_Bio", length = 160)
    private String Short_Bio;
}
