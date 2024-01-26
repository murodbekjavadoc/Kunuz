package main.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "categort")
public class CategoryEntity extends BaseEntity{
    @Column(name = "order_number",nullable = false)
    private Integer orderNumber;
    @Column(name = "name_uz",nullable = false)
    private String nameUz;
    @Column(name = "name_Ru",nullable = false)
    private String nameRu;
    @Column(name = "name_En",nullable = false)
    private String nameEng;
}
