package com.nyce.moves.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;

/**
 * GetAllPostsResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-03-07T14:45:46.226+05:30")

public class GetAllPostsResponse   {
  /**
   * Gets or Sets status
   */
  public enum StatusEnum {
    SUCCESS("SUCCESS"),
    
    FAILURE("FAILURE");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StatusEnum fromValue(String text) {
      for (StatusEnum b : StatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("status")
  private StatusEnum status = null;

  @JsonProperty("code")
  private String code = null;

  @JsonProperty("message")
  private String message = null;

  @JsonProperty("data")
  @Valid
  private List<Post> data = null;

  @JsonProperty("pageSize")
  private Long pageSize = null;

  @JsonProperty("pageNumber")
  private Long pageNumber = null;

  @JsonProperty("totalNumberofPagesAvailable")
  private Long totalNumberofPagesAvailable = null;

  public GetAllPostsResponse status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  **/
  @ApiModelProperty(value = "")


  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public GetAllPostsResponse code(String code) {
    this.code = code;
    return this;
  }

  /**
   * Get code
   * @return code
  **/
  @ApiModelProperty(value = "")


  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public GetAllPostsResponse message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Get message
   * @return message
  **/
  @ApiModelProperty(value = "")


  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public GetAllPostsResponse data(List<Post> data) {
    this.data = data;
    return this;
  }

  public GetAllPostsResponse addDataItem(Post dataItem) {
    if (this.data == null) {
      this.data = new ArrayList<Post>();
    }
    this.data.add(dataItem);
    return this;
  }

  /**
   * Get data
   * @return data
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<Post> getData() {
    return data;
  }

  public void setData(List<Post> data) {
    this.data = data;
  }

  public GetAllPostsResponse pageSize(Long pageSize) {
    this.pageSize = pageSize;
    return this;
  }

  /**
   * Get pageSize
   * @return pageSize
  **/
  @ApiModelProperty(value = "")


  public Long getPageSize() {
    return pageSize;
  }

  public void setPageSize(Long pageSize) {
    this.pageSize = pageSize;
  }

  public GetAllPostsResponse pageNumber(Long pageNumber) {
    this.pageNumber = pageNumber;
    return this;
  }

  /**
   * Get pageNumber
   * @return pageNumber
  **/
  @ApiModelProperty(value = "")


  public Long getPageNumber() {
    return pageNumber;
  }

  public void setPageNumber(Long pageNumber) {
    this.pageNumber = pageNumber;
  }

  public GetAllPostsResponse totalNumberofPagesAvailable(Long totalNumberofPagesAvailable) {
    this.totalNumberofPagesAvailable = totalNumberofPagesAvailable;
    return this;
  }

  /**
   * Get totalNumberofPagesAvailable
   * @return totalNumberofPagesAvailable
  **/
  @ApiModelProperty(value = "")


  public Long getTotalNumberofPagesAvailable() {
    return totalNumberofPagesAvailable;
  }

  public void setTotalNumberofPagesAvailable(Long totalNumberofPagesAvailable) {
    this.totalNumberofPagesAvailable = totalNumberofPagesAvailable;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetAllPostsResponse getAllPostsResponse = (GetAllPostsResponse) o;
    return Objects.equals(this.status, getAllPostsResponse.status) &&
        Objects.equals(this.code, getAllPostsResponse.code) &&
        Objects.equals(this.message, getAllPostsResponse.message) &&
        Objects.equals(this.data, getAllPostsResponse.data) &&
        Objects.equals(this.pageSize, getAllPostsResponse.pageSize) &&
        Objects.equals(this.pageNumber, getAllPostsResponse.pageNumber) &&
        Objects.equals(this.totalNumberofPagesAvailable, getAllPostsResponse.totalNumberofPagesAvailable);
  }

  @Override
  public int hashCode() {
    return Objects.hash(status, code, message, data, pageSize, pageNumber, totalNumberofPagesAvailable);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetAllPostsResponse {\n");
    
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
    sb.append("    pageNumber: ").append(toIndentedString(pageNumber)).append("\n");
    sb.append("    totalNumberofPagesAvailable: ").append(toIndentedString(totalNumberofPagesAvailable)).append("\n");
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

