package com.geekhub.nadolinskyi.serhii.simlpereader.models;

public class Article {
	
	String title, subtitle, id, date, content, link, picture;

	public String getPicture() {
		return picture;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public String getTitle() {
		return title;
	}

	public String getId() {
		return id;
	}

	public String getDate() {
		return date;
	}

	public String getContent() {
		return content;
	}

	public String getLink() {
		return link;
	}

	public Article setTitle(String title) {
		this.title = title;
		return this;
	}

	public Article setId(String id) {
		this.id = id;
		return this;
	}

	public Article setDate(String date) {
		this.date = date;
		return this;
	}

	public Article setContent(String content) {
		this.content = content;
		return this;
	}

	public Article setLink(String link) {
		this.link = link;
		return this;
	}
	
	public Article setSubtitle(String subtitle) {
		this.subtitle = subtitle;
		return this;
	}

	public Article setPicture(String picture) {
		this.picture = picture;
		return this;
	}

}
