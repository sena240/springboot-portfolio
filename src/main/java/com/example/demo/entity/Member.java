package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
	@NotBlank(message = "名前を入力してください")
	@Column(name = "member_name")
	private String memberName;

	// フリガナ
	@Pattern(regexp = "^(?=.*[ァ-ヶー])[ァ-ヶー\u3000\\s]+$", message = "フリガナをカタカナで入力してください")
	@Column(name = "furigana")
	private String furigana;

	// 所属部門（FK）
	@NotNull
	@ManyToOne
	private Department department;

	// 住所
	@Column(name = "address")
	private String address;

	// 電話番号
	@Pattern(regexp = "[0-9-]*", message = "電話番号は半角数字とハイフンで入力してください")
	@Column(name = "phone")
	private String phone;

	// 連絡先メール
	@NotBlank(message = "連絡先メールを入力してください")
	@Email(message = "有効なメールアドレスを入力してください")
	@Column(name = "mail")
	private String mail;

	// 備考
	@Column(name = "notes")
	private String notes;

	//リレーション
	@OneToMany(mappedBy = "member", fetch = FetchType.EAGER)
	private List<Task> tasks;

	@ManyToMany(mappedBy = "members", fetch = FetchType.EAGER)
	private List<Project> projects;

	@ManyToMany(mappedBy = "members", fetch = FetchType.EAGER)
	private List<Meeting> meetings;
}
