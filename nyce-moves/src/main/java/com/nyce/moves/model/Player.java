package com.nyce.moves.model;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;

/**
 * Player
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-02-22T15:38:48.275+05:30")
@Entity
public class Player   {
  @JsonProperty("email")
  private String email = null;

  @JsonProperty("firstName")
  private String firstName = null;

  @JsonProperty("lastName")
  private String lastName = null;

  @JsonProperty("password")
  private String password = null;

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

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @JsonProperty("playerId")
  private Long playerId = null;

  @JsonProperty("creationTime")
  private OffsetDateTime creationTime = null;

  @JsonProperty("updateTime")
  private LocalDate updateTime = null;

  @JsonProperty("numberOfConnections")
  private Long numberOfConnections = null;

  public Player email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
  **/
  @ApiModelProperty(value = "")


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Player firstName(String firstName) {
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

  public Player lastName(String lastName) {
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

  public Player password(String password) {
    this.password = password;
    return this;
  }

  /**
   * Get password
   * @return password
  **/
  @ApiModelProperty(value = "")


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Player displayName(String displayName) {
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

  public Player city(String city) {
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

  public Player country(String country) {
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

  public Player gender(GenderEnum gender) {
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

  public Player school(String school) {
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

  public Player primarySport(String primarySport) {
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

  public Player dob(LocalDate dob) {
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

  public Player playerId(Long playerId) {
    this.playerId = playerId;
    return this;
  }

  /**
   * Get playerId
   * @return playerId
  **/
  @ApiModelProperty(value = "")


  public Long getPlayerId() {
    return playerId;
  }

  public void setPlayerId(Long playerId) {
    this.playerId = playerId;
  }

  public Player creationTime(OffsetDateTime creationTime) {
    this.creationTime = creationTime;
    return this;
  }

  /**
   * Get creationTime
   * @return creationTime
  **/
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getCreationTime() {
    return creationTime;
  }

  public void setCreationTime(OffsetDateTime creationTime) {
    this.creationTime = creationTime;
  }

  public Player updateTime(LocalDate updateTime) {
    this.updateTime = updateTime;
    return this;
  }

  /**
   * Get updateTime
   * @return updateTime
  **/
  @ApiModelProperty(value = "")

  @Valid

  public LocalDate getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(LocalDate updateTime) {
    this.updateTime = updateTime;
  }

  public Player numberOfConnections(Long numberOfConnections) {
    this.numberOfConnections = numberOfConnections;
    return this;
  }

  /**
   * Get numberOfConnections
   * @return numberOfConnections
  **/
  @ApiModelProperty(value = "")


  public Long getNumberOfConnections() {
    return numberOfConnections;
  }

  public void setNumberOfConnections(Long numberOfConnections) {
    this.numberOfConnections = numberOfConnections;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Player player = (Player) o;
    return Objects.equals(this.email, player.email) &&
        Objects.equals(this.firstName, player.firstName) &&
        Objects.equals(this.lastName, player.lastName) &&
        Objects.equals(this.password, player.password) &&
        Objects.equals(this.displayName, player.displayName) &&
        Objects.equals(this.city, player.city) &&
        Objects.equals(this.country, player.country) &&
        Objects.equals(this.gender, player.gender) &&
        Objects.equals(this.school, player.school) &&
        Objects.equals(this.primarySport, player.primarySport) &&
        Objects.equals(this.dob, player.dob) &&
        Objects.equals(this.playerId, player.playerId) &&
        Objects.equals(this.creationTime, player.creationTime) &&
        Objects.equals(this.updateTime, player.updateTime) &&
        Objects.equals(this.numberOfConnections, player.numberOfConnections);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email, firstName, lastName, password, displayName, city, country, gender, school, primarySport, dob, playerId, creationTime, updateTime, numberOfConnections);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Player {\n");
    
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    displayName: ").append(toIndentedString(displayName)).append("\n");
    sb.append("    city: ").append(toIndentedString(city)).append("\n");
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("    gender: ").append(toIndentedString(gender)).append("\n");
    sb.append("    school: ").append(toIndentedString(school)).append("\n");
    sb.append("    primarySport: ").append(toIndentedString(primarySport)).append("\n");
    sb.append("    dob: ").append(toIndentedString(dob)).append("\n");
    sb.append("    playerId: ").append(toIndentedString(playerId)).append("\n");
    sb.append("    creationTime: ").append(toIndentedString(creationTime)).append("\n");
    sb.append("    updateTime: ").append(toIndentedString(updateTime)).append("\n");
    sb.append("    numberOfConnections: ").append(toIndentedString(numberOfConnections)).append("\n");
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

