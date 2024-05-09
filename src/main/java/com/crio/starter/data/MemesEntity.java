package com.crio.starter.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@AllArgsConstructor
@Data
@Document(collection = "memes")
public class MemesEntity {
  @Id
  private String id;
  @JsonProperty("name")
  private String userName;
  private String caption;
  private String memeUrl;
  @JsonIgnore
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private Date createdDate;
  
}
