package br.com.wallet.conta.integration;

import br.com.wallet.conta.ContaApplication;
import br.com.wallet.conta.configuration.ObjectMapperConfiguration;
import br.com.wallet.conta.entity.enums.TipoTransacao;
import br.com.wallet.conta.entity.transacao.ContaTransacaoRequest;
import br.com.wallet.conta.entity.transacao.TransacaoRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static br.com.wallet.conta.utils.TestUtils.toJson;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ContaApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Import({ObjectMapperConfiguration.class})
public class TransacaoControllerImplTest {
    private static final String ENDPOINT = "/transacoes";
    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest
    @MethodSource("jsonInvalido")
    @DisplayName("Deve retornar status HTTP 400 quando enviar uma requisição POST com erro no BODY")
    void deveRetornar400BodyIncorreto(String json) throws Exception {

        this.mockMvc.perform(post(ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)).andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Erro de validação"))
                .andExpect(jsonPath("$.path").value("/transacoes"))
                .andExpect(jsonPath("$.fieldMessages").isArray());
    }


    private static Stream<String> jsonInvalido() throws JsonProcessingException {
        ContaTransacaoRequest contaRequest = ContaTransacaoRequest.builder()
                .numeroConta("321")
                .numeroAgencia("2321")
                .build();

        return Stream.of(
                toJson(new TransacaoRequest(contaRequest, TipoTransacao.SAQUE, BigDecimal.ZERO, contaRequest)),
                toJson(new TransacaoRequest(null, TipoTransacao.SAQUE, BigDecimal.ZERO, contaRequest)),
                toJson(new TransacaoRequest(null, TipoTransacao.SAQUE, BigDecimal.ZERO, contaRequest)),
                toJson(new TransacaoRequest(contaRequest, null, BigDecimal.ZERO, contaRequest)),
                toJson(new TransacaoRequest(contaRequest, null, BigDecimal.ZERO, contaRequest)),
                toJson(new TransacaoRequest(contaRequest, TipoTransacao.SAQUE, null, contaRequest))

        );
    }
}
