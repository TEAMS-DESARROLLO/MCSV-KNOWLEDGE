package com.bussinesdomain.knowledge.client.master.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CommunityResponseDTO {
    @EqualsAndHashCode.Include
    private Long idCommunity;
    private String description;

    private Long idRegion;
    private String regionDescription;
}