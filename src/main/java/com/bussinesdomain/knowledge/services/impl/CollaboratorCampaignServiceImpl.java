package com.bussinesdomain.knowledge.services.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.bussinesdomain.knowledge.exception.ModelNotFoundException;
import com.bussinesdomain.knowledge.models.CollaboratorCampaignEntity;
import com.bussinesdomain.knowledge.repository.IGenericRepository;
import com.bussinesdomain.knowledge.services.ICollaboratorCampaignService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CollaboratorCampaignServiceImpl extends CRUDImpl<CollaboratorCampaignEntity, Long>
        implements ICollaboratorCampaignService {

    private final IGenericRepository<CollaboratorCampaignEntity, Long> repository;
    @Override
    protected IGenericRepository<CollaboratorCampaignEntity, Long> getRepo() {
        return repository;
    }

    @Override
    public CollaboratorCampaignEntity update(CollaboratorCampaignEntity entity, Long id) {
         
        CollaboratorCampaignEntity original = this.readById(id).stream().findFirst().orElse(null);

        if (original == null) {
			throw new ModelNotFoundException("The following ID does not exists : " + id);
		}
		String[] ignoreProperties = new String[] { "idCollaboratorCampaign" };
		BeanUtils.copyProperties(entity, original, ignoreProperties);
		return super.update(original, id);
    }

}
