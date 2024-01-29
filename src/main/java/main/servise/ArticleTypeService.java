package main.servise;

import main.dto.PaginationResultDTO;
import main.dto.RestLanguageDTO;
import main.entity.ArticleTypeEntity;
import main.enums.AppLanguage;
import main.exp.AppBadException;
import main.repository.ArticleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;


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
//    @PutMapping("/adm")
    public RestLanguageDTO update(Integer id, RestLanguageDTO dto) {
        ArticleTypeEntity entity=get(id);
        if (dto.getOrderNumber() != null){
            entity.setOrder_number(dto.getOrderNumber());
        }
        if (dto.getNameUz() !=null){
            entity.setName_uz(dto.getNameUz());
        }
        if (dto.getNameRu() != null){
            entity.setName_ru(dto.getNameRu());
        }
        if (dto.getNameEng() != null){
            entity.setName_en(dto.getNameEng());
        }
        if (dto.getVisible() != null){
            entity.setVisible(dto.getVisible());
        }

        articleTypeRepository.save(entity);
        return dto;
    }

    public void delete(Integer id) {
        ArticleTypeEntity entity = get(id);
        entity.setVisible(false);
        articleTypeRepository.save(entity);
    }

    public PaginationResultDTO<RestLanguageDTO> getAllPagination(Integer  page, Integer size) {
        Pageable pageable =  PageRequest.of(page,size);

        Page<ArticleTypeEntity> entityPage = articleTypeRepository.findAllByVisibleTrueOrderByCreatedDate(pageable);
        List<RestLanguageDTO> dtoList = new LinkedList<>();

        List<ArticleTypeEntity> entities = entityPage.getContent();
        for (ArticleTypeEntity entity : entities) {

            RestLanguageDTO dto = new RestLanguageDTO();
            dto.setId(entity.getId());
            dto.setNameUz(entity.getName_uz());
            dto.setNameRu(entity.getName_ru());
            dto.setNameEng(entity.getName_en());
            dto.setCreatedDate(entity.getCreatedDate());
            dtoList.add(dto);
        }
        return new PaginationResultDTO<RestLanguageDTO>(dtoList,entityPage.getTotalElements());
    }

    public List<RestLanguageDTO> getByLang(AppLanguage language) {

        List<ArticleTypeEntity> entityList = articleTypeRepository.findAllByVisibleTrueOrderByCreatedDate();
        List<RestLanguageDTO> dtoList = new LinkedList<>();


        for (ArticleTypeEntity entity : entityList) {
            RestLanguageDTO dto = new RestLanguageDTO();
            switch (language) {
                case uz -> dto.setName(entity.getName_uz());
                case ru -> dto.setName(entity.getName_ru());
                default -> dto.setName(entity.getName_en());
            }
            dtoList.add(dto);
        }
        return dtoList;
    }


    private ArticleTypeEntity get(Integer id) {
        if (id == null) {
            throw new AppBadException(" id is empty");
        }
        return articleTypeRepository.findById(id).orElseThrow(()
                -> new AppBadException("Article type id not found"));
    }
}
