package main.controller;

import jakarta.servlet.http.HttpServletRequest;
import main.dto.JwtDTO;
import main.dto.RestLanguageDTO;
import main.enums.AppLanguage;
import main.enums.ProfileRole;
import main.servise.ArticleTypeService;
import main.util.HttpRequestUtil;
import main.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articleType")
public class ArticleTypeController {
    @Autowired
    private ArticleTypeService articleTypeService;

    @PostMapping("/adm")
    public ResponseEntity<RestLanguageDTO> create(@RequestBody RestLanguageDTO dto,
                                                  HttpServletRequest request) {
        HttpRequestUtil.getProfileId(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(articleTypeService.create(dto));
    }

    @PutMapping("/adm")
    public ResponseEntity<RestLanguageDTO> update(@RequestParam("id") Integer id,
                                                  @RequestBody RestLanguageDTO dto,
                                                  HttpServletRequest request) {
        HttpRequestUtil.getProfileId(request, ProfileRole.ADMIN);
        articleTypeService.update(id, dto);
        return ResponseEntity.ok(articleTypeService.update(id, dto));
    }

    @DeleteMapping("/adm")
    public ResponseEntity<Boolean> delete(@RequestParam("id") Integer id,
                                          HttpServletRequest request) {
        HttpRequestUtil.getProfileRoleAndID(request, ProfileRole.ADMIN);
        articleTypeService.delete(id);
        return ResponseEntity.ok(true);
    }

    @PutMapping("/adm/getall")
    public ResponseEntity<?> getAllPagination(@RequestParam(value = "page", defaultValue =  "1") Integer page,
                                              @RequestParam(value = "size", defaultValue = "2") Integer size,
                                              HttpServletRequest request) {

        HttpRequestUtil.getProfileId(request);
        return ResponseEntity.ok(articleTypeService.getAllPagination(page -1, size));
    }
    @GetMapping("/getByLang")
    public ResponseEntity<?> getByLang(@RequestParam AppLanguage lang){
        return ResponseEntity.ok(articleTypeService.getByLang(lang));
    }
}
