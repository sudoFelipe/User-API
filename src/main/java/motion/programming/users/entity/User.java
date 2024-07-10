package motion.programming.users.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import motion.programming.users.dto.CityDTO;
import motion.programming.users.dto.StateDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("Users")
public class User {

    @Id
    private String cpf;

    @Field("nome")
    private String name;

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
}
