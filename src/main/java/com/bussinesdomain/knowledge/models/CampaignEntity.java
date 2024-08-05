package com.bussinesdomain.knowledge.models;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "campaign")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CampaignEntity {
 
    @Id
    @SequenceGenerator(name = "seqCampaign", sequenceName = "seqCampaign", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seqCampaign")
    @Column(name = "id_campaign")
    private Long idCampaign;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "campaign_description", nullable = false)
    private String description;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_start", nullable = false)
    private Date dateStart;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_end", nullable = false)
    private Date dateEnd;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = true)
    private LocalDateTime createdAt;

    @Column(name = "id_community", nullable = true)
    private Long idCommunity;

    @PrePersist
    public void prePersisten() {
        this.createdAt = LocalDateTime.now();
    }

}
