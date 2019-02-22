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
 * Followers
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-02-22T15:38:48.275+05:30")

public class Followers   {
  @JsonProperty("playerId")
  private Long playerId = null;

  @JsonProperty("followers")
  @Valid
  private List<FollowersFollowers> followers = null;

  public Followers playerId(Long playerId) {
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

  public Followers followers(List<FollowersFollowers> followers) {
    this.followers = followers;
    return this;
  }

  public Followers addFollowersItem(FollowersFollowers followersItem) {
    if (this.followers == null) {
      this.followers = new ArrayList<FollowersFollowers>();
    }
    this.followers.add(followersItem);
    return this;
  }

  /**
   * Get followers
   * @return followers
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<FollowersFollowers> getFollowers() {
    return followers;
  }

  public void setFollowers(List<FollowersFollowers> followers) {
    this.followers = followers;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Followers followers = (Followers) o;
    return Objects.equals(this.playerId, followers.playerId) &&
        Objects.equals(this.followers, followers.followers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(playerId, followers);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Followers {\n");
    
    sb.append("    playerId: ").append(toIndentedString(playerId)).append("\n");
    sb.append("    followers: ").append(toIndentedString(followers)).append("\n");
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

