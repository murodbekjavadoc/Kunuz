package main.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestLanguageDTO extends BaseDTO{
    private Integer orderNumber;
    private String nameUz;
    private String nameRu;
    private String nameEng;
    private String name;
}
