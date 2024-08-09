package com.bussinesdomain.knowledge.repository;

import org.springframework.stereotype.Repository;

import com.bussinesdomain.knowledge.models.CampaignEntity;

@Repository
public interface ICampaignRepository extends IGenericRepository<CampaignEntity, Long>{

}
