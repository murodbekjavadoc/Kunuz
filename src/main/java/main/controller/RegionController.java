package main.controller;

import main.dto.JwtDTO;
import main.dto.RegionDTO;
import main.entity.RegionEntity;
import main.enums.AppLanguage;
import main.enums.ProfileRole;
import main.servise.RegionService;
import main.util.JWTUtil;
import org.hibernate.sql.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/region")
public class RegionController {
    @Autowired
    private RegionService regionService;

    //task =1=
    @PostMapping("")
    public ResponseEntity<RegionDTO> create(@RequestBody RegionDTO dto,
                                            @RequestHeader(value = "Authorization") String jwt) {
        JwtDTO jwtDTO = JWTUtil.decode(jwt);
        if (!jwtDTO.getProfileRole().equals(ProfileRole.ADMIN)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        RegionDTO regionDTO = regionService.create(dto);
        return ResponseEntity.ok(regionDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Integer id,
                                              @RequestHeader(value = "Authorization") String jwt) {
        JwtDTO jwtDTO = JWTUtil.decode(jwt);
        if (!jwtDTO.getProfileRole().equals(ProfileRole.ADMIN)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        regionService.deleteById(id);
        return ResponseEntity.ok(true);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") Integer id,
            @RequestHeader(value = "Authorization", defaultValue = " ") String jwt,
            @RequestBody RegionDTO regionDTO) {

        JwtDTO jwtDTO = JWTUtil.decode(jwt);
        if (!jwtDTO.getProfileRole().equals(ProfileRole.ADMIN)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        regionService.update(id, regionDTO);
        return ResponseEntity.ok(true);
    }

    // task =3=
    //task =4=
    @GetMapping("/getAllList")
    public ResponseEntity<List<RegionDTO>> getAll(@RequestHeader(value = "Authorization", defaultValue = " ") String jwt) {
        JwtDTO jwtDTO = JWTUtil.decode(jwt);
        if (!jwtDTO.getProfileRole().equals(ProfileRole.ADMIN)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(regionService.getALl());
    }
    // task =5=
    @GetMapping("/byLang")
    public ResponseEntity<List<RegionDTO>> getByLang(@RequestParam(value = "lang", defaultValue = "uz") AppLanguage language) {
        List<RegionDTO> byLang = regionService.getByLang(language);
        return ResponseEntity.ok(byLang);
    }

//        (id,key,name_uz, name_ru, name_en,visible,created_date)
//            5. Get By Lang (Language keladi shu language dagi name larini berib yuboramiz)
//        (id,key,name) (name ga tegishli name_.. dagi qiymat qo'yiladi.) (visible true)
}
