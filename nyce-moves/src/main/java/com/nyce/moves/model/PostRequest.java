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
 * PostRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-02-22T15:38:48.275+05:30")

public class PostRequest   {
  @JsonProperty("postedBy")
  private Long postedBy = null;

  @JsonProperty("post")
  private String post = null;

  public PostRequest postedBy(Long postedBy) {
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

  public PostRequest post(String post) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PostRequest postRequest = (PostRequest) o;
    return Objects.equals(this.postedBy, postRequest.postedBy) &&
        Objects.equals(this.post, postRequest.post);
  }

  @Override
  public int hashCode() {
    return Objects.hash(postedBy, post);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PostRequest {\n");
    
    sb.append("    postedBy: ").append(toIndentedString(postedBy)).append("\n");
    sb.append("    post: ").append(toIndentedString(post)).append("\n");
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

