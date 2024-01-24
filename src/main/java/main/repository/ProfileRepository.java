package main.repository;

import main.entity.ProfileEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProfileRepository extends CrudRepository<ProfileEntity,Integer>{
    Optional<ProfileEntity> findByEmail(String adminEmail);
    Optional<ProfileEntity> findAllByEmailAndPassword(String email,String password);
}
