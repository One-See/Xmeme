package com.crio.starter.exchange;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MemesResponseDto {
  List<Meme> memes;
}
