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
 * FollowsRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-02-22T15:38:48.275+05:30")

public class FollowsRequest   {
  @JsonProperty("playerId")
  private Long playerId = null;

  @JsonProperty("followsId")
  private Long followsId = null;

  @JsonProperty("followsTimestamp")
  private OffsetDateTime followsTimestamp = null;

  public FollowsRequest playerId(Long playerId) {
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

  public FollowsRequest followsId(Long followsId) {
    this.followsId = followsId;
    return this;
  }

  /**
   * Get followsId
   * @return followsId
  **/
  @ApiModelProperty(value = "")


  public Long getFollowsId() {
    return followsId;
  }

  public void setFollowsId(Long followsId) {
    this.followsId = followsId;
  }

  public FollowsRequest followsTimestamp(OffsetDateTime followsTimestamp) {
    this.followsTimestamp = followsTimestamp;
    return this;
  }

  /**
   * Get followsTimestamp
   * @return followsTimestamp
  **/
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getFollowsTimestamp() {
    return followsTimestamp;
  }

  public void setFollowsTimestamp(OffsetDateTime followsTimestamp) {
    this.followsTimestamp = followsTimestamp;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FollowsRequest followsRequest = (FollowsRequest) o;
    return Objects.equals(this.playerId, followsRequest.playerId) &&
        Objects.equals(this.followsId, followsRequest.followsId) &&
        Objects.equals(this.followsTimestamp, followsRequest.followsTimestamp);
  }

  @Override
  public int hashCode() {
    return Objects.hash(playerId, followsId, followsTimestamp);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FollowsRequest {\n");
    
    sb.append("    playerId: ").append(toIndentedString(playerId)).append("\n");
    sb.append("    followsId: ").append(toIndentedString(followsId)).append("\n");
    sb.append("    followsTimestamp: ").append(toIndentedString(followsTimestamp)).append("\n");
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

