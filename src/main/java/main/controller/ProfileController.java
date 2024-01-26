package main.controller;

import jakarta.persistence.PostRemove;
import main.dto.JwtDTO;
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

    @PostMapping("/updateAdminDetail{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") Integer id,
            @RequestBody ProfileDTO dto,
            @RequestHeader(value = "Authorization") String jwt) {
        JwtDTO jwtDTO = JWTUtil.decode(jwt);
        if (!jwtDTO.getProfileRole().equals(ProfileRole.ADMIN)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        profileService.updateByAdmin(id, dto);
        return ResponseEntity.ok(true);
    }

    @PutMapping("update")
    public ResponseEntity<?> updateByProfile(
            @RequestParam("id") Integer id,
            @RequestBody ProfileDTO dto) {
        profileService.update(id, dto);
        return ResponseEntity.ok(true);
    }
}
