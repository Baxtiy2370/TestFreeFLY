package uz.b.testfreefly.domains;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private double balance;



    public enum Role{
        DIRECTOR,MANAGER,USER,TICKETMAN;
    }
}


