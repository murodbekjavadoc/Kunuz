package main.repository;

import jakarta.transaction.Transactional;
import main.entity.ProfileEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProfileRepository extends CrudRepository<ProfileEntity,Integer>{
    Optional<ProfileEntity> findByEmail(String adminEmail);
    Optional<ProfileEntity> findAllByEmailAndPassword(String email,String password);

    Page<ProfileEntity> findAllByVisibleTrueOrderByCreatedDate(Pageable pageable);

    @Query( value = "select count (*) from profile as p where email = ?1", nativeQuery = true)
    Integer checkCountByEmail(String email);
    @Transactional
    @Modifying
    @Query("update RegionEntity set visible=false where id=:id")
    Integer delete(@Param("id") Integer id);
}
