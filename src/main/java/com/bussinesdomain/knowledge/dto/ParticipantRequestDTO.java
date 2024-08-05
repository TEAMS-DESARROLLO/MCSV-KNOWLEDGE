package com.bussinesdomain.knowledge.dto;

import java.time.LocalDateTime;

import com.bussinesdomain.knowledge.constants.ValidationMessage;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ParticipantRequestDTO {

    @EqualsAndHashCode.Include
    private Long idParticipant;

    
	@NotEmpty(message = ValidationMessage.NONEMPTY_STRING)
    @NotBlank(message = ValidationMessage.NOWHITESPACES_STRING)
    private String email;
    
	@NotEmpty(message = ValidationMessage.NONEMPTY_STRING)
    @NotBlank(message = ValidationMessage.NOWHITESPACES_STRING)
    private String code;
    
	@NotEmpty(message = ValidationMessage.NONEMPTY_STRING)
    @NotBlank(message = ValidationMessage.NOWHITESPACES_STRING)
    private String responseStatus;

    
    @NotNull(message = ValidationMessage.CAN_T_BE_NULL)
    private Long idCampaign;
    
    @NotNull(message = ValidationMessage.CAN_T_BE_NULL)
    private Long idCollaborator;
}
