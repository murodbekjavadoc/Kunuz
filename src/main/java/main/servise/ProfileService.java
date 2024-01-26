package main.servise;

import main.dto.ProfileDTO;
import main.entity.ProfileEntity;
import main.enums.ProfileRole;
import main.exp.AppBadException;
import main.repository.ProfileRepository;
import main.util.JWTUtil;
import main.util.MDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    public void update(Integer id, ProfileDTO  dto) {
        ProfileEntity entity = get(id);
        if (!(dto.getName() == null)){
            entity.setName(dto.getName());
        }
        if (!(dto.getSurname() == null)){
            entity.setSurname(dto.getSurname());
        }
        if (!(dto.getPassword() == null)){
            entity.setPassword(dto.getPassword());
        }
        if (!(dto.getEmail() == null)){
            if (profileRepository.checkCountByEmail(dto.getEmail()) >1) {
                throw new AppBadException("Kiritilingan email data bazada mavjud va u boshqa userga tegishli");
            }
            entity.setEmail(dto.getEmail());
        }
        profileRepository.save(entity);
    }

    //=========================== UTIL =======================
    private ProfileEntity get(Integer id){
        return profileRepository.findById(id).orElseThrow(()
                -> new AppBadException("Id not found"));
    }
    private void checkInEmail(String email) {
        Optional<ProfileEntity> optional = profileRepository.findByEmail(email);
        if (optional.isPresent()){
            return;
        }
        throw new AppBadException("Email is empty");
    }
    public void updateByAdmin(Integer id, ProfileDTO dto) {

    }

}
