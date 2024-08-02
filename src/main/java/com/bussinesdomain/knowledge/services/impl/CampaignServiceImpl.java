package com.bussinesdomain.knowledge.services.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.bussinesdomain.knowledge.exception.ModelNotFoundException;
import com.bussinesdomain.knowledge.models.CampaignEntity;
import com.bussinesdomain.knowledge.repository.IGenericRepository;
import com.bussinesdomain.knowledge.services.ICampaignService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CampaignServiceImpl extends CRUDImpl<CampaignEntity, Long> implements ICampaignService {

	private final IGenericRepository<CampaignEntity, Long> repository;

	@Override
	protected IGenericRepository<CampaignEntity, Long> getRepo() {
		return repository;
	}

	@Override
	public CampaignEntity update(CampaignEntity entity, Long id) {
		CampaignEntity original = this.readById(id).stream().findFirst().orElse(null);
		if (original == null) {
			throw new ModelNotFoundException("The following ID does not exists : " + id);
		}
		String[] ignoreProperties = new String[] { "idCampaign" };
		BeanUtils.copyProperties(entity, original, ignoreProperties);
		return super.update(original, id);
	}

}
