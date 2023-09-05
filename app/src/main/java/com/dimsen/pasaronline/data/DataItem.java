package com.dimsen.pasaronline.data;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("item_weight")
	private int itemWeight;

	@SerializedName("item_price")
	private int itemPrice;

	@SerializedName("item_image")
	private Object itemImage;

	@SerializedName("item_name")
	private String itemName;

	@SerializedName("id")
	private int id;

	@SerializedName("category_dao")
	private CategoryDao categoryDao;

	public void setItemWeight(int itemWeight){
		this.itemWeight = itemWeight;
	}

	public int getItemWeight(){
		return itemWeight;
	}

	public void setItemPrice(int itemPrice){
		this.itemPrice = itemPrice;
	}

	public int getItemPrice(){
		return itemPrice;
	}

	public void setItemImage(Object itemImage){
		this.itemImage = itemImage;
	}

	public Object getItemImage(){
		return itemImage;
	}

	public void setItemName(String itemName){
		this.itemName = itemName;
	}

	public String getItemName(){
		return itemName;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setCategoryDao(CategoryDao categoryDao){
		this.categoryDao = categoryDao;
	}

	public CategoryDao getCategoryDao(){
		return categoryDao;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"item_weight = '" + itemWeight + '\'' + 
			",item_price = '" + itemPrice + '\'' + 
			",item_image = '" + itemImage + '\'' + 
			",item_name = '" + itemName + '\'' + 
			",id = '" + id + '\'' + 
			",category_dao = '" + categoryDao + '\'' + 
			"}";
		}
}