package com.dimsen.pasaronline.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class DataItem implements Parcelable {

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

	public DataItem(Parcel in) {
		itemWeight = in.readInt();
		itemPrice = in.readInt();
		itemName = in.readString();
		id = in.readInt();
	}

	public static final Creator<DataItem> CREATOR = new Creator<DataItem>() {
		@Override
		public DataItem createFromParcel(Parcel in) {
			return new DataItem(in);
		}

		@Override
		public DataItem[] newArray(int size) {
			return new DataItem[size];
		}
	};

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

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(@NonNull Parcel dest, int flags) {
		dest.writeInt(itemWeight);
		dest.writeInt(itemPrice);
		dest.writeString(itemName);
		dest.writeInt(id);
	}
}