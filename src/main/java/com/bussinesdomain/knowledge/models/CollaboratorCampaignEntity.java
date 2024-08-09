package com.bussinesdomain.knowledge.models;


import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "collaborator_campaign")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CollaboratorCampaignEntity implements Serializable{

	private static final long serialVersionUID = 3993188445268768079L;

    @Id
    @SequenceGenerator(name = "seqCollaboratorCampaign", sequenceName = "seqCollaboratorCampaign", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seqCollaboratorCampaign")
    @Column(name = "id_collaborator_campaign")
    private Long idCollaboratorCampaign;

    @Column(name="id_community",nullable = false)
    private Long idCommunity;

    @Column(name="id_subpractica",nullable = false)
    private Long idSubpractica;
    
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at",nullable = true)
    private LocalDateTime createdAt;
      
    @ManyToOne(optional = true, fetch= FetchType.EAGER)
    @JoinColumn(name="id_participant",referencedColumnName="id_participant")
    private ParticipantEntity participant;

    @PrePersist
    public void prePersisten(){
        this.createdAt=LocalDateTime.now();
    }

}
