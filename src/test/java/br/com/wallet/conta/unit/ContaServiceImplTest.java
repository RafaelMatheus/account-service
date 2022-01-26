package br.com.wallet.conta.unit;

import br.com.wallet.conta.entity.conta.ContaEntity;
import br.com.wallet.conta.exception.ObjetoConsultaNotFoundException;
import br.com.wallet.conta.exception.SaldoInsufienteException;
import br.com.wallet.conta.mapper.ContaMapper;
import br.com.wallet.conta.repository.AgenciaRepository;
import br.com.wallet.conta.repository.ContaRepository;
import br.com.wallet.conta.service.impl.ContaServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ContaServiceImplTest {
    @InjectMocks
    private ContaServiceImpl contaService;
    @Mock
    private ContaMapper contaMapper;
    @Mock
    private ContaRepository contaRepository;
    @Mock
    private AgenciaRepository agenciaRepository;

    @Test
    @DisplayName("Deverá lançar saldoInsuficienteException para tentativa de saque com saldo insuficiente.")
    void deveraLancarSaldoInsuficieenteExceptionParaSaque() {
        ContaEntity conta = ContaEntity.builder()
                .numeroConta("123")
                .saldo(BigDecimal.ZERO)
                .build();

        when(this.contaRepository.findByNumeroConta(anyString())).thenReturn(Optional.of(conta));

        assertThrows(SaldoInsufienteException.class, () ->
                this.contaService.realizarSaque("123", BigDecimal.valueOf(10)));
    }

    @Test
    @DisplayName("Deverá realizar saque com sucesso.")
    void deveraRealizarUmSaqueSucesso() {
        ContaEntity conta = ContaEntity.builder()
                .numeroConta("123")
                .saldo(BigDecimal.valueOf(10))
                .build();

        when(this.contaRepository.findByNumeroConta(anyString())).thenReturn(Optional.of(conta));

        assertDoesNotThrow(() -> this.contaService.realizarSaque("123", BigDecimal.valueOf(10)));
        verify(this.contaRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Deverá lançar saldoInsuficienteException para tentativa de transferencia com saldo insuficiente.")
    void deveraLancarSaldoInsuficieenteExceptionParaTransferencia() {
        ContaEntity contaOrigem = ContaEntity.builder()
                .numeroConta("123")
                .saldo(BigDecimal.ZERO)
                .build();

        when(this.contaRepository.findByNumeroConta(any())).thenReturn(Optional.of(contaOrigem));

        assertThrows(SaldoInsufienteException.class, () ->
                this.contaService.realizarTransferencia("123", "321",
                        BigDecimal.valueOf(10)));
    }

    @Test
    @DisplayName("Deverá realizar transferencia sucesso.")
    void deveraRealizarTransferenciaSucesso() {
        ContaEntity contaOrigem = ContaEntity.builder()
                .numeroConta("123")
                .saldo(BigDecimal.valueOf(10))
                .build();

        when(this.contaRepository.findByNumeroConta(any())).thenReturn(Optional.of(contaOrigem));

        assertDoesNotThrow(() ->
                this.contaService.realizarTransferencia("123", "321",
                        BigDecimal.valueOf(10)));
        verify(this.contaRepository, times(2)).save(any());

    }

    @Test
    @DisplayName("Deverá lançar ObjetoConsultaNotFoundException para tentativa de deposito para uma conta inexistente.")
    void deveraLancarObjetoConsultaNotFoundExceptionParaDepositoComContaDestinoInexistente() {

        when(this.contaRepository.findByNumeroConta(any())).thenReturn(Optional.empty());

        assertThrows(ObjetoConsultaNotFoundException.class, () ->
                this.contaService.realizarDeposito("321",
                        BigDecimal.valueOf(10)));
    }
}
