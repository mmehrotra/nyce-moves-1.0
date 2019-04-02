package com.nyce.moves.model;

import java.time.OffsetDateTime;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;

/**
 * DashboardElement
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-04-02T10:45:23.537+05:30")

public class DashboardElement   {
  @JsonProperty("playerId")
  private Long playerId = null;

  @JsonProperty("dashboardElementId")
  private Long dashboardElementId = null;

  /**
   * Gets or Sets dashboardElementType
   */
  public enum DashboardElementTypeEnum {
    IMAGE("IMAGE"),
    
    VIDEO("VIDEO"),
    
    POST("POST");

    private String value;

    DashboardElementTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static DashboardElementTypeEnum fromValue(String text) {
      for (DashboardElementTypeEnum b : DashboardElementTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("dashboardElementType")
  private DashboardElementTypeEnum dashboardElementType = null;

  @JsonProperty("title")
  private String title = null;

  @JsonProperty("url")
  private String url = null;

  @JsonProperty("preSignedUrl")
  private String preSignedUrl = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("applauds")
  private Long applauds = null;

  @JsonProperty("postedTimestamp")
  private OffsetDateTime postedTimestamp = null;

  @JsonProperty("numberOfComments")
  private Long numberOfComments = null;

  public DashboardElement playerId(Long playerId) {
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

  public DashboardElement dashboardElementId(Long dashboardElementId) {
    this.dashboardElementId = dashboardElementId;
    return this;
  }

  /**
   * Get dashboardElementId
   * @return dashboardElementId
  **/
  @ApiModelProperty(value = "")


  public Long getDashboardElementId() {
    return dashboardElementId;
  }

  public void setDashboardElementId(Long dashboardElementId) {
    this.dashboardElementId = dashboardElementId;
  }

  public DashboardElement dashboardElementType(DashboardElementTypeEnum dashboardElementType) {
    this.dashboardElementType = dashboardElementType;
    return this;
  }

  /**
   * Get dashboardElementType
   * @return dashboardElementType
  **/
  @ApiModelProperty(value = "")


  public DashboardElementTypeEnum getDashboardElementType() {
    return dashboardElementType;
  }

  public void setDashboardElementType(DashboardElementTypeEnum dashboardElementType) {
    this.dashboardElementType = dashboardElementType;
  }

  public DashboardElement title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
  **/
  @ApiModelProperty(value = "")


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public DashboardElement url(String url) {
    this.url = url;
    return this;
  }

  /**
   * Get url
   * @return url
  **/
  @ApiModelProperty(value = "")


  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public DashboardElement preSignedUrl(String preSignedUrl) {
    this.preSignedUrl = preSignedUrl;
    return this;
  }

  /**
   * Get preSignedUrl
   * @return preSignedUrl
  **/
  @ApiModelProperty(value = "")


  public String getPreSignedUrl() {
    return preSignedUrl;
  }

  public void setPreSignedUrl(String preSignedUrl) {
    this.preSignedUrl = preSignedUrl;
  }

  public DashboardElement description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
  **/
  @ApiModelProperty(value = "")


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public DashboardElement applauds(Long applauds) {
    this.applauds = applauds;
    return this;
  }

  /**
   * Get applauds
   * @return applauds
  **/
  @ApiModelProperty(value = "")


  public Long getApplauds() {
    return applauds;
  }

  public void setApplauds(Long applauds) {
    this.applauds = applauds;
  }

  public DashboardElement postedTimestamp(OffsetDateTime postedTimestamp) {
    this.postedTimestamp = postedTimestamp;
    return this;
  }

  /**
   * Get postedTimestamp
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

  public DashboardElement numberOfComments(Long numberOfComments) {
    this.numberOfComments = numberOfComments;
    return this;
  }

  /**
   * Get numberOfComments
   * @return numberOfComments
  **/
  @ApiModelProperty(value = "")


  public Long getNumberOfComments() {
    return numberOfComments;
  }

  public void setNumberOfComments(Long numberOfComments) {
    this.numberOfComments = numberOfComments;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DashboardElement dashboardElement = (DashboardElement) o;
    return Objects.equals(this.playerId, dashboardElement.playerId) &&
        Objects.equals(this.dashboardElementId, dashboardElement.dashboardElementId) &&
        Objects.equals(this.dashboardElementType, dashboardElement.dashboardElementType) &&
        Objects.equals(this.title, dashboardElement.title) &&
        Objects.equals(this.url, dashboardElement.url) &&
        Objects.equals(this.preSignedUrl, dashboardElement.preSignedUrl) &&
        Objects.equals(this.description, dashboardElement.description) &&
        Objects.equals(this.applauds, dashboardElement.applauds) &&
        Objects.equals(this.postedTimestamp, dashboardElement.postedTimestamp) &&
        Objects.equals(this.numberOfComments, dashboardElement.numberOfComments);
  }

  @Override
  public int hashCode() {
    return Objects.hash(playerId, dashboardElementId, dashboardElementType, title, url, preSignedUrl, description, applauds, postedTimestamp, numberOfComments);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DashboardElement {\n");
    
    sb.append("    playerId: ").append(toIndentedString(playerId)).append("\n");
    sb.append("    dashboardElementId: ").append(toIndentedString(dashboardElementId)).append("\n");
    sb.append("    dashboardElementType: ").append(toIndentedString(dashboardElementType)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("    preSignedUrl: ").append(toIndentedString(preSignedUrl)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    applauds: ").append(toIndentedString(applauds)).append("\n");
    sb.append("    postedTimestamp: ").append(toIndentedString(postedTimestamp)).append("\n");
    sb.append("    numberOfComments: ").append(toIndentedString(numberOfComments)).append("\n");
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

