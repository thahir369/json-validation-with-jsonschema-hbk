package com.helloWorld.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

  private String movieName;
  private String director;
  private String producer;
  private String release;
  private String storyLine;
  private List<String> cast;
  private String genre;
}
