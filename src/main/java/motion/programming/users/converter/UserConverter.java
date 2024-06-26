package motion.programming.users.converter;

import lombok.RequiredArgsConstructor;
import motion.programming.users.controller.UserResponseDTO;
import motion.programming.users.dto.UserRequestDTO;
import motion.programming.users.entity.User;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static java.lang.Boolean.TRUE;
import static motion.programming.users.utility.Format.formatAndMaskCpf;

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

        user.setCpf(request.cpf());
        user.setName(request.name());
        user.setAge(request.age());
        user.setBirthday(request.birthday());
        user.setPhone(request.phone());
        user.setAddress(request.address());
        user.setLastUpdate(LocalDateTime.now());

        return user;
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

    public UserResponseDTO toUserDTO(User user) {
        return UserResponseDTO.builder()
                .cpf(formatAndMaskCpf(user.getCpf(), TRUE))
                .name(user.getName())
                .age(user.getAge())
                .birthday(user.getBirthday())
                .phone(user.getPhone())
                .address(user.getAddress())
                .city(user.getCity().name())
                .state(user.getState().abbreviation())
                .build();
    }
}
