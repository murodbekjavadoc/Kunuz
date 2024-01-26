package main.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ArticleTypeEntity extends BaseEntity {
    private String name;
    @Column(nullable = false)
    private Integer order_number;
    @Column(nullable = false)
    private String name_uz;
    private String name_ru;
    private String name_en;


}
