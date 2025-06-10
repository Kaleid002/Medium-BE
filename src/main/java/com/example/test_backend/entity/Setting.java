package com.example.test_backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "SETTING")
public class Setting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private BigInteger ID;

    @OneToOne
    @JoinColumn(name = "UserID", referencedColumnName = "GOOGLE_ID", foreignKey = @ForeignKey(name = "none"))
    private User Setting_UserId;

    @Column(name = "Header_Color", length = 22)
    private String Header_Color;

    @Column(name = "Header_Img")
    private String Header_Img;

    @Column(name = "Name_txt_logo")
    private String Name_txt_logo;

    @Column(name = "Blog_roll", length = 5)
    private String Blog_roll;

    @Column(name = "Setting_At")
    private LocalDateTime Setting_At;
}
