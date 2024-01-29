package main.servise;

import main.dto.RestLanguageDTO;
import main.entity.RegionEntity;
import main.enums.AppLanguage;
import main.exp.AppBadException;
import main.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;

    //task =1=
    public RestLanguageDTO create(RestLanguageDTO dto) {
        RegionEntity entity = fromRegionDTOToRegionEntity(dto);
        regionRepository.save(entity);return dto;}

    // task =2=
    public void update(Integer id, RestLanguageDTO dto) {
        RegionEntity entity = get(id);
        entity.setOrderNumber(dto.getOrderNumber());
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEng(dto.getNameEng());
        regionRepository.save(entity);
    }

    // task =3=
    public void deleteById(Integer id) {
        RegionEntity entity = get(id);
        entity.setVisible(false);
        regionRepository.save(entity);
    }

    // task =4=
    public List<RestLanguageDTO> getALl() {
        Iterable<RegionEntity> listEntity = regionRepository.findAll();
        List<RestLanguageDTO> listDTO = new LinkedList<>();
        for (RegionEntity entity : listEntity) {
            listDTO.add(setRegionDTO(entity));
        }
        return listDTO;
    }

    // task =5=
    public List<RestLanguageDTO> getByLang(AppLanguage lang) {
        List<RestLanguageDTO> dtoList = new LinkedList<>();
        Iterable<RegionEntity> all = regionRepository.findAll();

        for (RegionEntity entity : all) {
            RestLanguageDTO dto = new RestLanguageDTO();
            dto.setId(entity.getId());
            switch (lang) {
                case uz -> dto.setName(entity.getNameUz());
                case ru -> dto.setName(entity.getNameRu());
                default -> dto.setName(entity.getNameEng());
            }
            ;
            dtoList.add(dto);
        }
        return dtoList;
    }
    private RestLanguageDTO setRegionDTO(RegionEntity entity) {
        RestLanguageDTO regionDTO = new RestLanguageDTO();
        regionDTO.setId(entity.getId());
        regionDTO.setOrderNumber(entity.getOrderNumber());
        regionDTO.setVisible(entity.getVisible());
        regionDTO.setNameEng(entity.getNameEng());
        regionDTO.setNameUz(entity.getNameUz());
        regionDTO.setNameRu(entity.getNameRu());
        regionDTO.setCreatedDate(entity.getCreatedDate());
        return regionDTO;
    }

    // ======================= Utils ==============================
    private RegionEntity fromRegionDTOToRegionEntity(RestLanguageDTO dto) {
        RegionEntity entity = new RegionEntity();
        entity.setOrderNumber(dto.getOrderNumber());
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEng(dto.getNameEng());
        return entity;
    }

    private RegionEntity get(Integer id) {
     return  regionRepository.findById(id).orElseThrow(()
             -> new AppBadException("Region not found"));
    }
}
