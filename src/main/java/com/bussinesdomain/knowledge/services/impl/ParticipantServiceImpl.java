package com.bussinesdomain.knowledge.services.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.bussinesdomain.knowledge.exception.ModelNotFoundException;
import com.bussinesdomain.knowledge.models.ParticipantEntity;
import com.bussinesdomain.knowledge.repository.IGenericRepository;
import com.bussinesdomain.knowledge.services.IParticipantService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ParticipantServiceImpl extends CRUDImpl<ParticipantEntity, Long> implements IParticipantService {
    
	private final IGenericRepository<ParticipantEntity, Long> repository;
    @Override
    protected IGenericRepository<ParticipantEntity, Long> getRepo() {
        return repository;
    }

    @Override
	public ParticipantEntity update(ParticipantEntity entity, Long id) {
		ParticipantEntity original = this.readById(id).stream().findFirst().orElse(null);
		if (original == null) {
			throw new ModelNotFoundException("The following ID does not exists : " + id);
		}
		String[] ignoreProperties = new String[] { "idParticipant" };
		BeanUtils.copyProperties(entity, original, ignoreProperties);
		return super.update(original, id);
	}
}
