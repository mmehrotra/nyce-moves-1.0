package com.nyce.moves.model;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * Video
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-03-07T16:07:52.516+05:30")
@Entity
public class Video {
	@JsonProperty("playerId")
	private Long playerId = null;

	@JsonProperty("title")
	private String title = null;

	@JsonProperty("videoUrl")
	private String videoUrl = null;
	
	@JsonProperty("preSignedVideoUrl")
	private String preSignedVideoUrl = null;

	@JsonProperty("description")
	private String description = null;

	@JsonProperty("videoId")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long videoId = null;

	@JsonProperty("postedTimestamp")
	private OffsetDateTime postedTimestamp = null;

	@JsonProperty("applauds")
	private Long applauds = null;

	@OneToMany(cascade = { CascadeType.ALL })
	private List<Comments> comments;

	public Video playerId(Long playerId) {
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

	public Video title(String title) {
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

	public Video videoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
		return this;
	}

	/**
	 * Get videoUrl
	 * 
	 * @return videoUrl
	 **/
	@ApiModelProperty(value = "")

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public Video description(String description) {
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

	public Video videoId(Long videoId) {
		this.videoId = videoId;
		return this;
	}

	/**
	 * Get videoId
	 * 
	 * @return videoId
	 **/
	@ApiModelProperty(value = "")

	public Long getVideoId() {
		return videoId;
	}

	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}

	public Video postedTimestamp(OffsetDateTime postedTimestamp) {
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

	public OffsetDateTime getPostedTimestamp() {
		return postedTimestamp;
	}

	public void setPostedTimestamp(OffsetDateTime postedTimestamp) {
		this.postedTimestamp = postedTimestamp;
	}

	public Video applauds(Long applauds) {
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

	public List<Comments> getComments() {
		return comments;
	}

	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Video video = (Video) o;
		return Objects.equals(this.playerId, video.playerId) && Objects.equals(this.title, video.title) && Objects.equals(this.videoUrl, video.videoUrl) && Objects.equals(this.description, video.description) && Objects.equals(this.videoId, video.videoId) && Objects.equals(this.postedTimestamp, video.postedTimestamp) && Objects.equals(this.applauds, video.applauds);
	}

	@Override
	public int hashCode() {
		return Objects.hash(playerId, title, videoUrl, description, videoId, postedTimestamp, applauds);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Video {\n");

		sb.append("    playerId: ").append(toIndentedString(playerId)).append("\n");
		sb.append("    title: ").append(toIndentedString(title)).append("\n");
		sb.append("    videoUrl: ").append(toIndentedString(videoUrl)).append("\n");
		sb.append("    description: ").append(toIndentedString(description)).append("\n");
		sb.append("    videoId: ").append(toIndentedString(videoId)).append("\n");
		sb.append("    postedTimestamp: ").append(toIndentedString(postedTimestamp)).append("\n");
		sb.append("    applauds: ").append(toIndentedString(applauds)).append("\n");
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

	public String getPreSignedVideoUrl() {
		return preSignedVideoUrl;
	}

	public void setPreSignedVideoUrl(String preSignedVideoUrl) {
		this.preSignedVideoUrl = preSignedVideoUrl;
	}
	
	
}
