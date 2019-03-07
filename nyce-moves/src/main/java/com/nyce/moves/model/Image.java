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
 * Image
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-03-07T10:16:43.744+05:30")

public class Image   {
  @JsonProperty("imageId")
  private Long imageId = null;

  @JsonProperty("playerId")
  private Long playerId = null;

  @JsonProperty("title")
  private String title = null;

  @JsonProperty("imageUrl")
  private String imageUrl = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("applauds")
  private Long applauds = null;

  @JsonProperty("postedTimestamp")
  private OffsetDateTime postedTimestamp = null;

  public Image imageId(Long imageId) {
    this.imageId = imageId;
    return this;
  }

  /**
   * Get imageId
   * @return imageId
  **/
  @ApiModelProperty(value = "")


  public Long getImageId() {
    return imageId;
  }

  public void setImageId(Long imageId) {
    this.imageId = imageId;
  }

  public Image playerId(Long playerId) {
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

  public Image title(String title) {
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

  public Image imageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
    return this;
  }

  /**
   * Get imageUrl
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
   * @return description
  **/
  @ApiModelProperty(value = "")


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Image applauds(Long applauds) {
    this.applauds = applauds;
    return this;
  }

  /**
   * Get applauds
   * @return applauds
  **/
  @ApiModelProperty(value = "")


  public Long getApplauds() {
    return applauds;
  }

  public void setApplauds(Long applauds) {
    this.applauds = applauds;
  }

  public Image postedTimestamp(OffsetDateTime postedTimestamp) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Image image = (Image) o;
    return Objects.equals(this.imageId, image.imageId) &&
        Objects.equals(this.playerId, image.playerId) &&
        Objects.equals(this.title, image.title) &&
        Objects.equals(this.imageUrl, image.imageUrl) &&
        Objects.equals(this.description, image.description) &&
        Objects.equals(this.applauds, image.applauds) &&
        Objects.equals(this.postedTimestamp, image.postedTimestamp);
  }

  @Override
  public int hashCode() {
    return Objects.hash(imageId, playerId, title, imageUrl, description, applauds, postedTimestamp);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Image {\n");
    
    sb.append("    imageId: ").append(toIndentedString(imageId)).append("\n");
    sb.append("    playerId: ").append(toIndentedString(playerId)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    imageUrl: ").append(toIndentedString(imageUrl)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
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
}

