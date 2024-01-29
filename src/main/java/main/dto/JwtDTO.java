package main.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import main.enums.ProfileRole;

@Getter
@Setter
@AllArgsConstructor
public class JwtDTO {
    private  Integer id;
    private ProfileRole profileRole;
    public  JwtDTO (Integer id){
        this.id=id;
    }
}
