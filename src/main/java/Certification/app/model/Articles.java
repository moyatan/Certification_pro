package Certification.app.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import Certification.app.groups.Group1;

@Entity
@Table(name="articles")
public class Articles {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	@NotNull
	private long id;
	
	@Column(name="title",length=20,nullable=false)
	@NotEmpty(message="記事のタイトルを入力して下さい")
	@Length(min=0,max=20,message="タイトルは２０文字以内でお願いします",groups=Group1.class)
	private String title;
	
	//改行も保存
	@Lob
	@Column(name="content",nullable=false)
	private String content;
	
	@Column(name="tag",nullable=true)
	private String tag;
	
	@Column(name="view_count")
	private long view_count;
	
	@Column(name="created_at",nullable=false)
	private Timestamp created_at;
	
	@ManyToOne
	@JoinColumn(name="categorymodel_id",nullable=false)
	private CategoryModel categorymodel;
	
	@ManyToOne
	@JoinColumn(name="account_id",nullable=false)
	private Account account;
	
	@Column(name="favorite_count")
	private int favoriteCount;
	
	@Column(name="delete_flag",nullable=true)
	private int delete_flag;
	
//	@ManyToOne
//	private Qualifications qualifications;
//	
//	//記事一つにつきコメントは複数ある
	@OneToMany(cascade=CascadeType.ALL)
	@Column(nullable=true)
	private List<Comments> comments;
	
	@OneToMany(cascade=CascadeType.ALL)
	@Column(nullable=true)
	private List<Favorites> favorites;
	
	public Articles() {
		
		super();
		account = new Account();
		categorymodel = new CategoryModel();
//		//ゲッターを使って呼び出せるように
//		qualifications = new Qualifications();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getView_count() {
		return view_count;
	}

	public void setView_count(long view_count) {
		this.view_count = view_count;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
//
//	public Qualifications getQualifications() {
//		return qualifications;
//	}
//
//	public void setQualifications(Qualifications qualifications) {
//		this.qualifications = qualifications;
//	}
//
	public List<Comments> getComments() {
		return comments;
	}

	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}

	public List<Favorites> getFavorites() {
		return favorites;
	}

	public void setFavorites(List<Favorites> favorites) {
		this.favorites = favorites;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public CategoryModel getCategorymodel() {
		return categorymodel;
	}

	public void setCategorymodel(CategoryModel categorymodel) {
		this.categorymodel = categorymodel;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public int getFavoriteCount() {
		return favoriteCount;
	}

	public void setFavoriteCount(int favoriteCount) {
		this.favoriteCount = favoriteCount;
	}

	public int getDelete_flag() {
		return delete_flag;
	}

	public void setDelete_flag(int delete_flag) {
		this.delete_flag = delete_flag;
	}
	
	

}
