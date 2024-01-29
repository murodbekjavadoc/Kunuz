package main.controller;

import jakarta.servlet.http.HttpServletRequest;
import main.dto.PaginationResultDTO;
import main.dto.ProfileDTO;
import main.enums.ProfileRole;
import main.servise.ProfileService;
import main.util.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping("/adm/profileCreatedByAdmin")
    public ResponseEntity<?> profileCreatedByAdmin(@RequestBody ProfileDTO dto,
                                                   HttpServletRequest request) {

        HttpRequestUtil.getProfileRoleAndID(request, ProfileRole.ADMIN);
        profileService.profileCreatedByAdmin(dto);
        return ResponseEntity.ok(true);
    }

    @PutMapping("/updateProfile/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,
                                    @RequestBody ProfileDTO dto,
                                    HttpServletRequest request) {
        HttpRequestUtil.getProfileRoleAndID(request,ProfileRole.ADMIN);
        profileService.update(id, dto);
        return ResponseEntity.ok(profileService.update(id, dto));
    }

    @PutMapping("/updateUser")
    public ResponseEntity<?> updateByUser(@RequestParam ProfileDTO dto,
                                          HttpServletRequest request) {
        Integer id = HttpRequestUtil.getProfileId(request, ProfileRole.ADMIN, ProfileRole.USER);

        return ResponseEntity.ok(profileService.update(id, dto));
    }

    @GetMapping("/adm/getProfileList")
    public ResponseEntity<PaginationResultDTO<ProfileDTO>> getPagination(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                                         @RequestParam(value = "size", defaultValue = "1") Integer size,
                                                                         HttpServletRequest request) {
        HttpRequestUtil.getProfileId(request,ProfileRole.ADMIN);
        return ResponseEntity.ok(profileService.getAllPagination(page - 1, size));
    }

    @DeleteMapping("/adm{id}")
    public ResponseEntity<?> deleteByID(@PathVariable(value = "id", required = false) Integer id,
                                        HttpServletRequest request){
        HttpRequestUtil.getProfileId(request,ProfileRole.ADMIN);
        profileService.deleteByID(id);
        return ResponseEntity.ok(true);
    }
}