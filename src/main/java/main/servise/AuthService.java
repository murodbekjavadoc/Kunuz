package main.servise;

import main.dto.AuthDTO;
import main.dto.ProfileDTO;
import main.entity.ProfileEntity;
import main.exp.AppBadException;
import main.repository.ProfileRepository;
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
        ProfileDTO dto = new ProfileDTO();
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setRole(entity.getRole());

        return dto;
    }
}
