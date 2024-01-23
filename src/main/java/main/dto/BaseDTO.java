package main.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BaseDTO {
    private Integer id;
    private LocalDateTime createdDate;
    private Boolean visible;
}
