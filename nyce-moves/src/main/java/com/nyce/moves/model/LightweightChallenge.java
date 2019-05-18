package com.nyce.moves.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * Challenge
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-05-18T16:26:14.015+05:30")
@Entity
public class LightweightChallenge {

	@Id
	@JsonProperty("challangeId")
	private Long challangeId = null;

	@JsonProperty("challangeName")
	private String challangeName = null;

	public LightweightChallenge challangeId(Long challangeId) {
		this.challangeId = challangeId;
		return this;
	}

	/**
	 * Get challangeId
	 * 
	 * @return challangeId
	 **/
	@ApiModelProperty(value = "")

	public Long getChallangeId() {
		return challangeId;
	}

	public void setChallangeId(Long challangeId) {
		this.challangeId = challangeId;
	}

	public LightweightChallenge challangeName(String challangeName) {
		this.challangeName = challangeName;
		return this;
	}

	/**
	 * Get challangeName
	 * 
	 * @return challangeName
	 **/
	@ApiModelProperty(value = "")

	public String getChallangeName() {
		return challangeName;
	}

	public void setChallangeName(String challangeName) {
		this.challangeName = challangeName;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		LightweightChallenge challenge = (LightweightChallenge) o;
		return Objects.equals(this.challangeId, challenge.challangeId) && Objects.equals(this.challangeName, challenge.challangeName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(challangeId, challangeName);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Challenge {\n");

		sb.append("    challangeId: ").append(toIndentedString(challangeId)).append("\n");
		sb.append("    challangeName: ").append(toIndentedString(challangeName)).append("\n");
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
