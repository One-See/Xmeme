package com.crio.starter.service;

import com.crio.starter.data.MemesEntity;
import com.crio.starter.exchange.Meme;
import com.crio.starter.exchange.MemesResponseDto;
import com.crio.starter.exchange.ResponseDto;
import com.crio.starter.repository.MemesRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class MemesService {
  
  @Autowired
  private MemesRepository memesRepository;
  
  public ResponseDto save(Meme meme) {
    String userName = meme.getUserName();
    String caption = meme.getCaption();
    String url = meme.getUrl();

    if (findMeme(meme) != null) {
      return null;
    } 

    MemesEntity memesEntity = new MemesEntity(null, userName, caption, url, new Date());
    return new ResponseDto(memesRepository.save(memesEntity).getId());
  }
  
  public MemesResponseDto findById(String id) {
    ArrayList<Meme> memes = new ArrayList<Meme>();
    Optional<MemesEntity> memesEntity = memesRepository.findById(id);
    if (memesEntity.isPresent()) {
      String memeId = memesEntity.get().getId();
      String userName = memesEntity.get().getUserName();
      String caption = memesEntity.get().getCaption();
      String url = memesEntity.get().getMemeUrl();
      Date date = memesEntity.get().getCreatedDate();
      memes.add(new Meme(memeId, userName, caption, url, date));
    }
    return new MemesResponseDto(memes);
  }
  
  public MemesResponseDto findAll() {
    ArrayList<Meme> memes = new ArrayList<Meme>();

    Pageable pageable = PageRequest.of(0, 100, Sort.Direction.DESC, "createdDate");

    for (MemesEntity memesEntity: memesRepository.findAll(pageable)) {
      String memeId = memesEntity.getId();
      String userName = memesEntity.getUserName();
      String caption = memesEntity.getCaption();
      String url = memesEntity.getMemeUrl();
      Date date = memesEntity.getCreatedDate();
      memes.add(new Meme(memeId, userName, caption, url, date));
    }
    return new MemesResponseDto(memes);
  }

  public MemesEntity findMeme(Meme meme) {
    String userName = meme.getUserName();
    String caption = meme.getCaption();
    String url = meme.getUrl();
    Optional<MemesEntity> memesEntity = memesRepository.findMeme(userName, url, caption);
    if (memesEntity.isEmpty()) {
      return null;
    } 
    return memesEntity.get();
  }
  
}
