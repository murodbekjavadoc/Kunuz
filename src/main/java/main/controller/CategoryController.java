package main.controller;

import main.dto.CategoryDTO;
import main.dto.JwtDTO;
import main.enums.AppLanguage;
import main.enums.ProfileRole;
import main.servise.CategoryService;
import main.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("")
    public ResponseEntity<CategoryDTO> create(@RequestBody CategoryDTO dto,
                                              @RequestHeader(value = "Authorization", defaultValue = "") String jwt) {
        JwtDTO jwtDTO = JWTUtil.decode(jwt);
        if (!jwtDTO.getProfileRole().equals(ProfileRole.ADMIN)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        CategoryDTO categoryDTO = categoryService.create(dto);
        return ResponseEntity.ok(categoryDTO);
    }
    @PutMapping("{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") Integer id,
                                        @RequestBody CategoryDTO dto,
                                        @RequestHeader(value = "Authorization", defaultValue = " ") String jwt) {
        JwtDTO jwtDTO = JWTUtil.decode(jwt);
        if (!jwtDTO.getProfileRole().equals(ProfileRole.ADMIN)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        categoryService.updateById(dto, id);
        return ResponseEntity.ok(true);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable ("id") Integer id,
                                        @RequestHeader (value = "Authorization",defaultValue = " ") String jwt){
        JwtDTO jwtDTO = JWTUtil.decode(jwt);
        if (!jwtDTO.getProfileRole().equals(ProfileRole.ADMIN)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        categoryService.deleteById(id);
        return ResponseEntity.ok(true);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<CategoryDTO>> getAll (@RequestHeader (value = "Authorization",defaultValue = " ") String jwt){
        JwtDTO jwtDTO = JWTUtil.decode(jwt);
        if (!jwtDTO.getProfileRole().equals(ProfileRole.ADMIN)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(categoryService.getAll());
    }
    @GetMapping("/getLanguage")
    public ResponseEntity<List<CategoryDTO>> getByLang(@RequestParam("lang") AppLanguage lang,
                                                       @RequestHeader (value = "Authorization",defaultValue = "") String jwt){
        JwtDTO jwtDTO = JWTUtil.decode(jwt);
        if (!jwtDTO.getProfileRole().equals(ProfileRole.ADMIN)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(categoryService.getByLang(lang));
    }
}
