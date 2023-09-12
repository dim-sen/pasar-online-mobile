package com.dimsen.pasaronline.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class DataItem implements Parcelable{

	@SerializedName("item_weight")
	private int itemWeight;

	@SerializedName("item_price")
	private int itemPrice;

	@SerializedName("item_image")
	private String itemImage;

	@SerializedName("item_name")
	private String itemName;

	@SerializedName("id")
	private int id;

	@SerializedName("category_dao")
	private CategoryDao categoryDao;

	protected DataItem(Parcel in) {
		itemWeight = in.readInt();
		itemPrice = in.readInt();
		itemImage = in.readString();
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

	public int getItemWeight() {
		return itemWeight;
	}

	public void setItemWeight(int itemWeight) {
		this.itemWeight = itemWeight;
	}

	public int getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getItemImage() {
		return itemImage;
	}

	public void setItemImage(String itemImage) {
		this.itemImage = itemImage;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Override
	public String toString() {
		return "DataItem{" +
				"itemWeight=" + itemWeight +
				", itemPrice=" + itemPrice +
				", itemImage='" + itemImage + '\'' +
				", itemName='" + itemName + '\'' +
				", id=" + id +
				", categoryDao=" + categoryDao +
				'}';
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(@NonNull Parcel dest, int flags) {
		dest.writeInt(itemWeight);
		dest.writeInt(itemPrice);
		dest.writeString(itemImage);
		dest.writeString(itemName);
		dest.writeInt(id);
	}
}