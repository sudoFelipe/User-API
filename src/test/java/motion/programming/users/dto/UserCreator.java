package motion.programming.users.dto;

import motion.programming.users.entity.User;

public class UserCreator {

    public static User createUserToBeSaved() {
        return User.builder()
                .name("Naruto Sun Tzu Li")
                .build();
    }

    public static User createValidUser() {
        return User.builder()
                .cpf("12345678901")
                .name("Naruto Sun Tzu Li")
                .build();
    }
}
