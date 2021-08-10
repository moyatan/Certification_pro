package Certification.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;



@Entity
@Table(name="profile")
public class Profile {
	
	@Id
	@NotNull
	@Column(name="account_id")
	private long id;
	
	@Column(name="profile",nullable=true)
	@Length(max=200,message="プロフィールは200文字以内に収まるようにしてください")
	private String profile;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}
	
}
