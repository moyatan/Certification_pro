package Certification.app.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="comments")
public class Comments {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	@NotNull
	private long id;
	
	@Column(name="content",nullable=false)
	@NotEmpty(message="コメントを入力してください")
	private String content;
	
	@Column(name="created_at",nullable=false)
	private Timestamp created_at;
	
	@ManyToOne
	private Articles articles;
	
	@ManyToOne
	private Account account;
	
	public Comments() {
		super();
		articles = new Articles();
		account = new Account();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Articles getArticles() {
		return articles;
	}

	public void setArticles(Articles articles) {
		this.articles = articles;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}
