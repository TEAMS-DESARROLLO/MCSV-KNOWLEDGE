package com.bussinesdomain.knowledge.dto;

import java.time.LocalDateTime;

import com.bussinesdomain.knowledge.constants.ValidationMessage;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Size(max=6, message="El tamaño máximo es 6")
    private String code;
    
	@NotEmpty(message = ValidationMessage.NONEMPTY_STRING)
    @NotBlank(message = ValidationMessage.NOWHITESPACES_STRING)
    private String responseStatus;

    
    @NotNull(message = ValidationMessage.CAN_T_BE_NULL)
    private Long idCampaign;
    
    @NotNull(message = ValidationMessage.CAN_T_BE_NULL)
    private Long idCollaborator;
    
	@NotEmpty(message = ValidationMessage.NONEMPTY_STRING)
    @NotBlank(message = ValidationMessage.NOWHITESPACES_STRING)
    private String namesCollaborator;
    
	@NotEmpty(message = ValidationMessage.NONEMPTY_STRING)
    @NotBlank(message = ValidationMessage.NOWHITESPACES_STRING)
    private String lastnameCollaborator;

    
    @NotNull(message = ValidationMessage.CAN_T_BE_NULL)
    private Long idLeader;
	@NotEmpty(message = ValidationMessage.NONEMPTY_STRING)
    @NotBlank(message = ValidationMessage.NOWHITESPACES_STRING)
    private String namesLeader;
    
    
    @NotNull(message = ValidationMessage.CAN_T_BE_NULL)
    private Long idRegion;
	@NotEmpty(message = ValidationMessage.NONEMPTY_STRING)
    @NotBlank(message = ValidationMessage.NOWHITESPACES_STRING)
    private String descriptionRegion;

    @NotNull(message = ValidationMessage.CAN_T_BE_NULL)
    private Long idCommunity;
	@NotEmpty(message = ValidationMessage.NONEMPTY_STRING)
    @NotBlank(message = ValidationMessage.NOWHITESPACES_STRING)
    private String descriptionCommunity;
    
    @NotNull(message = ValidationMessage.CAN_T_BE_NULL)
    private Long idFunctionalLeader;
	@NotEmpty(message = ValidationMessage.NONEMPTY_STRING)
    @NotBlank(message = ValidationMessage.NOWHITESPACES_STRING)
    private String namesFunctionalLeader;
}
