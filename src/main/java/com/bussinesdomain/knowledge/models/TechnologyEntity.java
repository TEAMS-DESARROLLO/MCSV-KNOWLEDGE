package com.bussinesdomain.knowledge.models;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "technology")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TechnologyEntity implements Serializable{

	private static final long serialVersionUID = 798618670059069385L;

	@Id
    @GeneratedValue(generator = "seqTechnology", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "seqCollaboratorCampaign", sequenceName = "technology_seq", allocationSize = 1)
    @Column(name="id_technology")
    private Long idTechnology;

    
    @ManyToOne(optional = false,fetch= FetchType.EAGER)
    @JoinColumn(name="id_collaborator_campaign",referencedColumnName="id_collaborator_campaign")
    private CollaboratorCampaignEntity collaboratorCampaign;

    @Column(name="id_catalog",nullable = false)
    private Long idCatalog;
    
    @Column(name="year_expert",nullable = false)
    private Double yearExpert;
    
    @Column(name="rank",nullable = false)
    private Long rank;
   
}
