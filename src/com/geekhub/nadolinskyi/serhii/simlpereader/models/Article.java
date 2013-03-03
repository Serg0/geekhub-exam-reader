package com.geekhub.nadolinskyi.serhii.simlpereader.models;

import java.util.ArrayList;

public class Article {
	//TODO do this class Parcelable
	String 
		title,
		link,
		comments,
		pubDate,
		creator,
		description,
		content,
		comments_rss,
		splash_comments,
		pic;
		
	ArrayList<String> categories;
	
	public Article(){
		title = "";
		link = "";
		comments = "";
		pubDate = "";
		creator = "";
		description = "";
		content = "";
		comments_rss = "";
		splash_comments = "";
		pic = "";
		categories = new ArrayList<String>();
		
	}

	public String getTitle() {
		return title;
	}

	public String getLink() {
		return link;
	}

	public String getComments() {
		return comments;
	}

	public String getPubDate() {
		return pubDate;
	}

	public String getCreator() {
		return creator;
	}

	public String getDescription() {
		return description;
	}

	public String getContent() {
		return content;
	}

	public String getComments_rss() {
		return comments_rss;
	}

	public String getSplash_comments() {
		return splash_comments;
	}

	public ArrayList<String> getCategories() {
		return categories;
	}

	public Article setTitle(String title) {
		this.title = title;
		return this;
	}

	public Article setLink(String link) {
		this.link = link;
		return this;
	}

	public Article setComments(String comments) {
		this.comments = comments;
		return this;
	}

	public Article setPubDate(String pubDate) {
		this.pubDate = pubDate;
		return this;
	}

	public Article setCreator(String creator) {
		this.creator = creator;
		return this;
	}

	public Article setDescription(String description) {
		this.description = description;
		return this;
	}

	public Article setContent(String content) {
		this.content = content;
		return this;
	}

	public Article setComments_rss(String comments_rss) {
		this.comments_rss = comments_rss;
		return this;
	}

	public Article setSplash_comments(String splash_comments) {
		this.splash_comments = splash_comments;
		return this;
	}

	public Article setCategories(ArrayList<String> categories) {
		this.categories = categories;
		return this;
	}
	
	public Article addCategory(String category){
		this.categories.add(category);
		return this;
	}

	public String getPic() {
		return pic;
	}

	public Article setPic(String pic) {
		this.pic = pic;
		return this;
	}
	
}
