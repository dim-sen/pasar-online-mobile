package com.dimsen.pasaronline.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Item implements Parcelable {
	@SerializedName("item_weight")
	private int itemWeight;
	@SerializedName("item_price")
	private int itemPrice;
	@SerializedName("item_image")
	private String itemImage;
	@SerializedName("item_name")
	private String itemName;
	@SerializedName("id")
	private Long id;
	@SerializedName("item_stock")
	private int itemStock;
	@SerializedName("item_description")
	private String itemDescription;
	@SerializedName("category_dao")
	private Category categoryDao;

	protected Item(Parcel in) {
		itemWeight = in.readInt();
		itemPrice = in.readInt();
		itemImage = in.readString();
		itemName = in.readString();
		if (in.readByte() == 0) {
			id = null;
		} else {
			id = in.readLong();
		}
		itemStock = in.readInt();
		itemDescription = in.readString();
	}

	public static final Creator<Item> CREATOR = new Creator<Item>() {
		@Override
		public Item createFromParcel(Parcel in) {
			return new Item(in);
		}

		@Override
		public Item[] newArray(int size) {
			return new Item[size];
		}
	};

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
		if (id == null) {
			dest.writeByte((byte) 0);
		} else {
			dest.writeByte((byte) 1);
			dest.writeLong(id);
		}
		dest.writeInt(itemStock);
		dest.writeString(itemDescription);
	}
}