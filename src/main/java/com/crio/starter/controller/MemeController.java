package com.crio.starter.controller;

import com.crio.starter.exchange.Meme;
import com.crio.starter.exchange.ResponseDto;
import com.crio.starter.service.MemesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemeController {
  
  @Autowired
  private MemesService memesService;
  
  @GetMapping({"/memes", "/memes/{id}"})
  public ResponseEntity<?> getMemes(@PathVariable(required = false) String id) {
    if (id != null) {
      List<Meme> memes = memesService.findById(id).getMemes();
      if (memes.size() == 0) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
      }
      return ResponseEntity.ok(memes.get(0));
    }
    return ResponseEntity.ok().body(memesService.findAll().getMemes());
  }
  
  @PostMapping("/memes")
  public ResponseEntity<?> postMemes(@RequestBody Meme meme) {
    if (meme.getCaption() == null || meme.getUrl() == null || meme.getUserName() == null) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    } 

    ResponseDto response = memesService.save(meme);

    if (response == null) {
      return ResponseEntity.status(409).body(null);
    } 

    return ResponseEntity.ok(response);
  }
  
}
