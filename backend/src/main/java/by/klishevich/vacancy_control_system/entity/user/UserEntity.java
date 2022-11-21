package by.klishevich.vacancy_control_system.entity.user;

import by.klishevich.vacancy_control_system.entity.BaseEntity;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

import static javax.persistence.EnumType.STRING;

@Entity
@Table(name = "user")
@Data
public class UserEntity implements UserDetails, BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Enumerated(STRING)
    @Column(name = "role")
    private RolesEnum role = RolesEnum.ROLE_USER;

    public static UserEntity guest() {
        UserEntity user = new UserEntity();
        user.setRole(RolesEnum.ROLE_GUEST);
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(role);
    }

    @Override
    public String getUsername() {
        return String.format("%s %s", name, surname);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
