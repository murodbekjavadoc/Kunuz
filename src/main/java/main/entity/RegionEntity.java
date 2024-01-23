package main.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Region")
public class RegionEntity extends BaseEntity {
    //    id,order_number,name_uz, name_ru, name_en,visible,created_date
    private String nameUz;
    private String nameRu;
    private String nameEng;
}
