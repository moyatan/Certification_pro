package Certification.app.model;

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

@Entity
@Table(name="qualifications")
public class Qualifications {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	@NotNull
	private long id;
	
	@Column(name="name",nullable=false)
	String name;
	
	@Column(name="name_kana",nullable=false)
	String name_kana;
	
	//一つの資格に対して複数の記事があるためListで受け取る
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

	public String getName_kana() {
		return name_kana;
	}

	public void setName_kana(String name_kana) {
		this.name_kana = name_kana;
	}

	public List<Articles> getArticles() {
		return articles;
	}

	public void setArticles(List<Articles> articles) {
		this.articles = articles;
	}

}
