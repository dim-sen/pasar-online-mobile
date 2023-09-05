package com.dimsen.pasaronline.data;

import com.google.gson.annotations.SerializedName;

public class CategoryDao{

	@SerializedName("createdAt")
	private String createdAt;

	@SerializedName("deleted")
	private boolean deleted;

	@SerializedName("createdBy")
	private String createdBy;

	@SerializedName("id")
	private int id;

	@SerializedName("categoryName")
	private String categoryName;

	@SerializedName("updatedAt")
	private Object updatedAt;

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setDeleted(boolean deleted){
		this.deleted = deleted;
	}

	public boolean isDeleted(){
		return deleted;
	}

	public void setCreatedBy(String createdBy){
		this.createdBy = createdBy;
	}

	public String getCreatedBy(){
		return createdBy;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setCategoryName(String categoryName){
		this.categoryName = categoryName;
	}

	public String getCategoryName(){
		return categoryName;
	}

	public void setUpdatedAt(Object updatedAt){
		this.updatedAt = updatedAt;
	}

	public Object getUpdatedAt(){
		return updatedAt;
	}

	@Override
 	public String toString(){
		return 
			"CategoryDao{" + 
			"createdAt = '" + createdAt + '\'' + 
			",deleted = '" + deleted + '\'' + 
			",createdBy = '" + createdBy + '\'' + 
			",id = '" + id + '\'' + 
			",categoryName = '" + categoryName + '\'' + 
			",updatedAt = '" + updatedAt + '\'' + 
			"}";
		}
}