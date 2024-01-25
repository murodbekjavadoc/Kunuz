package main.controller;

import main.dto.ProfileDTO;
import main.entity.ProfileEntity;
import main.servise.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;
//        1. Create profile (ADMIN) (can create MODERATOR,PUBLISHER))
//            (name,surname,email,phone,password,status,role)
//            2. Update Profile (ADMIN)
//    3. Update Profile Detail (ANY) (Profile updates own details)
//            4. Profile List (ADMIN) (Pagination)
//            5. Delete Profile By Id (ADMIN)
//    6. Update Photo (ANY) (Murojat qilgan odamni rasmini upda qilish)   (remove old image)
//    photo_id
//    7. Filter (name,surname,phone,role,created_date_from,created_date_to)

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody ProfileDTO profileDTO) {
        ProfileDTO profileDTO1 = profileService.create(profileDTO);
        return ResponseEntity.ok(profileDTO1);
    }

}
