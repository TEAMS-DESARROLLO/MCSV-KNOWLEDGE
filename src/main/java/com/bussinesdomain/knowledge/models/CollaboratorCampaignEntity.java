package com.bussinesdomain.knowledge.models;


import java.io.Serializable;
import java.time.LocalDateTime;
import jakarta.persistence.*;
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
    @GeneratedValue(generator = "seqCollaboratorCampaign", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "seqCollaboratorCampaign", sequenceName = "collaborator_campaign_seq", allocationSize = 1)
    @Column(name = "id_collaborator_campaign")
    private Long idCollaboratorCampaign;

    @Column(name="id_community",nullable = false)
    private Long idCommunity;

    @Column(name="id_subpractica",nullable = false)
    private Long idSubpractica;
    
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at",nullable = true)
    private LocalDateTime createdAt;
    
    @ManyToOne(optional = false,fetch= FetchType.EAGER)
    @JoinColumn(name="id_participant",referencedColumnName="id_participant")
    private ParticipantEntity participant;

    @PrePersist
    public void prePersisten(){
        this.createdAt=LocalDateTime.now();
    }

}
