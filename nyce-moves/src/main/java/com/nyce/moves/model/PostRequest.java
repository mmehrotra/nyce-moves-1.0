package com.nyce.moves.model;

import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * PostRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-03-07T10:16:43.744+05:30")

public class PostRequest   {
  @JsonProperty("postedBy")
  private Long postedBy = null;

  @JsonProperty("post")
  private String post = null;

  @JsonProperty("challengeName")
  private String challengeName = null;

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

public String getChallengeName() {
	return challengeName;
}

public void setChallengeName(String challengeName) {
	this.challengeName = challengeName;
}
  
  
}

