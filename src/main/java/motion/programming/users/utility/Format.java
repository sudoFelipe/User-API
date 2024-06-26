package motion.programming.users.utility;

import lombok.AllArgsConstructor;
import lombok.experimental.UtilityClass;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class Format {

    private static final String REGEX_DIGIT = "\\D";
    private static final String REGEX_PATTERN = "(\\d{3})(\\d{3})(\\d{3})(\\d{2})";
    private static final String MATCHER_DEFAULT = "$1.$2.$3-$4";
    private static final String MATCHER_MASK = "***.$2.$3-**";

    public static String formatAndMaskCpf(String cpf, boolean mask) {

        // Remove todos os caracteres não numéricos
        cpf = cpf.replaceAll(REGEX_DIGIT, "");

        // Verifica se o CPF possui 11 dígitos
        if (cpf.length() != 11) {
            throw new IllegalArgumentException("CPF deve conter 11 dígitos.");
        }

        // Define a expressão regular para o formato de CPF
        Pattern pattern = Pattern.compile(REGEX_PATTERN);
        Matcher matcher = pattern.matcher(cpf);

        // Formata o CPF utilizando o padrão definido
        if (matcher.matches()) {
            return matcher.replaceAll(mask ? MATCHER_MASK : MATCHER_DEFAULT);
        }

        throw new IllegalArgumentException("CPF inválido.");
    }
}
