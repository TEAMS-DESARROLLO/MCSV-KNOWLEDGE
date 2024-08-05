package com.bussinesdomain.knowledge.dto;

import java.time.LocalDateTime;

import com.bussinesdomain.knowledge.constants.ValidationMessage;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CollaboratorCampaignRequestDTO {
    
    @EqualsAndHashCode.Include
    private Long idCollaboratorCampaign;

    @NotNull(message = ValidationMessage.CAN_T_BE_NULL)
    private Long idCommunity;

    @NotNull(message = ValidationMessage.CAN_T_BE_NULL)
    private Long idSubpractica;
    
    @NotNull(message = ValidationMessage.CAN_T_BE_NULL)
    private LocalDateTime createdAt;
    
    @NotNull(message = ValidationMessage.CAN_T_BE_NULL)
    private Long idParticipant;
}
