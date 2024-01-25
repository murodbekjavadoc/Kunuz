package main.servise;

import main.dto.ProfileDTO;
import main.entity.ProfileEntity;
import main.exp.AppBadException;
import main.repository.ProfileRepository;
import main.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    public ProfileDTO create(ProfileDTO dto) {
        checkInEmail(dto.getEmail());
        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        profileRepository.save(entity);
        dto.setJwt(JWTUtil.encode(dto.getId(),dto.getRole()));

        dto.setCreatedDate(entity.getCreatedDate());
        dto.setId(entity.getId());
        dto.setRole(entity.getRole());
        dto.setStatus(entity.getStatus());
        return dto;
    }


    private void checkInEmail(String email) {
        Optional<ProfileEntity> optional = profileRepository.findByEmail(email);
        if (optional.isEmpty()){
            return;
        }
        throw new AppBadException("Email mavjud");
    }

    private ProfileEntity get(Integer id){
        return profileRepository.findById(id).orElseThrow(()
                -> new AppBadException("Id not found"));
    }
}
