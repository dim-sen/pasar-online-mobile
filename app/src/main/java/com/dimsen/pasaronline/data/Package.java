package com.dimsen.pasaronline.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Package implements Parcelable {
	@SerializedName("package_name")
	private String packageName;
	@SerializedName("package_weight")
	private int packageWeight;
	@SerializedName("id")
	private Long id;
	@SerializedName("package_image")
	private String packageImage;
	@SerializedName("package_price")
	private int packagePrice;
	@SerializedName("package_description")
	private String packageDescription;

	protected Package(Parcel in) {
		packageName = in.readString();
		packageWeight = in.readInt();
		if (in.readByte() == 0) {
			id = null;
		} else {
			id = in.readLong();
		}
		packageImage = in.readString();
		packagePrice = in.readInt();
		packageDescription = in.readString();
	}

	public static final Creator<Package> CREATOR = new Creator<Package>() {
		@Override
		public Package createFromParcel(Parcel in) {
			return new Package(in);
		}

		@Override
		public Package[] newArray(int size) {
			return new Package[size];
		}
	};

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(@NonNull Parcel dest, int flags) {
		dest.writeString(packageName);
		dest.writeInt(packageWeight);
		if (id == null) {
			dest.writeByte((byte) 0);
		} else {
			dest.writeByte((byte) 1);
			dest.writeLong(id);
		}
		dest.writeString(packageImage);
		dest.writeInt(packagePrice);
		dest.writeString(packageDescription);
	}
}