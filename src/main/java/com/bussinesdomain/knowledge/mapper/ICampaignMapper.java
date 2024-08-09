package com.bussinesdomain.knowledge.mapper;

import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.bussinesdomain.knowledge.dto.CampaignRequestDTO;
import com.bussinesdomain.knowledge.dto.CampaignResponseDTO;
import com.bussinesdomain.knowledge.models.CampaignEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ICampaignMapper {

	CampaignResponseDTO toGetDTO(CampaignEntity entity);

    @Mapping(target = "createdAt",ignore = true)
    @InheritInverseConfiguration
    CampaignEntity toEntity(CampaignRequestDTO dto);

    List<CampaignResponseDTO> listEntityToDTO(List<CampaignEntity> lst);
}
