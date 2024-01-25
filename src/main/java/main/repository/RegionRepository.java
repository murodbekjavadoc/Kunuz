package main.repository;

import jakarta.transaction.Transactional;
import main.entity.RegionEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RegionRepository extends CrudRepository<RegionEntity,Integer> {
    @Transactional
    @Modifying
    @Query("update RegionEntity set visible=false where id=:id")
    Integer delete(@Param("id") Integer id);
}
