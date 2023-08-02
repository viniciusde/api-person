package br.com.denarde.exceptions.handler;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ExceptionResponse {
    private String code;
    private String message;
    private List<String> details;
}
