package main.controller;

import main.dto.RegionDTO;
import main.servise.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    public ResponseEntity<?> create (@RequestBody RegionDTO dto){
        articleService.create(dto);
        return ResponseEntity.ok(true);
    }
}
