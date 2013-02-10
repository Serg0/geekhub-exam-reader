package com.geekhub.nadolinskyi.serhii.simlpereader.models;


import java.util.ArrayList;

public class ArticlesArray {
	
	ArrayList <Article> articlesArray;
	
	public ArticlesArray (){
		articlesArray = new ArrayList<Article>();
	}

	public ArrayList<Article> getArticlesArray() {
		return articlesArray;
	}

	public void setArticlesArray(ArrayList<Article> articlesArray) {
		this.articlesArray = articlesArray;
	}
	
	public void add (Article article) {
		this.articlesArray.add(article);
	}
}
