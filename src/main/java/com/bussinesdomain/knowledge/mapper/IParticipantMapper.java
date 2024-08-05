package com.bussinesdomain.knowledge.mapper;

import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.bussinesdomain.knowledge.dto.ParticipantRequestDTO;
import com.bussinesdomain.knowledge.dto.ParticipantResponseDTO;
import com.bussinesdomain.knowledge.models.ParticipantEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IParticipantMapper {

	ParticipantResponseDTO toGetDTO(ParticipantEntity entity);

    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @InheritInverseConfiguration
    ParticipantEntity toEntity(ParticipantRequestDTO dto);

    List<ParticipantResponseDTO> listEntityToDTO(List<ParticipantEntity> lst);
}
