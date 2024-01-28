package main.controller;

import main.dto.JwtDTO;
import main.dto.PaginationResultDTO;
import main.dto.ProfileDTO;
import main.enums.ProfileRole;
import main.servise.ProfileService;
import main.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping("/profileCreatedByAdmin")
    public ResponseEntity<?> profileCreatedByAdmin(@RequestBody ProfileDTO dto,
                                                   @RequestHeader(value = "Authorization", defaultValue = " ") String jwt) {
        JwtDTO jwtDTO = JWTUtil.decode(jwt);
        if (!jwtDTO.getProfileRole().equals(ProfileRole.ADMIN)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        profileService.profileCreatedByAdmin(dto);
        return ResponseEntity.ok(true);
    }

    @PutMapping("/updateProfile/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") Integer id,
            @RequestBody ProfileDTO dto,
            @RequestHeader(value = "Authorization") String jwt) {
        JwtDTO jwtDTO = JWTUtil.decode(jwt);
        if (!jwtDTO.getProfileRole().equals(ProfileRole.ADMIN)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        profileService.update(id, dto);
        return ResponseEntity.ok(profileService.update(id, dto));
    }

    @PutMapping("/updateUser")
    public ResponseEntity<?> updateByUser(@RequestParam ProfileDTO dto,
                                          @RequestHeader(value = "Authorization", defaultValue = " ") String jwt) {
        JwtDTO jwtDTO = JWTUtil.decode(jwt);
        return ResponseEntity.ok(profileService.update(jwtDTO.getId(), dto));
    }

    @GetMapping("/getProfileList")
    public ResponseEntity<PaginationResultDTO<ProfileDTO>> getPagination(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                                         @RequestParam(value = "size", defaultValue = "1") Integer size,
                                                                         @RequestHeader(value = "Authorization", defaultValue = " ") String jwt) {
        JwtDTO jwtDTO = JWTUtil.decode(jwt);
        if (!jwtDTO.getProfileRole().equals(ProfileRole.ADMIN)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(profileService.getAllPagination(page - 1, size));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteByID(
            @PathVariable(value = "id", required = false) Integer id,
            @RequestHeader(value = "Authorization", defaultValue = " ") String jwt) {
        JwtDTO jwtDTO = JWTUtil.decode(jwt);

        if (!jwtDTO.getProfileRole().equals(ProfileRole.ADMIN)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        profileService.deleteByID(id);
        return ResponseEntity.ok(true);
    }

}