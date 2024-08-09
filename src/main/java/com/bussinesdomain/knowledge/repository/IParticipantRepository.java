package com.bussinesdomain.knowledge.repository;

import org.springframework.stereotype.Repository;

import com.bussinesdomain.knowledge.models.ParticipantEntity;

@Repository
public interface IParticipantRepository extends IGenericRepository<ParticipantEntity,Long>{

}
