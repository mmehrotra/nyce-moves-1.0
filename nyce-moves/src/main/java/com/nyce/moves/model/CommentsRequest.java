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
 * CommentsRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-03-07T10:16:43.744+05:30")

public class CommentsRequest   {
  @JsonProperty("postedBy")
  private Long postedBy = null;

  @JsonProperty("imageId")
  private Long imageId = null;

  @JsonProperty("videoId")
  private Long videoId = null;

  @JsonProperty("postId")
  private Long postId = null;

  @JsonProperty("comments")
  private String comments = null;

  public CommentsRequest postedBy(Long postedBy) {
    this.postedBy = postedBy;
    return this;
  }

  /**
   * Get postedBy
   * @return postedBy
  **/
  @ApiModelProperty(value = "")


  public Long getPostedBy() {
    return postedBy;
  }

  public void setPostedBy(Long postedBy) {
    this.postedBy = postedBy;
  }

  public CommentsRequest imageId(Long imageId) {
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

  public CommentsRequest videoId(Long videoId) {
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

  public CommentsRequest postId(Long postId) {
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

  public CommentsRequest comments(String comments) {
    this.comments = comments;
    return this;
  }

  /**
   * Get comments
   * @return comments
  **/
  @ApiModelProperty(value = "")


  public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CommentsRequest commentsRequest = (CommentsRequest) o;
    return Objects.equals(this.postedBy, commentsRequest.postedBy) &&
        Objects.equals(this.imageId, commentsRequest.imageId) &&
        Objects.equals(this.videoId, commentsRequest.videoId) &&
        Objects.equals(this.postId, commentsRequest.postId) &&
        Objects.equals(this.comments, commentsRequest.comments);
  }

  @Override
  public int hashCode() {
    return Objects.hash(postedBy, imageId, videoId, postId, comments);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CommentsRequest {\n");
    
    sb.append("    postedBy: ").append(toIndentedString(postedBy)).append("\n");
    sb.append("    imageId: ").append(toIndentedString(imageId)).append("\n");
    sb.append("    videoId: ").append(toIndentedString(videoId)).append("\n");
    sb.append("    postId: ").append(toIndentedString(postId)).append("\n");
    sb.append("    comments: ").append(toIndentedString(comments)).append("\n");
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

