package main.servise;

import main.dto.AuthDTO;
import main.dto.ProfileDTO;
import main.entity.ProfileEntity;
import main.exp.AppBadException;
import main.repository.ProfileRepository;
import main.util.JWTUtil;
import main.util.MDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private ProfileRepository profileRepository;
    public ProfileDTO auth(AuthDTO authDTO){

        Optional<ProfileEntity> optional = profileRepository.findAllByEmailAndPassword(authDTO.getEmail()
                ,MDUtil.encode(authDTO.getPassword()));

        if (optional.isEmpty()){
            throw new AppBadException("email or password nor wrong");
        }
        ProfileEntity entity = optional.get();
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setName(entity.getName());
        profileDTO.setSurname(entity.getSurname());
        profileDTO.setRole(entity.getRole());
        profileDTO.setJwt(JWTUtil.encode(entity.getId(),entity.getRole()));
        return profileDTO;
    }
}
