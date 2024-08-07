package com.bussinesdomain.knowledge.dto;

import java.time.LocalDateTime;
import java.util.Date;

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
public class CampaignResponseDTO {

	@EqualsAndHashCode.Include
    @NotNull(message = ValidationMessage.CAN_T_BE_NULL)
    private Long idCampaign;
	
    private String name;
	
    private String description;
	
    private Date dateStart;
	
    private Date dateEnd;
	
    
    private Long idCommunity;
    private String communityDescription;
}
