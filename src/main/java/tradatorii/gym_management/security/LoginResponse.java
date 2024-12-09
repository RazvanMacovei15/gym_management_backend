package tradatorii.gym_management.security;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import tradatorii.gym_management.DTO.UserDTO;

@Setter
@Getter
@Builder
public class LoginResponse {
    private String token;
    private long expiresIn;
    private UserDTO user;
}
