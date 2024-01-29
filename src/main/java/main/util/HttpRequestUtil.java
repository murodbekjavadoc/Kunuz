package main.util;

import jakarta.servlet.http.HttpServletRequest;
import main.dto.JwtDTO;
import main.enums.ProfileRole;
import main.exp.ForbiddenException;

public class HttpRequestUtil {
    public static Integer getProfileId(HttpServletRequest request, ProfileRole... requiredRoleList) {
        Integer id = (Integer) request.getAttribute("id");
        ProfileRole role = (ProfileRole) request.getAttribute("role");
        if (requiredRoleList.length == 0){
            return id;
        }
        for (ProfileRole requiredRole: requiredRoleList) {
            if (role.equals(requiredRole)) {
                return id;
            }
        }
        throw new ForbiddenException("Method not allowed");
    }

    public static JwtDTO getProfileRoleAndID(HttpServletRequest request, ProfileRole... requiredRoleList) {
        Integer id = (Integer) request.getAttribute("id");
        ProfileRole role = (ProfileRole) request.getAttribute("role");
        for (ProfileRole requiredRole: requiredRoleList) {
            if (role.equals(requiredRole)) {
                return new JwtDTO(id,role);
            }
        }
        throw new ForbiddenException("Method not allowed");
    }
}
