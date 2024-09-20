package motion.programming.users.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import motion.programming.users.dto.CityDTO;
import motion.programming.users.dto.StateDTO;
import motion.programming.users.enums.RoleEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("Users")
public class User implements UserDetails {

    @Id
    private String cpf;

    @Field("nome")
    private String name;

    @Field("email")
    private String email;

    @Field("senha")
    private String password;

    @Field("nascimento")
    private LocalDate birthday;

    @Field("contato")
    private String phone;

    @Field("endereco")
    private String address;

    @Field("cidade")
    private CityDTO city;

    @Field("uf")
    private StateDTO state;

    @Field("criacao")
    private LocalDateTime creationDay;

    @Field("ultimaAtualizacao")
    private LocalDateTime lastUpdate;

    @Field("role")
    private RoleEnum role;

    public int getCurrentAge() {
        final var today = LocalDate.now();
        final var differenceYears = today.minusYears(this.getBirthday().getYear()).getYear();

        if (isMonthAfterBirthday(today) || isDayAfterBirthday(today))
            return differenceYears;

        return differenceYears - 1;
    }

    private boolean isDayAfterBirthday(LocalDate today) {
        return today.getMonthValue() == this.birthday.getMonthValue() && today.getDayOfMonth() > this.birthday.getDayOfMonth();
    }

    private boolean isMonthAfterBirthday(LocalDate today) {
        return today.getMonthValue() > this.birthday.getMonthValue();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.toString()));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
