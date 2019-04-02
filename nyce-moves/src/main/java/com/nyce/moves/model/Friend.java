package com.nyce.moves.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * Friend
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-04-02T14:36:06.953+05:30")
@Entity
public class Friend   {

  @Id	
  @JsonProperty("playerId")
  private Long playerId = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("displayImageUrl")
  private String displayImageUrl = null;

  @JsonProperty("displayImagePreSignedUrl")
  private String displayImagePreSignedUrl = null;

  public Friend playerId(Long playerId) {
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

  public Friend name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(value = "")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Friend displayImageUrl(String displayImageUrl) {
    this.displayImageUrl = displayImageUrl;
    return this;
  }

  /**
   * Get displayImageUrl
   * @return displayImageUrl
  **/
  @ApiModelProperty(value = "")


  public String getDisplayImageUrl() {
    return displayImageUrl;
  }

  public void setDisplayImageUrl(String displayImageUrl) {
    this.displayImageUrl = displayImageUrl;
  }

  public Friend displayImagePreSignedUrl(String displayImagePreSignedUrl) {
    this.displayImagePreSignedUrl = displayImagePreSignedUrl;
    return this;
  }

  /**
   * Get displayImagePreSignedUrl
   * @return displayImagePreSignedUrl
  **/
  @ApiModelProperty(value = "")


  public String getDisplayImagePreSignedUrl() {
    return displayImagePreSignedUrl;
  }

  public void setDisplayImagePreSignedUrl(String displayImagePreSignedUrl) {
    this.displayImagePreSignedUrl = displayImagePreSignedUrl;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Friend friend = (Friend) o;
    return Objects.equals(this.playerId, friend.playerId) &&
        Objects.equals(this.name, friend.name) &&
        Objects.equals(this.displayImageUrl, friend.displayImageUrl) &&
        Objects.equals(this.displayImagePreSignedUrl, friend.displayImagePreSignedUrl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(playerId, name, displayImageUrl, displayImagePreSignedUrl);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Friend {\n");
    
    sb.append("    playerId: ").append(toIndentedString(playerId)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    displayImageUrl: ").append(toIndentedString(displayImageUrl)).append("\n");
    sb.append("    displayImagePreSignedUrl: ").append(toIndentedString(displayImagePreSignedUrl)).append("\n");
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

