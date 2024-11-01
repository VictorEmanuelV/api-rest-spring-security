package dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.entity;
import dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@EqualsAndHashCode(of = "id")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "TB_USER")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "email",nullable = false,unique = true)
    private String email;
    @Column(name = "password",nullable = false)
    private String password;
    @Column(name = "role",nullable = false)
    private RoleEnum role;

}
