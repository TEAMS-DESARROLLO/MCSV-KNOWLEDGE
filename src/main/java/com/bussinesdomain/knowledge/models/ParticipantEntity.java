package com.bussinesdomain.knowledge.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "participant")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ParticipantEntity implements Serializable {

	private static final long serialVersionUID = -5786663920489165356L;

	@Id
    @GeneratedValue(generator = "seqParticipant", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "seqParticipant", sequenceName = "participant_seq", allocationSize = 1)
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
    private Long collaborator;

    @PrePersist
    public void prePersisten(){
        this.createdAt=LocalDateTime.now();
    }
    
    @PreUpdate
    public void preModify(){
        this.updatedAt = LocalDateTime.now();
    }
    
}
