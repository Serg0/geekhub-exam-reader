package com.geekhub.nadolinskyi.serhii.simlpereader.models;

import java.util.ArrayList;

public class DataResponceEntity {
	
	private int responceCode;
	private int currentPage;
	private String responceMessage;
	private boolean hasNextPage;
	
	private ArrayList<Article> articlesArray;
	
	public DataResponceEntity(){
		setHasNextPage(true);
		setCurrentPage(1);
	}
	public int getResponceCode() {
		return responceCode;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public String getResponceMessage() {
		return responceMessage;
	}

	public boolean hasNextPage() {
		return hasNextPage;
	}

	public ArrayList<Article> getArticlesArray() {
		return articlesArray;
	}

	public DataResponceEntity setResponceCode(int responceCode) {
		this.responceCode = responceCode;
		return this;
	}

	private void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public DataResponceEntity setResponceMessage(String responceMessage) {
		this.responceMessage = responceMessage;
		return this;
	}

	private void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	public DataResponceEntity setArticlesArray(ArrayList<Article> articlesArray) {
		this.articlesArray = articlesArray;
		return this;
	}

	public int incCurrentPage (){
		currentPage++;
		return getCurrentPage();
	}
	
	public DataResponceEntity decCurrentPage (){
		currentPage--;
		return this;
	}
	
	public void setHasNoNextPage() {
		this.hasNextPage = false;
	}
/*	public boolean hasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}
	public int getCurrent_page() {
		return mCurrentPage;
	}

	private void setCurrent_page(int current_page) {
		this.mCurrentPage = current_page;
	}
	
	public int getResponce_code() {
		return responce_code;
	}

	public String getResponce_message() {
		return responce_message;
	}

	public ArrayList<Article> getArticlesArray() {
		return articlesArray;
	}
	
	public void setResponce_code(int responce_code) {
		this.responce_code = responce_code;
	}

	public void setResponce_message(String responce_message) {
		this.responce_message = responce_message;
	}

	public void setArticlesArray(ArrayList<Article> articlesArray) {
		this.articlesArray = articlesArray;
	}*/
	
}
