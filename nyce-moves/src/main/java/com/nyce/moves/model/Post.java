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
 * Post
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-02-22T15:38:48.275+05:30")

public class Post   {
  @JsonProperty("postId")
  private Long postId = null;

  @JsonProperty("postedBy")
  private Long postedBy = null;

  @JsonProperty("post")
  private String post = null;

  @JsonProperty("postedTimestamp")
  private OffsetDateTime postedTimestamp = null;

  public Post postId(Long postId) {
    this.postId = postId;
    return this;
  }

  /**
   * Get postId
   * @return postId
  **/
  @ApiModelProperty(value = "")


  public Long getPostId() {
    return postId;
  }

  public void setPostId(Long postId) {
    this.postId = postId;
  }

  public Post postedBy(Long postedBy) {
    this.postedBy = postedBy;
    return this;
  }

  /**
   * Player Id
   * @return postedBy
  **/
  @ApiModelProperty(value = "Player Id")


  public Long getPostedBy() {
    return postedBy;
  }

  public void setPostedBy(Long postedBy) {
    this.postedBy = postedBy;
  }

  public Post post(String post) {
    this.post = post;
    return this;
  }

  /**
   * Get post
   * @return post
  **/
  @ApiModelProperty(value = "")


  public String getPost() {
    return post;
  }

  public void setPost(String post) {
    this.post = post;
  }

  public Post postedTimestamp(OffsetDateTime postedTimestamp) {
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
    Post post = (Post) o;
    return Objects.equals(this.postId, post.postId) &&
        Objects.equals(this.postedBy, post.postedBy) &&
        Objects.equals(this.post, post.post) &&
        Objects.equals(this.postedTimestamp, post.postedTimestamp);
  }

  @Override
  public int hashCode() {
    return Objects.hash(postId, postedBy, post, postedTimestamp);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Post {\n");
    
    sb.append("    postId: ").append(toIndentedString(postId)).append("\n");
    sb.append("    postedBy: ").append(toIndentedString(postedBy)).append("\n");
    sb.append("    post: ").append(toIndentedString(post)).append("\n");
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

