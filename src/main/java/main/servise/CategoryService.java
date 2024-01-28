package main.servise;

import main.dto.CategoryDTO;
import main.entity.CategoryEntity;
import main.enums.AppLanguage;
import main.exp.AppBadException;
import main.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

//    @PostMapping("")
    public CategoryDTO create(CategoryDTO dto) {
        CategoryEntity entity = new CategoryEntity();

        entity.setOrderNumber(dto.getOrderNumber());
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEng(dto.getNameEng());
        categoryRepository.save(entity);
        dto.setId(entity.getId());
        dto.setVisible(entity.getVisible());
        dto.setCreatedDate(entity.getCreatedDate());

        return dto;
    }
//    @PutMapping("{id}")
    public void updateById(CategoryDTO dto, Integer id) {
        CategoryEntity entity = get(id);
        if (!(dto.getOrderNumber() ==null)){
            entity.setOrderNumber(dto.getOrderNumber());
        }
        if (!(dto.getNameUz() ==null)){
            entity.setNameEng(dto.getNameEng());
        }
        if(!(dto.getNameRu() == null)){
            entity.setNameRu(dto.getNameRu());
        }
        if (!(dto.getNameEng() == null)){
            entity.setNameEng(dto.getNameEng());
        }
            categoryRepository.save(entity);
    }

    private CategoryEntity get(Integer id){
        return categoryRepository.findById(id).orElseThrow(()->
                new AppBadException(" id not found"));
    }

    public void deleteById(Integer id) {
        categoryRepository.deleteById(id);
    }

    public List<CategoryDTO> getAll() {
        Iterable<CategoryEntity> entityList = categoryRepository.findAll();
        List<CategoryDTO> dtoList = new LinkedList<>();

        for(CategoryEntity entities : entityList){
            CategoryDTO dto = new CategoryDTO();
            dto.setOrderNumber(entities.getOrderNumber());
            dto.setNameUz(entities.getNameUz());
            dto.setNameRu(entities.getNameRu());
            dto.setNameEng(entities.getNameEng());
            dto.setId(entities.getId());
            dtoList.add(dto);
        }
        return dtoList;
    }

    public List<CategoryDTO> getByLang(AppLanguage lang) {
        Iterable<CategoryEntity> entities = categoryRepository.findAll();
        List<CategoryDTO> dtoList = new LinkedList<>();
        for (CategoryEntity entity : entities) {
            CategoryDTO dto = new CategoryDTO();
            dto.setId(entity.getId());
            switch (lang){
                case uz -> dto.setName(entity.getNameUz());
                case ru -> dto.setName(entity.getNameRu());
                default -> dto.setName(entity.getNameEng());
            }
            dtoList.add(dto);
        }
        return dtoList;
    }
}

