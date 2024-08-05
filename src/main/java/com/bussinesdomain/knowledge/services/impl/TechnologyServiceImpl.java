package com.bussinesdomain.knowledge.services.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.bussinesdomain.knowledge.exception.ModelNotFoundException;
import com.bussinesdomain.knowledge.models.TechnologyEntity;
import com.bussinesdomain.knowledge.repository.IGenericRepository;
import com.bussinesdomain.knowledge.services.ITechnologyService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TechnologyServiceImpl extends CRUDImpl<TechnologyEntity, Long> implements ITechnologyService {

    private final IGenericRepository<TechnologyEntity, Long> repository;

    @Override
    protected IGenericRepository<TechnologyEntity, Long> getRepo() {
        return repository;
    }

    @Override
    public TechnologyEntity update(TechnologyEntity entity, Long id) {
        
        TechnologyEntity original = this.readById(id).stream().findFirst().orElse(null);

        if (original == null) {
			throw new ModelNotFoundException("The following ID does not exists : " + id);
		}
		String[] ignoreProperties = new String[] { "idTechnology" };
		BeanUtils.copyProperties(entity, original, ignoreProperties);
		return super.update(original, id);
    }

    
 
}
