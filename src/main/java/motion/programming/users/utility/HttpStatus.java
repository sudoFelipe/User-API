package motion.programming.users.utility;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class HttpStatus {

    public static final String CREATED = "Created";
    public static final String SUCCESS = "Success";
    public static final String BAD_REQUEST = "Bad Request";
    public static final String NOT_FOUND = "Not Found";
    public static final String INTERNAL_SERVER_ERROR = "Internal Server Error";

    public static final String CREATED_CODE = "201";
    public static final String SUCCESS_CODE = "200";
    public static final String BAD_REQUEST_CODE = "400";
    public static final String NOT_FOUND_CODE = "404";
    public static final String INTERNAL_SERVER_ERROR_CODE = "500";
}
