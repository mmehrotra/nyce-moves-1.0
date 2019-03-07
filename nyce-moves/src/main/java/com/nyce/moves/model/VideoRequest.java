package com.nyce.moves.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * VideoRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-03-07T10:16:43.744+05:30")

public class VideoRequest   {
  @JsonProperty("playerId")
  private Long playerId = null;

  @JsonProperty("title")
  private String title = null;

  @JsonProperty("videoUrl")
  private String videoUrl = null;

  @JsonProperty("description")
  private String description = null;

  public VideoRequest playerId(Long playerId) {
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

  public VideoRequest title(String title) {
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

  public VideoRequest videoUrl(String videoUrl) {
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

  public VideoRequest description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
  **/
  @ApiModelProperty(value = "")


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VideoRequest videoRequest = (VideoRequest) o;
    return Objects.equals(this.playerId, videoRequest.playerId) &&
        Objects.equals(this.title, videoRequest.title) &&
        Objects.equals(this.videoUrl, videoRequest.videoUrl) &&
        Objects.equals(this.description, videoRequest.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(playerId, title, videoUrl, description);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VideoRequest {\n");
    
    sb.append("    playerId: ").append(toIndentedString(playerId)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    videoUrl: ").append(toIndentedString(videoUrl)).append("\n");
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
}

