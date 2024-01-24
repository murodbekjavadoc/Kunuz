package main.repository;

import main.entity.RegionEntity;
import org.springframework.data.repository.CrudRepository;

public interface RegionRepository extends CrudRepository<RegionEntity,Integer> {
}
