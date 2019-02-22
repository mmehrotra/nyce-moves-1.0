package com.nyce.moves.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Video
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-02-22T15:38:48.275+05:30")

public class Video   {
  @JsonProperty("videoId")
  private Long videoId = null;

  @JsonProperty("playerId")
  private Long playerId = null;

  @JsonProperty("title")
  private String title = null;

  @JsonProperty("videoUrl")
  private String videoUrl = null;

  @JsonProperty("descriptioin")
  private String descriptioin = null;

  @JsonProperty("postedTimestamp")
  private OffsetDateTime postedTimestamp = null;

  @JsonProperty("applauds")
  private Integer applauds = null;

  public Video videoId(Long videoId) {
    this.videoId = videoId;
    return this;
  }

  /**
   * Get videoId
   * @return videoId
  **/
  @ApiModelProperty(value = "")


  public Long getVideoId() {
    return videoId;
  }

  public void setVideoId(Long videoId) {
    this.videoId = videoId;
  }

  public Video playerId(Long playerId) {
    this.playerId = playerId;
    return this;
  }

  /**
   * Get playerId
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
   * @return videoUrl
  **/
  @ApiModelProperty(value = "")


  public String getVideoUrl() {
    return videoUrl;
  }

  public void setVideoUrl(String videoUrl) {
    this.videoUrl = videoUrl;
  }

  public Video descriptioin(String descriptioin) {
    this.descriptioin = descriptioin;
    return this;
  }

  /**
   * Get descriptioin
   * @return descriptioin
  **/
  @ApiModelProperty(value = "")


  public String getDescriptioin() {
    return descriptioin;
  }

  public void setDescriptioin(String descriptioin) {
    this.descriptioin = descriptioin;
  }

  public Video postedTimestamp(OffsetDateTime postedTimestamp) {
    this.postedTimestamp = postedTimestamp;
    return this;
  }

  /**
   * Get postedTimestamp
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

  public Video applauds(Integer applauds) {
    this.applauds = applauds;
    return this;
  }

  /**
   * Get applauds
   * @return applauds
  **/
  @ApiModelProperty(value = "")


  public Integer getApplauds() {
    return applauds;
  }

  public void setApplauds(Integer applauds) {
    this.applauds = applauds;
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
    return Objects.equals(this.videoId, video.videoId) &&
        Objects.equals(this.playerId, video.playerId) &&
        Objects.equals(this.title, video.title) &&
        Objects.equals(this.videoUrl, video.videoUrl) &&
        Objects.equals(this.descriptioin, video.descriptioin) &&
        Objects.equals(this.postedTimestamp, video.postedTimestamp) &&
        Objects.equals(this.applauds, video.applauds);
  }

  @Override
  public int hashCode() {
    return Objects.hash(videoId, playerId, title, videoUrl, descriptioin, postedTimestamp, applauds);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Video {\n");
    
    sb.append("    videoId: ").append(toIndentedString(videoId)).append("\n");
    sb.append("    playerId: ").append(toIndentedString(playerId)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    videoUrl: ").append(toIndentedString(videoUrl)).append("\n");
    sb.append("    descriptioin: ").append(toIndentedString(descriptioin)).append("\n");
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
}

