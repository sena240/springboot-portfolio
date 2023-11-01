package com.example.demo.entity;


import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name = "member")
public class Member {

    // ID
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    // 名前
    @Column(name = "member_name")
    private String memberName;
    
    // フリガナ
    @Column(name = "furigana")
    private String furigana;
    
    // 所属部門（FK）
    @ManyToOne
    private Department department;
    
    // 住所
    @Column(name = "address")
    private String address;
    
    // 電話番号
    @Column(name = "phone")
    private String phone;

    // 連絡先メール
    @Column(name = "mail")
    private String mail;

    // 備考
    @Column(name = "notes")
    private String notes;
    
    //リレーション
    @OneToMany(mappedBy = "member")
    private List<Task> tasks;
    
    @ManyToMany(mappedBy = "members")
    private List<Project> projects;
    
    @ManyToMany(mappedBy = "members")
    private List<Meeting> meetings;
}
