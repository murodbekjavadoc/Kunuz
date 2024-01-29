package main.controller;

import jakarta.servlet.http.HttpServletRequest;
import main.dto.RestLanguageDTO;
import main.enums.AppLanguage;
import main.enums.ProfileRole;
import main.servise.RegionService;
import main.util.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/region")
public class RegionController {
    @Autowired
    private RegionService regionService;

    @PostMapping("/adm/")
    public ResponseEntity<RestLanguageDTO> create(@RequestBody RestLanguageDTO dto,
                                            HttpServletRequest request) {
        HttpRequestUtil.getProfileId(request, ProfileRole.ADMIN);
        RestLanguageDTO regionDTO = regionService.create(dto);
        return ResponseEntity.ok(regionDTO);
    }
    @DeleteMapping("/adm/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Integer id,
                                              HttpServletRequest request){
        HttpRequestUtil.getProfileId(request);
        regionService.deleteById(id);
        return ResponseEntity.ok(true);
    }
    @PutMapping("/adm")
    public ResponseEntity<?> update(@RequestParam("id") Integer id,
                                    @RequestBody RestLanguageDTO regionDTO,
                                    HttpServletRequest request) {
        HttpRequestUtil.getProfileId(request,ProfileRole.ADMIN);
        regionService.update(id, regionDTO);
        return ResponseEntity.ok(true);
    }

    @GetMapping("/adm/getAllList")
    public ResponseEntity<List<RestLanguageDTO>> getAll(HttpServletRequest request) {
        HttpRequestUtil.getProfileId(request,ProfileRole.ADMIN);
        return ResponseEntity.ok(regionService.getALl());
    }
    @GetMapping("/byLang")
    public ResponseEntity<List<RestLanguageDTO>> getByLang(@RequestParam(value = "lang", defaultValue = "uz") AppLanguage lang) {
        List<RestLanguageDTO> byLang = regionService.getByLang(lang);
        return ResponseEntity.ok(byLang);
    }

}
