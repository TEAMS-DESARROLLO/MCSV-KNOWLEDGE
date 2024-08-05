package com.bussinesdomain.knowledge.mapper;

import java.util.List;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.bussinesdomain.knowledge.dto.CollaboratorCampaignRequestDTO;
import com.bussinesdomain.knowledge.dto.CollaboratorCampaignResponseDTO;
import com.bussinesdomain.knowledge.models.CollaboratorCampaignEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ICollaboratorCampaignMapper {

    @Mapping(target  = "idParticipant", ignore = true)
    CollaboratorCampaignRequestDTO toGetDTO(CollaboratorCampaignEntity entity);

    @Mapping(target = "idParticipant", ignore = true)
    CollaboratorCampaignResponseDTO toGetResponseDTO(CollaboratorCampaignEntity entity);

    @Mapping(target = "participant", ignore = true)
    @InheritConfiguration
    CollaboratorCampaignEntity toEntity(CollaboratorCampaignRequestDTO dto);

    List<CollaboratorCampaignResponseDTO> listEntity(List<CollaboratorCampaignEntity> list);

}
