package com.bussinesdomain.knowledge.services.impl;

import java.util.List;

import org.hibernate.service.spi.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bussinesdomain.knowledge.commons.Filter;
import com.bussinesdomain.knowledge.commons.IPaginationCommons;
import com.bussinesdomain.knowledge.commons.PaginationModel;
import com.bussinesdomain.knowledge.commons.SortModel;
import com.bussinesdomain.knowledge.dto.CollaboratorCampaignResponseDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CollaboratorCampaignPaginationService implements IPaginationCommons<CollaboratorCampaignResponseDTO> {

    private final EntityManager entityManager;

    @Override
    public Page<CollaboratorCampaignResponseDTO> pagination(PaginationModel pagination) {

        try {
            String sqlCount = "SELECT count(a) " + getFrom().toString()
                    + getFilters(pagination.getFilters()).toString();
            String sqlSelect = getSelect().toString() + getFrom().toString()
                    + getFilters(pagination.getFilters()).toString() + getOrder(pagination.getSorts());
            Query queryCount = entityManager.createQuery(sqlCount);
            Query querySelect = entityManager.createQuery(sqlSelect);

            this.setParams(pagination.getFilters(), queryCount);
            this.setParams(pagination.getFilters(), querySelect);

            Long total = (long) queryCount.getSingleResult();

            querySelect.setFirstResult((pagination.getPageNumber()) * pagination.getRowsPerPage());
            querySelect.setMaxResults(pagination.getRowsPerPage());

            @SuppressWarnings("unchecked")
            List<CollaboratorCampaignResponseDTO> lista = querySelect.getResultList();

            PageRequest pageable = PageRequest.of(pagination.getPageNumber(), pagination.getRowsPerPage());

            Page<CollaboratorCampaignResponseDTO> page = new PageImpl<CollaboratorCampaignResponseDTO>(lista, pageable,
                    total);
            return page;
        } catch (RuntimeException e) {
            throw new ServiceException("error when generating the pagination " + e.getMessage());
        }
    }

    @Override
    public StringBuilder getSelect() {
        StringBuilder sql = new StringBuilder(
                "SELECT new com.bussinesdomain.knowledge.dto.CollaboratorCampaignResponseDTO" +
                        "(a.idCollaboratorCampaign,a.idCommunity,a.idSubpractica,a.idParticipant.idParticipant) ");
        return sql;
    }

    @Override
    public StringBuilder getFrom() {
        StringBuilder sql = new StringBuilder(" FROM CollaboratorCampaignEntity a ");
        return sql;
    }

    @Override
    public StringBuilder getFilters(List<Filter> filters) {
        StringBuilder sql = new StringBuilder("where 1=1 ");

        for (Filter filtro : filters) {
            if (filtro.getField().equals("idCollaboratorCampaign")) {
                sql.append(" AND a.idCollaboratorCampaign = :idCollaboratorCampaign");
            }
            if (filtro.getField().equals("idCommunity")) {
                sql.append(" AND a.idCommunity = :idCommunity ");
            }
        }

        return sql;

    }

    @Override
    public Query setParams(List<Filter> filters, Query query) {
        for (Filter filtro : filters) {
            if (filtro.getField().equals("idCollaboratorCampaign")) {
                query.setParameter("idCollaboratorCampaign", filtro.getValue());
            }
            if (filtro.getField().equals("idCollaboratorCampaign")) {
                query.setParameter("idCommunity", filtro.getValue());
            }
        }
        return query;

    }
 
    @Override
    public StringBuilder getOrder(List<SortModel> sorts) {
        boolean flagMore = false;
        StringBuilder sql = new StringBuilder("");
        if (!sorts.isEmpty()) {
            sql.append(" ORDER BY ");

            for (SortModel sort : sorts) {
                if (sort.getColName().equals("idCollaboratorCampaign")) {
                    if (flagMore)
                        sql.append(", ");

                    sql.append(" idCollaboratorCampaign " + sort.getSort());
                    flagMore = true;
                }

                if (sort.getColName().equals("idCommunity")) {
                    if (flagMore)
                        sql.append(", ");

                    sql.append(" idCommunity " + sort.getSort());
                    flagMore = true;
                }
            }
        }
        return sql;
    }

}
