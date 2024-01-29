package main.repository;

import main.entity.ArticleTypeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleTypeRepository extends CrudRepository<ArticleTypeEntity, Integer> {

    List<ArticleTypeEntity> findAllByVisibleTrueOrderByCreatedDate();
    Page<ArticleTypeEntity> findAllByVisibleTrueOrderByCreatedDate(Pageable pageable);


}
