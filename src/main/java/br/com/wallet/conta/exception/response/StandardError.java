package br.com.wallet.conta.exception.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StandardError {
    private Long timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
}
