package TableTap.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private LocalDateTime timeOfLogin = LocalDateTime.now();
    private String accessToken;
}
