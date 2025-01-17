package com.bussinesdomain.knowledge.dto;

import java.time.LocalDateTime;
import java.util.Date;

import com.bussinesdomain.knowledge.constants.ValidationMessage;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CampaignRequestDTO {
	
	@EqualsAndHashCode.Include
    private Long idCampaign;
	
	@NotEmpty(message = ValidationMessage.NONEMPTY_STRING)
    @NotBlank(message = ValidationMessage.NOWHITESPACES_STRING)
    private String name;
	
	@NotEmpty(message = ValidationMessage.NONEMPTY_STRING)
    @NotBlank(message = ValidationMessage.NOWHITESPACES_STRING)
    private String description;
	
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @NotNull(message = ValidationMessage.CAN_T_BE_NULL)
    private Date dateStart;
	
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @NotNull(message = ValidationMessage.CAN_T_BE_NULL)
    private Date dateEnd;
    
    @NotNull(message = ValidationMessage.CAN_T_BE_NULL)
    private Long idCommunity;
}
