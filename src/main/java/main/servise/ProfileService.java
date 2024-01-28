package main.servise;

import main.dto.PaginationResultDTO;
import main.dto.ProfileDTO;
import main.entity.ProfileEntity;
import main.enums.ProfileRole;
import main.exp.AppBadException;
import main.repository.ProfileRepository;
import main.util.JWTUtil;
import main.util.MDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;


//  @PostMapping("/profileCreatedByAdmin")
    public ProfileDTO profileCreatedByAdmin(ProfileDTO dto) {
        Optional<ProfileEntity> option = profileRepository.findByEmail(dto.getEmail());
        if (option.isPresent()) {
            throw new AppBadException("Email registered !");
        }

        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPassword(MDUtil.encode(dto.getPassword()));
        entity.setRole(dto.getRole());

        profileRepository.save(entity);
        dto.setJwt(JWTUtil.encode(dto.getId(),dto.getRole()));

        dto.setCreatedDate(entity.getCreatedDate());
        dto.setId(entity.getId());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    //    @PutMapping("/updateProfile/{id}")
    public ProfileDTO update(Integer id, ProfileDTO dto) {

        ProfileEntity entity = get(id);
        if (!(dto.getName() == null)) {
            entity.setName(dto.getName());
        }
        if (!(dto.getSurname() == null)) {
            entity.setSurname(dto.getSurname());
        }
        if (!(dto.getPassword() == null)) {
            entity.setPassword(dto.getPassword());
        }
        if (!(dto.getEmail() == null)) {
            if (profileRepository.checkCountByEmail(dto.getEmail()) > 1) {
                throw new AppBadException("The entered email is already in the database and belongs to another user !");
            }
            entity.setEmail(dto.getEmail());
        }
        if (!(dto.getVisible() == null)) {
            entity.setVisible(!dto.getVisible());
        }
        if (!(dto.getRole() == null)) {
            if (dto.getRole().equals(ProfileRole.ADMIN)) {
                entity.setRole(ProfileRole.ADMIN);
            } else if (dto.getRole().equals(ProfileRole.USER)){
                entity.setRole(ProfileRole.USER);
            }
        }
        profileRepository.save(entity);
        return dto;
    }

    //@GetMapping("/getProfileList")
    public PaginationResultDTO<ProfileDTO> getAllPagination(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<ProfileEntity> entityPage = profileRepository.findAllByVisibleTrueOrderByCreatedDate(pageable);
        List<ProfileEntity> entityList = entityPage.getContent();
        List<ProfileDTO> dtoList = new LinkedList<>();
        for (ProfileEntity entity : entityList) {
            ProfileDTO dto = new ProfileDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setCreatedDate(entity.getCreatedDate());
            dto.setEmail(entity.getEmail());
            dto.setVisible(entity.getVisible());
            dto.setRole(entity.getRole());
            dto.setStatus(entity.getStatus());
            dtoList.add(dto);
        }
        PaginationResultDTO<ProfileDTO> resultDTO = new PaginationResultDTO<>();
        resultDTO.setList(dtoList);
        resultDTO.setTotalSize(entityPage.getTotalElements());
        return resultDTO;
    }

    //    @DeleteMapping("{id}")
    public void deleteByID(Integer id) {
        ProfileEntity entity = get(id);
        entity.setVisible(false);
        profileRepository.save(entity);
    }

    //=========================== UTIL =======================

    private ProfileEntity get(Integer id) {
        return profileRepository.findById(id).orElseThrow(()
                -> new AppBadException("Id not found"));
    }

    private void checkInEmail(String email) {
        Optional<ProfileEntity> optional = profileRepository.findByEmail(email);
        if (optional.isPresent()) {
            return;
        }
        throw new AppBadException("Email is empty");
    }
}
