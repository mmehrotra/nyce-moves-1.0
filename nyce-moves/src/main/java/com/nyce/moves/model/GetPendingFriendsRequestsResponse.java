package com.nyce.moves.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.nyce.moves.model.Friend;
import com.nyce.moves.model.ResponseTemplate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * GetPendingFriendsRequestsResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-04-02T15:09:35.250+05:30")

public class GetPendingFriendsRequestsResponse   {
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
  private List<Friend> data = null;

  @JsonProperty("numberOfPendingRequests")
  private Long numberOfPendingRequests = null;

  public GetPendingFriendsRequestsResponse status(StatusEnum status) {
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

  public GetPendingFriendsRequestsResponse code(String code) {
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

  public GetPendingFriendsRequestsResponse message(String message) {
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

  public GetPendingFriendsRequestsResponse data(List<Friend> data) {
    this.data = data;
    return this;
  }

  public GetPendingFriendsRequestsResponse addDataItem(Friend dataItem) {
    if (this.data == null) {
      this.data = new ArrayList<Friend>();
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

  public List<Friend> getData() {
    return data;
  }

  public void setData(List<Friend> data) {
    this.data = data;
  }

  public GetPendingFriendsRequestsResponse numberOfPendingRequests(Long numberOfPendingRequests) {
    this.numberOfPendingRequests = numberOfPendingRequests;
    return this;
  }

  /**
   * Get numberOfPendingRequests
   * @return numberOfPendingRequests
  **/
  @ApiModelProperty(value = "")


  public Long getNumberOfPendingRequests() {
    return numberOfPendingRequests;
  }

  public void setNumberOfPendingRequests(Long numberOfPendingRequests) {
    this.numberOfPendingRequests = numberOfPendingRequests;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetPendingFriendsRequestsResponse getPendingFriendsRequestsResponse = (GetPendingFriendsRequestsResponse) o;
    return Objects.equals(this.status, getPendingFriendsRequestsResponse.status) &&
        Objects.equals(this.code, getPendingFriendsRequestsResponse.code) &&
        Objects.equals(this.message, getPendingFriendsRequestsResponse.message) &&
        Objects.equals(this.data, getPendingFriendsRequestsResponse.data) &&
        Objects.equals(this.numberOfPendingRequests, getPendingFriendsRequestsResponse.numberOfPendingRequests);
  }

  @Override
  public int hashCode() {
    return Objects.hash(status, code, message, data, numberOfPendingRequests);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetPendingFriendsRequestsResponse {\n");
    
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("    numberOfPendingRequests: ").append(toIndentedString(numberOfPendingRequests)).append("\n");
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

