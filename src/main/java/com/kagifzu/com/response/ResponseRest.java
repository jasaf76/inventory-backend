package com.kagifzu.com.response;

import java.util.ArrayList;
import java.util.HashMap;

public class ResponseRest {

	private ArrayList<HashMap<String, String>> metadata = new ArrayList<>();

	public ArrayList<HashMap<String, String>> getMetadata() {
		return metadata;
	}

	public void setMetadata(String type, String code, String date) {
		HashMap<String, String> map = new HashMap<String, String>();

		map.put("type", type);
		map.put("date", date);
		map.put("code", code);

		metadata.add(map);
	}

}
