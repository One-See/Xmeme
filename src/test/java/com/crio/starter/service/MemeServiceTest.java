package com.crio.starter.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.crio.starter.data.MemesEntity;
import com.crio.starter.exchange.Meme;
import com.crio.starter.exchange.MemesResponseDto;
import com.crio.starter.repository.MemesRepository;
import java.util.Date;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
class MemeServiceTest {
  
  @Mock
  private MemesRepository memesRepository;
  
  @InjectMocks
  private MemesService memesService;
  
  @Test
  void getMessage() {
    Date date = new Date();
    Optional<MemesEntity> memesEntity = getMeme("1", "Samsher Khan", "I am King", "https://meme.com", date);
    
    Mockito.doReturn(memesEntity)
    .when(memesRepository).findById("1");
    MemesResponseDto responseDto = memesService.findById("1");
    
    Meme meme = new Meme("1", "Samsher Khan", "I am King", "https://meme.com", date);
    
    assertEquals(meme, responseDto.getMemes().get(0));
    
  }
  
  private Optional<MemesEntity> getMeme(String id, String name, String cap, String url, Date date) {
    MemesEntity memesEntity = new MemesEntity(id, name, cap, url, date);
    Optional<MemesEntity> optionalMemesEntity = Optional.of(memesEntity);
    return optionalMemesEntity;
  }
}