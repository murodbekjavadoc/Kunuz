package main.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import main.enums.ProfileRole;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileDTO extends BaseDTO{
    private String name;
    private String surname;
    private String email;
    private String password;
    private ProfileStatus status;
    private ProfileRole role;
    protected LocalDateTime updatedDate;


}
