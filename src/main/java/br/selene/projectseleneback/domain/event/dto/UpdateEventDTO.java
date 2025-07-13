package br.selene.projectseleneback.domain.event.dto;

import java.time.LocalDateTime;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Size;

public record UpdateEventDTO(
		@Size(max = 255, message = "O título do evento deve ter no máximo 255 caracteres.")
		String title, 
		
		String description, 
		
        @Future(message = "A data do evento deve ser futura.")
		LocalDateTime date,
		
		@Valid
		AddressDTO address
) {}
