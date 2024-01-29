package main.servise;

import main.dto.RestLanguageDTO;
import main.entity.ArticleTypeEntity;
import main.repository.ArticleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleTypeService {
    @Autowired
    private ArticleTypeRepository articleTypeRepository;

//    @PostMapping("")
    public RestLanguageDTO create(RestLanguageDTO dto) {
        ArticleTypeEntity entity = new ArticleTypeEntity();
        entity.setName_uz(dto.getNameUz());
        entity.setName_ru(dto.getNameRu());
        entity.setName_en(dto.getNameEng());
        entity.setOrder_number(dto.getOrderNumber());

        articleTypeRepository.save(entity);

        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setVisible(entity.getVisible());

        return dto;
    }
}
