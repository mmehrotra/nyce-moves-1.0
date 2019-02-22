package com.nyce.moves.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.nyce.moves.model.FollowersFollowers;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Follows
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-02-22T15:38:48.275+05:30")

public class Follows   {
  @JsonProperty("playerId")
  private Long playerId = null;

  @JsonProperty("follows")
  @Valid
  private List<FollowersFollowers> follows = null;

  public Follows playerId(Long playerId) {
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

  public Follows follows(List<FollowersFollowers> follows) {
    this.follows = follows;
    return this;
  }

  public Follows addFollowsItem(FollowersFollowers followsItem) {
    if (this.follows == null) {
      this.follows = new ArrayList<FollowersFollowers>();
    }
    this.follows.add(followsItem);
    return this;
  }

  /**
   * Get follows
   * @return follows
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<FollowersFollowers> getFollows() {
    return follows;
  }

  public void setFollows(List<FollowersFollowers> follows) {
    this.follows = follows;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Follows follows = (Follows) o;
    return Objects.equals(this.playerId, follows.playerId) &&
        Objects.equals(this.follows, follows.follows);
  }

  @Override
  public int hashCode() {
    return Objects.hash(playerId, follows);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Follows {\n");
    
    sb.append("    playerId: ").append(toIndentedString(playerId)).append("\n");
    sb.append("    follows: ").append(toIndentedString(follows)).append("\n");
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

