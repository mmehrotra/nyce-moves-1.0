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
 * ChallengeRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-05-18T16:26:14.015+05:30")

public class ChallengeRequest   {
  @JsonProperty("challangeName")
  private String challangeName = null;

  @JsonProperty("challangeCreatedBy")
  private Long challangeCreatedBy = null;

  public ChallengeRequest challangeName(String challangeName) {
    this.challangeName = challangeName;
    return this;
  }

  /**
   * Get challangeName
   * @return challangeName
  **/
  @ApiModelProperty(value = "")


  public String getChallangeName() {
    return challangeName;
  }

  public void setChallangeName(String challangeName) {
    this.challangeName = challangeName;
  }

  public ChallengeRequest challangeCreatedBy(Long challangeCreatedBy) {
    this.challangeCreatedBy = challangeCreatedBy;
    return this;
  }

  /**
   * Get challangeCreatedBy
   * @return challangeCreatedBy
  **/
  @ApiModelProperty(value = "")


  public Long getChallangeCreatedBy() {
    return challangeCreatedBy;
  }

  public void setChallangeCreatedBy(Long challangeCreatedBy) {
    this.challangeCreatedBy = challangeCreatedBy;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ChallengeRequest challengeRequest = (ChallengeRequest) o;
    return Objects.equals(this.challangeName, challengeRequest.challangeName) &&
        Objects.equals(this.challangeCreatedBy, challengeRequest.challangeCreatedBy);
  }

  @Override
  public int hashCode() {
    return Objects.hash(challangeName, challangeCreatedBy);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ChallengeRequest {\n");
    
    sb.append("    challangeName: ").append(toIndentedString(challangeName)).append("\n");
    sb.append("    challangeCreatedBy: ").append(toIndentedString(challangeCreatedBy)).append("\n");
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

