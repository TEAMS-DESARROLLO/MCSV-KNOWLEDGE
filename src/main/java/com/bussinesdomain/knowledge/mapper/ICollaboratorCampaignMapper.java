package com.bussinesdomain.knowledge.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.bussinesdomain.knowledge.dto.CollaboratorCampaignRequestDTO;
import com.bussinesdomain.knowledge.dto.CollaboratorCampaignResponseDTO;
import com.bussinesdomain.knowledge.models.CollaboratorCampaignEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ICollaboratorCampaignMapper {

    @Mapping(target  = "idParticipant", source = "participant.idParticipant")
	CollaboratorCampaignResponseDTO toGetResponseDTO(CollaboratorCampaignEntity entity);

    @Mapping(target = "createdAt",ignore = true)
    
    @Mapping(target  = "participant.idParticipant", source = "idParticipant")
    @InheritInverseConfiguration
    CollaboratorCampaignEntity toEntity(CollaboratorCampaignRequestDTO dto);

    List<CollaboratorCampaignResponseDTO> listEntity(List<CollaboratorCampaignEntity> list);

}
