package org.example.DTOs;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Long id;

    private String userName;

    private String password;
    private String email;
    private String role;
}
