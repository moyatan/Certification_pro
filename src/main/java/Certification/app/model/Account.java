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
@Table(name="Users")
public class Account implements UserDetails{
	
	//主キー
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	@NotNull
	private long id;
	
	@Column(name="name",nullable=false)
	private String name;
	
	@Column(name="email",nullable=false,unique=true)
	private String email;
	
	@Column(name="password",nullable=false)
	private String password;
	
	@Column(name="profile",nullable=true)
	@Length(max=200,message="プロフィールは200文字以内に収まるようにしてください")
	private String profile;
	
	@Column(name="profileImage",nullable=true)
	private String profileImage;
	
	@OneToMany(cascade=CascadeType.ALL)
	@Column(nullable=true)
	private List<Favorites> favorites;
	
	@OneToMany(cascade=CascadeType.ALL)
	@Column(nullable = true)
	private List<Articles> articles;
	
	
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

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
	
	@Override
    public String getUsername() {
        return this.email;
    }
	
	@Override
    public boolean isAccountNonExpired() {
        return true;
    }
	
	 @Override
	    public boolean isAccountNonLocked() {
	        return true;
	    }
	 
	 @Override
	    public boolean isCredentialsNonExpired() {
	        return true;
	    }
	 
	 @Override
	    public boolean isEnabled() {
	        return true;
	    }

	public List<Favorites> getFavorites() {
		return favorites;
	}

	public void setFavorites(List<Favorites> favorites) {
		this.favorites = favorites;
	}

	public List<Articles> getArticles() {
		return articles;
	}

	public void setArticles(List<Articles> articles) {
		this.articles = articles;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}
	
	
}
