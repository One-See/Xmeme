package com.crio.starter.exchange;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Meme {
  private String id;
  @NonNull
  @JsonProperty("name")
  private String userName;
  @NonNull
  private String caption;
  @NonNull
  private String url;
  @JsonIgnore
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private Date createdDate;
}
