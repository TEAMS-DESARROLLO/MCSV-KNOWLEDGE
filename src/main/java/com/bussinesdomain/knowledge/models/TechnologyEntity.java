package com.bussinesdomain.knowledge.models;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
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
    @SequenceGenerator(name = "seqTechnology", sequenceName = "seqTechnology", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seqTechnology")
    @Column(name = "id_technology")
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
