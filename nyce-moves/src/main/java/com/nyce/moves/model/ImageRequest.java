package com.nyce.moves.model;

import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * ImageRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-03-07T16:07:52.516+05:30")

public class ImageRequest {
	@JsonProperty("playerId")
	private Long playerId = null;

	@JsonProperty("title")
	private String title = null;

	@JsonProperty("imageUrl")
	private String imageUrl = null;

	@JsonProperty("description")
	private String description = null;

	@JsonProperty("height")
	private Integer height = null;

	@JsonProperty("width")
	private Integer width = null;

	@JsonProperty("challengeName")
	private String challengeName = null;

	public ImageRequest playerId(Long playerId) {
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

	public ImageRequest title(String title) {
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

	public ImageRequest imageUrl(String imageUrl) {
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

	public ImageRequest description(String description) {
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

	public ImageRequest height(Integer height) {
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

	public ImageRequest width(Integer width) {
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
		ImageRequest imageRequest = (ImageRequest) o;
		return Objects.equals(this.playerId, imageRequest.playerId) && Objects.equals(this.title, imageRequest.title) && Objects.equals(this.imageUrl, imageRequest.imageUrl) && Objects.equals(this.description, imageRequest.description);
	}

	@Override
	public int hashCode() {
		return Objects.hash(playerId, title, imageUrl, description);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ImageRequest {\n");

		sb.append("    playerId: ").append(toIndentedString(playerId)).append("\n");
		sb.append("    title: ").append(toIndentedString(title)).append("\n");
		sb.append("    imageUrl: ").append(toIndentedString(imageUrl)).append("\n");
		sb.append("    description: ").append(toIndentedString(description)).append("\n");
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

	public String getChallengeName() {
		return challengeName;
	}

	public void setChallengeName(String challengeName) {
		this.challengeName = challengeName;
	}

}
