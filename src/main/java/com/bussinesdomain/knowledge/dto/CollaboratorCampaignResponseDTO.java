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
public class CollaboratorCampaignResponseDTO {
    
    @EqualsAndHashCode.Include
    @NotNull(message = ValidationMessage.CAN_T_BE_NULL)
    private Long idCollaboratorCampaign;

    private Long idCommunity;

    private Long idSubpractica;
    
    private LocalDateTime createdAt;
    
    private Long idParticipant;
}
