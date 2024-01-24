package main.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ArticleTypeEntity extends BaseEntity {
    private String name;

//    2. ArticleType
//            id,order_number,name_uz, name_ru, name_en,visible,created_date
//     (Asosiy,Muharrir tanlovi,Dolzarb, Maqola,
//    Foto yangilik,Interview,Biznes,Surushturuv,Video Yangilik)
//
}
