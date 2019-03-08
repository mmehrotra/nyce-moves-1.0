package com.nyce.moves.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * Post
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-03-07T10:16:43.744+05:30")
@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty("postId")
	private Long postId = null;

	@JsonProperty("postedBy")
	private Long postedBy = null;

	@JsonProperty("post")
	private String post = null;

	@JsonProperty("postedTimestamp")
	private OffsetDateTime postedTimestamp = null;

	@OneToMany(cascade = { CascadeType.ALL })
	private List<Comments> comments;

	public Post postId(Long postId) {
		this.postId = postId;
		return this;
	}

	/**
	 * Get postId
	 * 
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
	 * 
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
	 * 
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
	 * 
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

	public List<Comments> getComments() {
		return comments;
	}

	public void setComments(List<Comments> comments) {
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
		Post post = (Post) o;
		return Objects.equals(this.postId, post.postId) && Objects.equals(this.postedBy, post.postedBy) && Objects.equals(this.post, post.post) && Objects.equals(this.postedTimestamp, post.postedTimestamp);
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
