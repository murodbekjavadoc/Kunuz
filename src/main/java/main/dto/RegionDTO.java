package main.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegionDTO extends BaseDTO {
    private Integer orderNumber;
    private String nameUz;
    private String nameRu;
    private String nameEng;
    private String name;
}
