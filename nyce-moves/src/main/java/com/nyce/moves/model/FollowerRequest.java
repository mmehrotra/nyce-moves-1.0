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
 * FollowerRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-02-22T15:38:48.275+05:30")

public class FollowerRequest   {
  @JsonProperty("playerId")
  private Long playerId = null;

  @JsonProperty("followerId")
  private Long followerId = null;

  public FollowerRequest playerId(Long playerId) {
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

  public FollowerRequest followerId(Long followerId) {
    this.followerId = followerId;
    return this;
  }

  /**
   * Get followerId
   * @return followerId
  **/
  @ApiModelProperty(value = "")


  public Long getFollowerId() {
    return followerId;
  }

  public void setFollowerId(Long followerId) {
    this.followerId = followerId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FollowerRequest followerRequest = (FollowerRequest) o;
    return Objects.equals(this.playerId, followerRequest.playerId) &&
        Objects.equals(this.followerId, followerRequest.followerId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(playerId, followerId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FollowerRequest {\n");
    
    sb.append("    playerId: ").append(toIndentedString(playerId)).append("\n");
    sb.append("    followerId: ").append(toIndentedString(followerId)).append("\n");
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

