package com.bussinesdomain.knowledge.models;


import java.io.Serializable;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "collaborator")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CollaboratorEntity implements Serializable{

	private static final long serialVersionUID = 3993188445268768079L;

	@Id
    @Column(name="id_collaborator")
    private Long idCollaborator;

    @Column(name="id_community",nullable = false)
    private Long idCommunity;

    @Column(name="id_subpractica",nullable = false)
    private Long idSubpractica;
    
    @Column(name="id_technology",nullable = false)
    private Long idTechnology;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at",nullable = true)
    private LocalDateTime createdAt;
    
    @ManyToOne(optional = true,fetch= FetchType.EAGER)
    @JoinColumn(name="id_participant",referencedColumnName="id_participant")
    private ParticipantEntity participant;

    @PrePersist
    public void prePersisten(){
        this.createdAt=LocalDateTime.now();
    }

}
