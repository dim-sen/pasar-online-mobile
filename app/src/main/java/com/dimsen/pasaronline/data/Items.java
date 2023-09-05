package com.dimsen.pasaronline.data;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Items{

	@SerializedName("data")
	private List<DataItem> data;

	@SerializedName("timestamp")
	private String timestamp;

	@SerializedName("status")
	private Status status;

	public void setData(List<DataItem> data){
		this.data = data;
	}

	public List<DataItem> getData(){
		return data;
	}

	public void setTimestamp(String timestamp){
		this.timestamp = timestamp;
	}

	public String getTimestamp(){
		return timestamp;
	}

	public void setStatus(Status status){
		this.status = status;
	}

	public Status getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"Items{" + 
			"data = '" + data + '\'' + 
			",timestamp = '" + timestamp + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}