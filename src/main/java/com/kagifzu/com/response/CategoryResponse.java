package com.kagifzu.com.response;

import java.util.List;

import com.kagifzu.com.model.Category;

import lombok.Data;

@Data
public class CategoryResponse {

	private List<Category> category;
}
