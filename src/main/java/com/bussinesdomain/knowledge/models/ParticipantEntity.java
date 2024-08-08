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
import jakarta.persistence.PreUpdate;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "participant")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ParticipantEntity implements Serializable {

	private static final long serialVersionUID = -5786663920489165356L;

	@Id
    @SequenceGenerator(name = "seqParticipant", sequenceName = "seqParticipant", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seqParticipant")
    @Column(name = "id_participant")
    private Long idParticipant;

    @Column(name="email",nullable = false)
    private String email;
    
    @Column(name="code",nullable = false)
    private String code;
    
    @Column(name="status_response ", nullable=false,length = 1)
    private String responseStatus;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at",nullable = true)
    private LocalDateTime createdAt;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_at",nullable = true)
    private LocalDateTime updatedAt;

    
    @ManyToOne(optional = true,fetch= FetchType.EAGER)
    @JoinColumn(name="id_campaign",referencedColumnName="id_campaign")
    private CampaignEntity campaign;
    
    @Column(name="id_collaborator")
    private Long idCollaborator;
    @Column(name="names_collaborator")
    private String namesCollaborator;
    @Column(name="lastname_collaborator")
    private String lastnameCollaborator;

    
    @Column(name="id_leader")
    private Long idLeader;
    
    @Column(name="names_leader")
    private String namesLeader;

    @Column(name="id_region")
    private Long idRegion;

    @Column(name="description_region")
    private String descriptionRegion;

    @Column(name="id_community")
    private Long idCommunity;

    @Column(name="description_community")
    private String descriptionCommunity;

    @Column(name="id_functional_leader")
    private Long idFunctionalLeader;

    @Column(name="names_functional_leader")
    private String namesFunctionalLeader;



    @PrePersist
    public void prePersisten(){
        this.createdAt=LocalDateTime.now();
    }
    
    @PreUpdate
    public void preModify(){
        this.updatedAt = LocalDateTime.now();
    }
    
}
