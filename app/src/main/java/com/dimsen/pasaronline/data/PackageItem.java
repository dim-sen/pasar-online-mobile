package com.dimsen.pasaronline.data;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class PackageItem {
	@SerializedName("item_dao")
	private Item itemDao;
	@SerializedName("id")
	private int id;
	@SerializedName("package_dao")
	private Package packageDao;
}