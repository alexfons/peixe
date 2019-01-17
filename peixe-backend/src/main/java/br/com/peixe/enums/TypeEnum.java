package br.com.peixe.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TypeEnum {
	@JsonProperty("local")
	LOCAL,
	@JsonProperty("product")
	PRODUCT,
	@JsonProperty("travel")
	TRAVEL;

}
