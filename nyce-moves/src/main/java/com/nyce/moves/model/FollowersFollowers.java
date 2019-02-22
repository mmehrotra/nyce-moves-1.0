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
 * FollowersFollowers
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-02-22T15:38:48.275+05:30")

public class FollowersFollowers   {
  @JsonProperty("playerId")
  private Long playerId = null;

  @JsonProperty("followTimestamp")
  private OffsetDateTime followTimestamp = null;

  public FollowersFollowers playerId(Long playerId) {
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

  public FollowersFollowers followTimestamp(OffsetDateTime followTimestamp) {
    this.followTimestamp = followTimestamp;
    return this;
  }

  /**
   * Get followTimestamp
   * @return followTimestamp
  **/
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getFollowTimestamp() {
    return followTimestamp;
  }

  public void setFollowTimestamp(OffsetDateTime followTimestamp) {
    this.followTimestamp = followTimestamp;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FollowersFollowers followersFollowers = (FollowersFollowers) o;
    return Objects.equals(this.playerId, followersFollowers.playerId) &&
        Objects.equals(this.followTimestamp, followersFollowers.followTimestamp);
  }

  @Override
  public int hashCode() {
    return Objects.hash(playerId, followTimestamp);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FollowersFollowers {\n");
    
    sb.append("    playerId: ").append(toIndentedString(playerId)).append("\n");
    sb.append("    followTimestamp: ").append(toIndentedString(followTimestamp)).append("\n");
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

