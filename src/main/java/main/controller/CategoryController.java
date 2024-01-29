package main.controller;

import jakarta.servlet.http.HttpServletRequest;
import main.dto.RestLanguageDTO;
import main.enums.AppLanguage;
import main.enums.ProfileRole;
import main.servise.CategoryService;
import main.util.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/adm")
    public ResponseEntity<RestLanguageDTO> create(@RequestBody RestLanguageDTO dto,
                                              HttpServletRequest request) {
        HttpRequestUtil.getProfileRoleAndID(request,ProfileRole.ADMIN);
        RestLanguageDTO categoryDTO = categoryService.create(dto);
        return ResponseEntity.ok(categoryDTO);
    }
    @PutMapping("/adm{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") Integer id,
                                        @RequestBody RestLanguageDTO dto,
                                        HttpServletRequest request) {

        HttpRequestUtil.getProfileRoleAndID(request,ProfileRole.ADMIN);
        categoryService.updateById(dto, id);
        return ResponseEntity.ok(true);
    }
    @DeleteMapping("/adm{id}")
    public ResponseEntity<?> deleteById(@PathVariable ("id") Integer id,
                                        HttpServletRequest request){
        HttpRequestUtil.getProfileRoleAndID(request,ProfileRole.ADMIN);
        categoryService.deleteById(id);
        return ResponseEntity.ok(true);
    }
    @GetMapping("/adm/getAll")
    public ResponseEntity<List<RestLanguageDTO>> getAll ( HttpServletRequest request){

        HttpRequestUtil.getProfileId(request,ProfileRole.ADMIN);
        return ResponseEntity.ok(categoryService.getAll());
    }
    @GetMapping("/getLanguage")
    public ResponseEntity<List<RestLanguageDTO>> getByLang(@RequestParam("lang") AppLanguage lang){
        return ResponseEntity.ok(categoryService.getByLang(lang));
    }
}
