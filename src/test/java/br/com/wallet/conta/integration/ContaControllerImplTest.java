package br.com.wallet.conta.integration;

import br.com.wallet.conta.ContaApplication;
import br.com.wallet.conta.configuration.ObjectMapperConfiguration;
import br.com.wallet.conta.entity.agencia.AgenciaRequest;
import br.com.wallet.conta.entity.agencia.AgenciaResponse;
import br.com.wallet.conta.entity.conta.ContaRequest;
import br.com.wallet.conta.service.AgenciaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
public class ContaControllerImplTest {
    private static final String ENDPOINT = "/contas";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AgenciaService agenciaService;

    @ParameterizedTest
    @MethodSource("jsonInvalido")
    @DisplayName("Deve retornar status HTTP 400 quando enviar uma requisição POST com erro no BODY")
    void deveRetornar400BodyIncorreto(String json) throws Exception {
        AgenciaResponse agenciaResponse = this.agenciaService.criarAgencia(new AgenciaRequest("Agencia teste"));

        this.mockMvc.perform(post(ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)).andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Erro de validação"))
                .andExpect(jsonPath("$.path").value("/contas"))
                .andExpect(jsonPath("$.fieldMessages").isArray());
    }

    @Test
    @DisplayName("Deve retornar status HTTP 200 quando enviar uma requisição POST correta")
    void deveRetornar200BodyCorreto() throws Exception {

        AgenciaResponse agenciaResponse = this.agenciaService.criarAgencia(new AgenciaRequest("Agencia teste"));

        this.mockMvc.perform(post(ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(new ContaRequest(agenciaResponse.getNumeroAgencia(), "01234567890"))))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusConta").value("ATIVA"))
                .andExpect(jsonPath("$.numeroConta").exists())
                .andExpect(jsonPath("$.saldo").isNumber());

    }

    @Test
    @DisplayName("Deve retornar status HTTP 400 quando enviar uma requisição POST correta mas com AGENCIA inexistente")
    void deveRetornar400AgenciaInexistente() throws Exception {

        this.mockMvc.perform(post(ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(new ContaRequest("321", "01234567890"))))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Objeto não encontrado."))
                .andExpect(jsonPath("$.path").value("/contas"));

    }

    private static Stream<String> jsonInvalido() throws JsonProcessingException {
        return Stream.of(
                toJson(new ContaRequest("", "")),
                toJson(new ContaRequest(null, null)),
                toJson(new ContaRequest("", null)),
                toJson(new ContaRequest(null, "")),
                toJson(new ContaRequest("asd", "ads"))
        );
    }
}
