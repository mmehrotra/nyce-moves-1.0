package com.nyce.moves.model;

import java.time.LocalDate;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;

/**
 * UpdatePlayerRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-03-26T12:15:36.477+05:30")

public class UpdatePlayerRequest   {
  @JsonProperty("firstName")
  private String firstName = null;

  @JsonProperty("lastName")
  private String lastName = null;

  @JsonProperty("displayName")
  private String displayName = null;

  @JsonProperty("city")
  private String city = null;

  @JsonProperty("country")
  private String country = null;

  /**
   * Gets or Sets gender
   */
  public enum GenderEnum {
    MALE("male"),
    
    FEMALE("female"),
    
    UNSPECIFIED("unspecified");

    private String value;

    GenderEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static GenderEnum fromValue(String text) {
      for (GenderEnum b : GenderEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("gender")
  private GenderEnum gender = null;

  @JsonProperty("school")
  private String school = null;

  @JsonProperty("primarySport")
  private String primarySport = null;

  @JsonProperty("dob")
  private LocalDate dob = null;

  @JsonProperty("profileImageUrl")
  private String profileImageUrl = null;

  public UpdatePlayerRequest firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  /**
   * Get firstName
   * @return firstName
  **/
  @ApiModelProperty(value = "")


  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public UpdatePlayerRequest lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  /**
   * Get lastName
   * @return lastName
  **/
  @ApiModelProperty(value = "")


  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public UpdatePlayerRequest displayName(String displayName) {
    this.displayName = displayName;
    return this;
  }

  /**
   * Get displayName
   * @return displayName
  **/
  @ApiModelProperty(value = "")


  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public UpdatePlayerRequest city(String city) {
    this.city = city;
    return this;
  }

  /**
   * Get city
   * @return city
  **/
  @ApiModelProperty(value = "")


  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public UpdatePlayerRequest country(String country) {
    this.country = country;
    return this;
  }

  /**
   * Get country
   * @return country
  **/
  @ApiModelProperty(value = "")


  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public UpdatePlayerRequest gender(GenderEnum gender) {
    this.gender = gender;
    return this;
  }

  /**
   * Get gender
   * @return gender
  **/
  @ApiModelProperty(value = "")


  public GenderEnum getGender() {
    return gender;
  }

  public void setGender(GenderEnum gender) {
    this.gender = gender;
  }

  public UpdatePlayerRequest school(String school) {
    this.school = school;
    return this;
  }

  /**
   * Get school
   * @return school
  **/
  @ApiModelProperty(value = "")


  public String getSchool() {
    return school;
  }

  public void setSchool(String school) {
    this.school = school;
  }

  public UpdatePlayerRequest primarySport(String primarySport) {
    this.primarySport = primarySport;
    return this;
  }

  /**
   * Get primarySport
   * @return primarySport
  **/
  @ApiModelProperty(value = "")


  public String getPrimarySport() {
    return primarySport;
  }

  public void setPrimarySport(String primarySport) {
    this.primarySport = primarySport;
  }

  public UpdatePlayerRequest dob(LocalDate dob) {
    this.dob = dob;
    return this;
  }

  /**
   * Get dob
   * @return dob
  **/
  @ApiModelProperty(value = "")

  @Valid

  public LocalDate getDob() {
    return dob;
  }

  public void setDob(LocalDate dob) {
    this.dob = dob;
  }

  public UpdatePlayerRequest profileImageUrl(String profileImageUrl) {
    this.profileImageUrl = profileImageUrl;
    return this;
  }

  /**
   * Get profileImageUrl
   * @return profileImageUrl
  **/
  @ApiModelProperty(value = "")


  public String getProfileImageUrl() {
    return profileImageUrl;
  }

  public void setProfileImageUrl(String profileImageUrl) {
    this.profileImageUrl = profileImageUrl;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdatePlayerRequest updatePlayerRequest = (UpdatePlayerRequest) o;
    return Objects.equals(this.firstName, updatePlayerRequest.firstName) &&
        Objects.equals(this.lastName, updatePlayerRequest.lastName) &&
        Objects.equals(this.displayName, updatePlayerRequest.displayName) &&
        Objects.equals(this.city, updatePlayerRequest.city) &&
        Objects.equals(this.country, updatePlayerRequest.country) &&
        Objects.equals(this.gender, updatePlayerRequest.gender) &&
        Objects.equals(this.school, updatePlayerRequest.school) &&
        Objects.equals(this.primarySport, updatePlayerRequest.primarySport) &&
        Objects.equals(this.dob, updatePlayerRequest.dob) &&
        Objects.equals(this.profileImageUrl, updatePlayerRequest.profileImageUrl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName, displayName, city, country, gender, school, primarySport, dob, profileImageUrl);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UpdatePlayerRequest {\n");
    
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    displayName: ").append(toIndentedString(displayName)).append("\n");
    sb.append("    city: ").append(toIndentedString(city)).append("\n");
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("    gender: ").append(toIndentedString(gender)).append("\n");
    sb.append("    school: ").append(toIndentedString(school)).append("\n");
    sb.append("    primarySport: ").append(toIndentedString(primarySport)).append("\n");
    sb.append("    dob: ").append(toIndentedString(dob)).append("\n");
    sb.append("    profileImageUrl: ").append(toIndentedString(profileImageUrl)).append("\n");
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

