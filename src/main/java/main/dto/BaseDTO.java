package main.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BaseDTO {
    protected Integer id;
    protected LocalDateTime createdDate;
    protected Boolean visible;
}
