package com.bussinesdomain.knowledge.services.impl;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bussinesdomain.knowledge.commons.Filter;
import com.bussinesdomain.knowledge.commons.IPaginationCommons;
import com.bussinesdomain.knowledge.commons.PaginationModel;
import com.bussinesdomain.knowledge.commons.SortModel;
import com.bussinesdomain.knowledge.dto.CampaignResponseDTO;
import com.bussinesdomain.knowledge.exception.ServiceException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CampaignPaginationServiceImpl implements IPaginationCommons<CampaignResponseDTO>{

    private final EntityManager entityManager;
	
	@Override
	public Page<CampaignResponseDTO> pagination(PaginationModel pagination) {
		try {

			String sqlCount = "SELECT count(r) " + getFrom().toString() + getFilters(pagination.getFilters()).toString();
			String sqlSelect = getSelect().toString() + getFrom().toString() + getFilters(pagination.getFilters()).toString() + getOrder(pagination.getSorts());

			Query queryCount = entityManager.createQuery(sqlCount);
			Query querySelect = entityManager.createQuery(sqlSelect);

			this.setParams(pagination.getFilters(), queryCount);
			this.setParams(pagination.getFilters(), querySelect);

			Long total = (long) queryCount.getSingleResult();

			querySelect.setFirstResult((pagination.getPageNumber()) * pagination.getRowsPerPage());
			querySelect.setMaxResults(pagination.getRowsPerPage());

			@SuppressWarnings("unchecked")
			List<CampaignResponseDTO> lista = querySelect.getResultList();

			PageRequest pageable = PageRequest.of(pagination.getPageNumber(), pagination.getRowsPerPage());

			Page<CampaignResponseDTO> page = new PageImpl<CampaignResponseDTO>(lista, pageable, total);

			return page;
		} catch (RuntimeException e) {
			throw new ServiceException("error when generating the pagination " + e.getMessage(), e.getCause());
		}
	}

	@Override
	public StringBuilder getSelect() {
		StringBuilder sql = new StringBuilder("SELECT new com.bussinesdomain.maestros.dto.CampaignResponseDTO("+
        "r.idCampaign,"+
        "r.name,"+
        "r.description,"+
        "r.dateStart,"+
        "r.dateEnd,"+
        "r.idCommunity"+
        ") ");
        return sql;
	}

	@Override
	public StringBuilder getFrom() {
		StringBuilder sql = new StringBuilder(" FROM CampaignEntity r  ");
        return sql;
	}

	@Override
	public StringBuilder getFilters(List<Filter> filters) {
		StringBuilder sql = new StringBuilder("where 1=1 ");

        for(Filter filtro:filters){
            if(filtro.getField().equals("idCampaign")){
                sql.append(" AND r.idCampaign = :idCampaign");
            }
            if(filtro.getField().equals("name")){
                sql.append(" AND r.name LIKE :name ");
            }
            if(filtro.getField().equals("description")){
                sql.append(" AND r.description LIKE :description ");
            }
            if(filtro.getField().equals("dateStart")){
                sql.append(" AND r.dateStart = :dateStart");
            }
            if(filtro.getField().equals("dateEnd")){
                sql.append(" AND r.dateEnd = :dateEnd");
            }
            if(filtro.getField().equals("idCommunity")){
                sql.append(" AND r.idCommunity = :idCommunity");
            }
        }

        return sql;
	}

	@Override
	public Query setParams(List<Filter> filters, Query query) {
		for(Filter filtro:filters){
            if(filtro.getField().equals("idCampaign")){
                query.setParameter("idCampaign",filtro.getValue() );
            }
            if(filtro.getField().equals("name")){
                query.setParameter("name","%"+filtro.getValue()+"%");
            }
            if(filtro.getField().equals("description")){
                query.setParameter("description","%"+filtro.getValue()+"%");
            }
            if(filtro.getField().equals("dateStart")){
                query.setParameter("dateStart",filtro.getValue() );
            }
            if(filtro.getField().equals("dateEnd")){
                query.setParameter("dateEnd",filtro.getValue() );
            }
            if(filtro.getField().equals("idCommunity")){
                query.setParameter("idCommunity",filtro.getValue() );
            }
        }
        return query;
	}

	@Override
	public StringBuilder getOrder(List<SortModel> sorts) {
		boolean flagMore = false;
        StringBuilder sql = new StringBuilder("");
        if(!sorts.isEmpty()){
            sql.append(" ORDER BY ");

            for(SortModel sort:sorts){
                if(sort.getColName().equals("idCampaign")){
                    if(flagMore)
                        sql.append(", ");

                    sql.append( " r.idCampaign " + sort.getSort() );
                    flagMore = true;
                }

                if(sort.getColName().equals("description")){
                    if(flagMore)
                        sql.append(", ");
                    sql.append( " r.description " + sort.getSort() );
                    flagMore = true;
                }

                if(sort.getColName().equals("name")){
                    if(flagMore)
                        sql.append(", ");
                    sql.append( " r.name " + sort.getSort() );
                    flagMore = true;
                }

                if(sort.getColName().equals("dateStart")){
                    if(flagMore)
                        sql.append(", ");

                    sql.append( " r.dateStart " + sort.getSort() );
                    flagMore = true;
                }

                if(sort.getColName().equals("dateEnd")){
                    if(flagMore)
                        sql.append(", ");

                    sql.append( " r.dateEnd " + sort.getSort() );
                    flagMore = true;
                }

                if(sort.getColName().equals("idCommunity")){
                    if(flagMore)
                        sql.append(", ");

                    sql.append( " r.idCommunity " + sort.getSort() );
                    flagMore = true;
                }
           }
        }
         return sql;
	}

}
