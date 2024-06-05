package motion.programming.users.converter;

import lombok.RequiredArgsConstructor;
import motion.programming.users.controller.UserResponseDTO;
import motion.programming.users.dto.CityDTO;
import motion.programming.users.dto.StateDTO;
import motion.programming.users.dto.UserRequestDTO;
import motion.programming.users.entity.User;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class UserConverter {

    public User toSaveUser(UserRequestDTO request) {

        return User.builder()
                .cpf(request.cpf())
                .name(request.name())
                .age(request.age())
                .birthday(request.birthday())
                .phone(request.phone())
                .address(request.address())
                .creationDay(LocalDateTime.now())
                .build();
    }

    public User toUpdateUser(UserRequestDTO request, User user) {
        return User.builder()
                .cpf(request.cpf())
                .name(request.name())
                .age(request.age())
                .birthday(request.birthday())
                .phone(request.phone())
                .address(request.address())
                .city(null)
                .state(null)
                .creationDay(user.getCreationDay())
                .lastUpdate(LocalDateTime.now())
                .build();
    }

    public Mono<UserResponseDTO> toUserDTO(Mono<User> user) {
        return user.map(attribute -> {
            return UserResponseDTO.builder()
                    .cpf(attribute.getCpf())
                    .name(attribute.getName())
                    .age(attribute.getAge())
                    .birthday(attribute.getBirthday())
                    .phone(attribute.getPhone())
                    .address(attribute.getAddress())
                    .city(attribute.getCity().name())
                    .state(attribute.getState().abbreviation())
                    .build();
        });
    }
}
