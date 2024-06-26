package motion.programming.users.utility;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static motion.programming.users.utility.Format.formatAndMaskCpf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FormatTest {

    @ParameterizedTest
    @ValueSource(strings = "12345678901")
    void formatCpfOnlyTest(String cpf) {
        final var result = formatAndMaskCpf(cpf, FALSE);
        final var expected = "123.456.789-01";
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = "12345678901")
    @DisplayName("Formata ")
    void formatAndMaskCpfTest(String cpf) {
        final var result = formatAndMaskCpf(cpf, TRUE);
        final var expected = "***.456.789-**";
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(expected);
    }
}