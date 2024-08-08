package com.bussinesdomain.knowledge.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ParticipantResponseDTO {

    @EqualsAndHashCode.Include
    private Long idParticipant;

    
    private String email;
    
    private String code;
    
    private String responseStatus;

    
    private Long idCampaign;
    private String campaignDescription;
    
    private Long idCollaborator;

    
    private String namesCollaborator;
    private String lastnameCollaborator;


    
    private Long idLeader;
    private String namesLeader;

    
    private Long idRegion;

    private String descriptionRegion;

    private Long idCommunity;

    private String descriptionCommunity;
    private Long idFunctionalLeader;

    private String namesFunctionalLeader;
}
