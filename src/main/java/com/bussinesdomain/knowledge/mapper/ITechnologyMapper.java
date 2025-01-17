package com.bussinesdomain.knowledge.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.bussinesdomain.knowledge.dto.TechnologyRequestDTO;
import com.bussinesdomain.knowledge.dto.TechnologyResponseDTO;
import com.bussinesdomain.knowledge.models.TechnologyEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ITechnologyMapper {

    @Mapping(target  = "idCollaboratorCampaign", source = "collaboratorCampaign.idCollaboratorCampaign")
    TechnologyResponseDTO toGetResponseDTO(TechnologyEntity entity);

    @Mapping(target  = "collaboratorCampaign.idCollaboratorCampaign", source = "idCollaboratorCampaign")
    @InheritInverseConfiguration
    TechnologyEntity toEntity(TechnologyRequestDTO dto);

    List<TechnologyResponseDTO> listEntityToDTO(List<TechnologyEntity> list);
}
