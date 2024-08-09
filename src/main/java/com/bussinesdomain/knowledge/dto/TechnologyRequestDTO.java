package com.bussinesdomain.knowledge.dto;

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
public class TechnologyRequestDTO {
    
    @EqualsAndHashCode.Include
    private Long idTechnology;

    @NotNull(message = ValidationMessage.CAN_T_BE_NULL)
    private Long idCollaboratorCampaign;

    @NotNull(message = ValidationMessage.CAN_T_BE_NULL)
    private Long idCatalog;
    
    @NotNull(message = ValidationMessage.CAN_T_BE_NULL)
    private Double yearExpert;
    
    @NotNull(message = ValidationMessage.CAN_T_BE_NULL)
    private Long rank;
}
