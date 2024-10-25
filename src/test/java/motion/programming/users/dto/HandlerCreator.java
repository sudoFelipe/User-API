package motion.programming.users.dto;

public class HandlerCreator {

    public static StateDTO createState() {
        return StateDTO.builder()
                .id(1)
                .name("State")
                .abbreviation("ST")
                .build();
    }

    public static CityDTO createCity() {
        return CityDTO.builder()
                .id(1)
                .name("City")
                .build();
    }
}
