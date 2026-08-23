package br.edu.catolica.customer_ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

@Schema(name = "CustomerDTO", description = "Representaçao dos dados do Customer")
public record CustomerDTO(

      @Schema(description = "Nome do cliente", minLength = 2)
      @NotBlank(message = "O campo nome é obrigatório")
      @Size(min = 2, message = "O nome não deve ser menor que 2 caracteres")
      String name,

      @Schema(description = "Email do cliente", example = "cliente@email.com")
      @NotBlank(message = "O campo email é obrigatório")
      @Email(message = "Email invaĺido")
      String email,

      @Schema(description = "CPF do cliente (somente numeros)", example = "12345678952")
      @NotBlank(message = "O campo CPF é obrigatório")
      @CPF(message = "CPF inválido")
      String cpf,

      @Schema(description = "Telefone do cliente", example = "83999995412")
      @NotBlank(message = "O campo telefone é obrigatório")
      @Size(min = 11, max = 11, message = "O número do telefone deve conter 11 dígitos")
      String phoneNumber,
      AddressDTO address) {
}
