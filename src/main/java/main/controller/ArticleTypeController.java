package main.controller;

import jakarta.servlet.http.HttpServletRequest;
import main.dto.JwtDTO;
import main.dto.RestLanguageDTO;
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
    public ResponseEntity<RestLanguageDTO> create (@RequestBody RestLanguageDTO dto,
                                                   HttpServletRequest request){
        HttpRequestUtil.getProfileId(request);
        return ResponseEntity.ok(articleTypeService.create(dto));
    }
}






//         2. Update by id (ADMIN)
//         (order_number,name_uz, name_ru, name_en)
//         3. Delete by id (ADMIN)
//         4. Get List (ADMIN) (Pagination)
//         (id,key,name_uz, name_ru, name_en,visible,created_date)
//         5. Get By Lang (Language keladi shu language dagi name larini berib yuboramiz)
//         (id,key,name) (name ga tegishli name_.. dagi qiymat qo'yiladi.)