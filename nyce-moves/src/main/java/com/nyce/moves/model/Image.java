package com.nyce.moves.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * Image
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-03-07T16:07:52.516+05:30")
@NamedQuery(name = "Image.fetchImagesByPlayerIdList", query = "SELECT e FROM Image e WHERE e.playerId IN (:inclList) ORDER BY e.postedTimestamp DESC")
@Entity
public class Image {
	@JsonProperty("playerId")
	private Long playerId = null;

	@JsonProperty("title")
	private String title = null;

	@JsonProperty("imageUrl")
	private String imageUrl = null;

	@JsonProperty("preSignedImageUrl")
	private String preSignedImageUrl = null;

	@JsonProperty("description")
	private String description = null;

	@JsonProperty("imageId")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long imageId = null;

	@JsonProperty("applauds")
	private Long applauds = null;
	
	@JsonProperty
	@ElementCollection
	private List<Long> applaudDoneByPlayerIds = null;

	@JsonProperty("postedTimestamp")
	private Timestamp postedTimestamp = null;

	@JsonProperty("height")
	private Integer height = null;

	@JsonProperty("width")
	private Integer width = null;

	@OneToMany(cascade = { CascadeType.ALL })
	private List<Comments> comments;

	@JsonProperty("challengeName")
	private String challengeName = null;

	@JsonProperty("challengeId")
	private Long challengeId = null;
	
	public Image playerId(Long playerId) {
		this.playerId = playerId;
		return this;
	}

	/**
	 * Get playerId
	 * 
	 * @return playerId
	 **/
	@ApiModelProperty(value = "")

	public Long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}

	public Image title(String title) {
		this.title = title;
		return this;
	}

	/**
	 * Get title
	 * 
	 * @return title
	 **/
	@ApiModelProperty(value = "")

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Image imageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
		return this;
	}

	/**
	 * Get imageUrl
	 * 
	 * @return imageUrl
	 **/
	@ApiModelProperty(value = "")

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Image description(String description) {
		this.description = description;
		return this;
	}

	/**
	 * Get description
	 * 
	 * @return description
	 **/
	@ApiModelProperty(value = "")

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Image imageId(Long imageId) {
		this.imageId = imageId;
		return this;
	}

	/**
	 * Get imageId
	 * 
	 * @return imageId
	 **/
	@ApiModelProperty(value = "")

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	public Image applauds(Long applauds) {
		this.applauds = applauds;
		return this;
	}

	/**
	 * Get applauds
	 * 
	 * @return applauds
	 **/
	@ApiModelProperty(value = "")

	public Long getApplauds() {
		return applauds;
	}

	public void setApplauds(Long applauds) {
		this.applauds = applauds;
	}

	public Image postedTimestamp(Timestamp postedTimestamp) {
		this.postedTimestamp = postedTimestamp;
		return this;
	}

	/**
	 * Get postedTimestamp
	 * 
	 * @return postedTimestamp
	 **/
	@ApiModelProperty(value = "")

	@Valid

	public Timestamp getPostedTimestamp() {
		return postedTimestamp;
	}

	public void setPostedTimestamp(Timestamp postedTimestamp) {
		this.postedTimestamp = postedTimestamp;
	}

	public List<Comments> getComments() {
		return comments;
	}

	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}
	
	public List<Long> getApplaudDoneByPlayerIds() {
		return applaudDoneByPlayerIds;
	}

	public void setApplaudDoneByPlayerIds(List<Long> applaudDoneByPlayerIds) {
		this.applaudDoneByPlayerIds = applaudDoneByPlayerIds;
	}

	public Image height(Integer height) {
		this.height = height;
		return this;
	}

	/**
	 * Get height
	 * 
	 * @return height
	 **/
	@ApiModelProperty(value = "")

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Image width(Integer width) {
		this.width = width;
		return this;
	}

	/**
	 * Get width
	 * 
	 * @return width
	 **/
	@ApiModelProperty(value = "")

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Image image = (Image) o;
		return Objects.equals(this.playerId, image.playerId) && Objects.equals(this.title, image.title) && Objects.equals(this.imageUrl, image.imageUrl) && Objects.equals(this.description, image.description) && Objects.equals(this.imageId, image.imageId) && Objects.equals(this.applauds, image.applauds) && Objects.equals(this.postedTimestamp, image.postedTimestamp);
	}

	@Override
	public int hashCode() {
		return Objects.hash(playerId, title, imageUrl, description, imageId, applauds, postedTimestamp);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Image {\n");

		sb.append("    playerId: ").append(toIndentedString(playerId)).append("\n");
		sb.append("    title: ").append(toIndentedString(title)).append("\n");
		sb.append("    imageUrl: ").append(toIndentedString(imageUrl)).append("\n");
		sb.append("    description: ").append(toIndentedString(description)).append("\n");
		sb.append("    imageId: ").append(toIndentedString(imageId)).append("\n");
		sb.append("    applauds: ").append(toIndentedString(applauds)).append("\n");
		sb.append("    postedTimestamp: ").append(toIndentedString(postedTimestamp)).append("\n");
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

	public String getPreSignedImageUrl() {
		return preSignedImageUrl;
	}

	public void setPreSignedImageUrl(String preSignedImageUrl) {
		this.preSignedImageUrl = preSignedImageUrl;
	}

	public String getChallengeName() {
		return challengeName;
	}

	public void setChallengeName(String challengeName) {
		this.challengeName = challengeName;
	}

	public Long getChallengeId() {
		return challengeId;
	}

	public void setChallengeId(Long challengeId) {
		this.challengeId = challengeId;
	}
	
}
