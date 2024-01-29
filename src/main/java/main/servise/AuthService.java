package main.servise;

import io.jsonwebtoken.JwtException;
import main.dto.AuthDTO;
import main.dto.JwtDTO;
import main.dto.ProfileDTO;
import main.entity.ProfileEntity;
import main.enums.ProfileStatus;
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
    @Autowired
    private MailSenderService mailSenderService;
    public ProfileDTO auth(AuthDTO authDTO){

        Optional<ProfileEntity> optional = profileRepository.findAllByEmailAndPassword(authDTO.getEmail()
                ,MDUtil.encode(authDTO.getPassword()));

        if (optional.isEmpty()){
            throw new AppBadException("email or password nor wrong");
        }
        ProfileEntity entity = optional.get();
        if (!entity.getStatus().equals(ProfileStatus.ACTIVE)) {
            throw new AppBadException("Profile not active");
        }



        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setName(entity.getName());
        profileDTO.setSurname(entity.getSurname());
        profileDTO.setRole(entity.getRole());
        profileDTO.setJwt(JWTUtil.encode(entity.getId(),entity.getRole()));
        return profileDTO;
    }


    public ProfileDTO registration(ProfileDTO dto) {
        Optional<ProfileEntity> optional = profileRepository.findByEmail(dto.getEmail());
        if (optional.isPresent()) {
            if (optional.get().getStatus().equals(ProfileStatus.REGISTRATION)) {
               profileRepository.delete(optional.get());  // delete

                // or
                //send verification code (email/sms)
            } else {
                throw new AppBadException("Email exists");
            }
        }
        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setStatus(ProfileStatus.REGISTRATION);
        entity.setPassword(MDUtil.encode(dto.getPassword()));

        profileRepository.save(entity);
        dto.setJwt(JWTUtil.encode(dto.getId(),dto.getRole()));

        dto.setCreatedDate(entity.getCreatedDate());
        dto.setId(entity.getId());
        dto.setRole(entity.getRole());
        dto.setStatus(entity.getStatus());
        String jwt = JWTUtil.encodeForEmail(entity.getId());

        String text = "Hello. \n To complete registration please link to the following link\n"
                + "http://localhost:8080/auth/verification/email/" + jwt;

        mailSenderService.sendEmail(dto.getEmail(),"Complete registration", text);

        return dto;
    }

    public String emailVerification(String jwt) {
        try {
            JwtDTO jwtDTO = JWTUtil.decode(jwt);

            Optional<ProfileEntity> optional = profileRepository.findById(jwtDTO.getId());
            if (optional.isEmpty()) {
                throw new AppBadException("Profile not found");
            }
            ProfileEntity entity = optional.get();
            if (!entity.getStatus().equals(ProfileStatus.REGISTRATION)) {
                throw new AppBadException("Profile in wrong status");
            }
            profileRepository.updateStatus(entity.getId(), ProfileStatus.ACTIVE);
        } catch (JwtException e) {
            throw new AppBadException("Please tyre again.");
        }    return null;
    }
}
