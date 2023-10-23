package com.dimsen.pasaronline.data;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Category {
	@SerializedName("category_name")
	private String categoryName;
	@SerializedName("id")
	private Long id;
}