package vn.sogo.lmscms.controller;

import vn.sogo.lmscms.model.response.ApiResponse;

public class BaseController {
	protected <T> ApiResponse<T> responseResult(T data){
		ApiResponse<T> result = new ApiResponse<>();
		
		result.setData(data);
		
		return result;
	}
}
