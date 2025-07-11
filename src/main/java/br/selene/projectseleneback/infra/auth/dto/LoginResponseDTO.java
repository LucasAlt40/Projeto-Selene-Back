package br.selene.projectseleneback.infra.auth.dto;

public class LoginResponseDTO {
	private String token;

	private Long expiresIn;

	public String getToken() {
		return token;
	}

	public Long getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(Long expiresIn) {
		this.expiresIn = expiresIn;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
