package com.bussinesdomain.knowledge.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "campaign")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CampaignEntity {

    @Id
    @GeneratedValue(generator = "seqCampaign", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "seqCampaign", sequenceName = "campaign_seq", allocationSize = 1)
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
