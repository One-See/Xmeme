package com.crio.starter.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.crio.starter.exchange.Meme;
import com.crio.starter.exchange.MemesResponseDto;
import com.crio.starter.service.MemesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.UriComponentsBuilder;


@AutoConfigureMockMvc
@SpringBootTest
public class MemesControllerTest {
  @Autowired
  private MockMvc mvc;
  
  @MockBean
  private MemesService memesService;
  
  @Test
  void findMeme() throws Exception {
    //given

    List<Meme> meme = new ArrayList<Meme>();
    meme.add(new Meme("1", "Samsher Khan", "I am King", "https://meme.com", null));
    
    Mockito.doReturn(new MemesResponseDto(meme))
    .when(memesService).findById("1");
    
    // when
    URI uri = UriComponentsBuilder
        .fromPath("/memes")
        .path("/{id}")
        .build("1");
    
    MockHttpServletResponse response = mvc.perform(get(uri.toString())).andReturn().getResponse();
    
    //then
    String responseStr = response.getContentAsString();
    ObjectMapper mapper = new ObjectMapper();
    Meme memeResponse = mapper.readValue(responseStr, Meme.class);
    
    
    Meme ref = new Meme("1", "Samsher Khan", "I am King", "https://meme.com", null);
    
    assertEquals(memeResponse, ref);
    Mockito.verify(memesService, Mockito.times(1)).findById("1");
  }
}
