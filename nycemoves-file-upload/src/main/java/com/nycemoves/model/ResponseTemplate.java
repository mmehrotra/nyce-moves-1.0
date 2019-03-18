package com.nycemoves.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseTemplate {
	/**
	 * Gets or Sets status
	 */
	public enum StatusEnum {
		SUCCESS("SUCCESS"),

		FAILURE("FAILURE");

		private String value;

		StatusEnum(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static StatusEnum fromValue(String text) {
			for (StatusEnum b : StatusEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

	@JsonProperty("status")
	private StatusEnum status = null;

	@JsonProperty("code")
	private String code = null;

	@JsonProperty("message")
	private String message = null;

	public ResponseTemplate status(StatusEnum status) {
		this.status = status;
		return this;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public ResponseTemplate code(String code) {
		this.code = code;
		return this;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public ResponseTemplate message(String message) {
		this.message = message;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ResponseTemplate responseTemplate = (ResponseTemplate) o;
		return Objects.equals(this.status, responseTemplate.status) && Objects.equals(this.code, responseTemplate.code) && Objects.equals(this.message, responseTemplate.message);
	}

	@Override
	public int hashCode() {
		return Objects.hash(status, code, message);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ResponseTemplate {\n");

		sb.append("    status: ").append(toIndentedString(status)).append("\n");
		sb.append("    code: ").append(toIndentedString(code)).append("\n");
		sb.append("    message: ").append(toIndentedString(message)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
