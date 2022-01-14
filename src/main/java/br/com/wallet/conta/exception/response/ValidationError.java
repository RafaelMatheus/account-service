package br.com.wallet.conta.exception.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@SuperBuilder
public class ValidationError extends StandardError {
    private List<FieldMessage> fieldMessages;
}