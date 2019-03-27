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
 * ChangeEmailRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-03-26T15:47:49.816+05:30")

public class ChangeEmailRequest   {
  @JsonProperty("currentEmail")
  private String currentEmail = null;

  @JsonProperty("newEmail")
  private String newEmail = null;

  public ChangeEmailRequest currentEmail(String currentEmail) {
    this.currentEmail = currentEmail;
    return this;
  }

  /**
   * Get currentEmail
   * @return currentEmail
  **/
  @ApiModelProperty(value = "")


  public String getCurrentEmail() {
    return currentEmail;
  }

  public void setCurrentEmail(String currentEmail) {
    this.currentEmail = currentEmail;
  }

  public ChangeEmailRequest newEmail(String newEmail) {
    this.newEmail = newEmail;
    return this;
  }

  /**
   * Get newEmail
   * @return newEmail
  **/
  @ApiModelProperty(value = "")


  public String getNewEmail() {
    return newEmail;
  }

  public void setNewEmail(String newEmail) {
    this.newEmail = newEmail;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ChangeEmailRequest changeEmailRequest = (ChangeEmailRequest) o;
    return Objects.equals(this.currentEmail, changeEmailRequest.currentEmail) &&
        Objects.equals(this.newEmail, changeEmailRequest.newEmail);
  }

  @Override
  public int hashCode() {
    return Objects.hash(currentEmail, newEmail);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ChangeEmailRequest {\n");
    
    sb.append("    currentEmail: ").append(toIndentedString(currentEmail)).append("\n");
    sb.append("    newEmail: ").append(toIndentedString(newEmail)).append("\n");
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

