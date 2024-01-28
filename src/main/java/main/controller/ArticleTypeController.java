package main.controller;

import main.dto.ArticleTypeDTO;
import main.dto.JwtDTO;
import main.enums.ProfileRole;
import main.servise.ArticleTypeService;
import main.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articleType")
public class ArticleTypeController {
    @Autowired
    private ArticleTypeService articleTypeService;
    @PostMapping("")
    public ResponseEntity<ArticleTypeDTO> create (@RequestBody ArticleTypeDTO dto,
                                     @RequestHeader ("Authorization") String jwt){
        JwtDTO jwtDTO = JWTUtil.decode(jwt);
        if (!jwtDTO.getProfileRole().equals(ProfileRole.ADMIN)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        articleTypeService.create(dto);
        return ResponseEntity.ok(articleTypeService.create(dto));
    }
//    @PutMapping("")
//    public ResponseEntity<?> update (@RequestBody ArticleTypeDTO dto,
//                                     @RequestHeader (value = "Authorization", defaultValue = " ") String jwt){
//
//        if
//        articleTypeService.update(dto);
//        return ResponseEntity.ok(true);
//    }





}






//         2. Update by id (ADMIN)
//         (order_number,name_uz, name_ru, name_en)
//         3. Delete by id (ADMIN)
//         4. Get List (ADMIN) (Pagination)
//         (id,key,name_uz, name_ru, name_en,visible,created_date)
//         5. Get By Lang (Language keladi shu language dagi name larini berib yuboramiz)
//         (id,key,name) (name ga tegishli name_.. dagi qiymat qo'yiladi.)