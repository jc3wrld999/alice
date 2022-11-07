package com.ub1st.alice;

import javax.validation.constraints.NotBlank;

import lombok.Data;

	
@Data
public class RequestVO {
	
	@Header
	@NotBlank(message = "authorization은 필수값")
	private String authorization;

	@Header
	@NotBlank(message = "adminKey은 필수값")
	private String adminKey;
	
	@Header
	@NotBlank(message = "contentType은 필수값")
	private String contentType;
}
