package Certification.app.model;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import Certification.app.groups.Group1;

@Entity
@Table(name = "CopyUser")
public class CopyAccount {

	// 主キー
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	@NotNull
	private long id;

	@Column(name = "name", nullable = false)
	@Length(min = 1, max = 20, message = "ユーザーネームは1文字〜２０文字以内にしてください")
	private String name;

	@Column(name = "email")
	@NotEmpty(message = "メールアドレスを入力してください")
	@Pattern(regexp = "^[a-zA-Z0-9_.+-]+@([a-zA-Z0-9][a-zA-Z0-9-]*[a-zA-Z0-9]*\\.)+[a-zA-Z]{2,}$", message = "メールアドレスの形式が正しくありません", groups = Group1.class)
	private String email;

	@Column(name = "password", nullable = false)
	@Length(min = 8, max = 20, message = "パスワードは８文字〜２０文字以内にしてください")
	@Pattern(regexp = "[a-zA-Z0-9]+", message = "パスワードが不正です", groups = Group1.class)
	private String password;
	
	@Column(name="profileImage",nullable=true)
	private String profileImage;

	@Column
	private String uuid;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}
	

}
