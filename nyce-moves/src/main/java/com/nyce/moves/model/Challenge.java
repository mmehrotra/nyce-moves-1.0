package com.nyce.moves.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;

/**
 * Challenge
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-05-18T16:26:14.015+05:30")
@NamedQuery(name = "Challenge.fetchChallengesByChallengeIdList", query = "SELECT e FROM Challenge e WHERE e.challangeId IN (:inclList) ORDER BY e.challangeCreatedBy DESC")
@Entity
public class Challenge {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty("challangeId")
	private Long challangeId = null;

	@JsonProperty("challangeName")
	private String challangeName = null;

	@JsonProperty("challangeCreatedBy")
	private Long challangeCreatedBy = null;

	@JsonProperty("challangeOwnerName")
	private String challangeOwnerName = null;

	@JsonProperty("challanageCreationTime")
	private Timestamp challanageCreationTime = null;

	@JsonProperty("ownerProfileImageUrl")
	private String ownerProfileImageUrl = null;

	@JsonProperty("profilePreSignUrl")
	private String profilePreSignUrl = null;

	@JsonProperty("challengeParticipants")
	@ManyToMany(cascade = { CascadeType.ALL })
	private List<ChallengeParticipants> challengeParticipants = null;

	/**
	 * Gets or Sets challengeStatus
	 */
	public enum ChallengeStatusEnum {
		OPEN("OPEN"),

		CLOSED("CLOSED");

		private String value;

		ChallengeStatusEnum(String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static ChallengeStatusEnum fromValue(String text) {
			for (ChallengeStatusEnum b : ChallengeStatusEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

	@JsonProperty("challengeStatus")
	private ChallengeStatusEnum challengeStatus = null;

	public Challenge challangeId(Long challangeId) {
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

	public Challenge challangeName(String challangeName) {
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

	public Challenge challangeCreatedBy(Long challangeCreatedBy) {
		this.challangeCreatedBy = challangeCreatedBy;
		return this;
	}

	/**
	 * Get challangeCreatedBy
	 * 
	 * @return challangeCreatedBy
	 **/
	@ApiModelProperty(value = "")

	public Long getChallangeCreatedBy() {
		return challangeCreatedBy;
	}

	public void setChallangeCreatedBy(Long challangeCreatedBy) {
		this.challangeCreatedBy = challangeCreatedBy;
	}

	public Challenge challangeOwnerName(String challangeOwnerName) {
		this.challangeOwnerName = challangeOwnerName;
		return this;
	}

	/**
	 * Get challangeOwnerName
	 * 
	 * @return challangeOwnerName
	 **/
	@ApiModelProperty(value = "")

	public String getChallangeOwnerName() {
		return challangeOwnerName;
	}

	public void setChallangeOwnerName(String challangeOwnerName) {
		this.challangeOwnerName = challangeOwnerName;
	}

	public Challenge challanageCreationTime(Timestamp challanageCreationTime) {
		this.challanageCreationTime = challanageCreationTime;
		return this;
	}

	/**
	 * Get challanageCreationTime
	 * 
	 * @return challanageCreationTime
	 **/
	@ApiModelProperty(value = "")

	@Valid

	public Timestamp getChallanageCreationTime() {
		return challanageCreationTime;
	}

	public void setChallanageCreationTime(Timestamp challanageCreationTime) {
		this.challanageCreationTime = challanageCreationTime;
	}

	public Challenge ownerProfileImageUrl(String ownerProfileImageUrl) {
		this.ownerProfileImageUrl = ownerProfileImageUrl;
		return this;
	}

	/**
	 * Get ownerProfileImageUrl
	 * 
	 * @return ownerProfileImageUrl
	 **/
	@ApiModelProperty(value = "")

	public String getOwnerProfileImageUrl() {
		return ownerProfileImageUrl;
	}

	public void setOwnerProfileImageUrl(String ownerProfileImageUrl) {
		this.ownerProfileImageUrl = ownerProfileImageUrl;
	}

	public Challenge profilePreSignUrl(String profilePreSignUrl) {
		this.profilePreSignUrl = profilePreSignUrl;
		return this;
	}

	/**
	 * Get profilePreSignUrl
	 * 
	 * @return profilePreSignUrl
	 **/
	@ApiModelProperty(value = "")

	public String getProfilePreSignUrl() {
		return profilePreSignUrl;
	}

	public void setProfilePreSignUrl(String profilePreSignUrl) {
		this.profilePreSignUrl = profilePreSignUrl;
	}

	public List<ChallengeParticipants> getChallengeParticipants() {
		return challengeParticipants;
	}

	public void setChallengeParticipants(List<ChallengeParticipants> challengeParticipants) {
		this.challengeParticipants = challengeParticipants;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Challenge challenge = (Challenge) o;
		return Objects.equals(this.challangeId, challenge.challangeId) && Objects.equals(this.challangeName, challenge.challangeName) && Objects.equals(this.challangeCreatedBy, challenge.challangeCreatedBy) && Objects.equals(this.challangeOwnerName, challenge.challangeOwnerName) && Objects.equals(this.challanageCreationTime, challenge.challanageCreationTime) && Objects.equals(this.ownerProfileImageUrl, challenge.ownerProfileImageUrl) && Objects.equals(this.profilePreSignUrl, challenge.profilePreSignUrl);
	}

	@Override
	public int hashCode() {
		return Objects.hash(challangeId, challangeName, challangeCreatedBy, challangeOwnerName, challanageCreationTime, ownerProfileImageUrl, profilePreSignUrl);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Challenge {\n");

		sb.append("    challangeId: ").append(toIndentedString(challangeId)).append("\n");
		sb.append("    challangeName: ").append(toIndentedString(challangeName)).append("\n");
		sb.append("    challangeCreatedBy: ").append(toIndentedString(challangeCreatedBy)).append("\n");
		sb.append("    challangeOwnerName: ").append(toIndentedString(challangeOwnerName)).append("\n");
		sb.append("    challanageCreationTime: ").append(toIndentedString(challanageCreationTime)).append("\n");
		sb.append("    ownerProfileImageUrl: ").append(toIndentedString(ownerProfileImageUrl)).append("\n");
		sb.append("    profilePreSignUrl: ").append(toIndentedString(profilePreSignUrl)).append("\n");
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

	public Challenge challengeStatus(ChallengeStatusEnum challengeStatus) {
		this.challengeStatus = challengeStatus;
		return this;
	}

	/**
	 * Get challengeStatus
	 * 
	 * @return challengeStatus
	 **/
	@ApiModelProperty(value = "")

	public ChallengeStatusEnum getChallengeStatus() {
		return challengeStatus;
	}

	public void setChallengeStatus(ChallengeStatusEnum challengeStatus) {
		this.challengeStatus = challengeStatus;
	}

}
