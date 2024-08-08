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
import com.bussinesdomain.knowledge.dto.TechnologyResponseDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TechnologyPaginationService implements IPaginationCommons<TechnologyResponseDTO> {

    private final EntityManager entityManager;

    @Override
    public Page<TechnologyResponseDTO> pagination(PaginationModel pagination) {

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
            List<TechnologyResponseDTO> lista = querySelect.getResultList();

            PageRequest pageable = PageRequest.of(pagination.getPageNumber(), pagination.getRowsPerPage());

            Page<TechnologyResponseDTO> page = new PageImpl<TechnologyResponseDTO>(lista, pageable, total);
            return page;
        } catch (RuntimeException e) {
            throw new ServiceException("error when generating the pagination " + e.getMessage());
        }

    }

    @Override
    public StringBuilder getSelect() {
        StringBuilder sql = new StringBuilder(
                "SELECT new com.bussinesdomain.knowledge.dto.TechnologyResponseDTO"+
                        "(a.idTechnology,a.idCollaboratorCampaign,a.idCatalog,a.yearExpert," +
                        "a.rank) ");
        return sql;

    }

    @Override
    public StringBuilder getFrom() {
        StringBuilder sql = new StringBuilder(" FROM TechnologyEntity a ");
        return sql;

    }

    @Override
    public StringBuilder getFilters(List<Filter> filters) {
        StringBuilder sql = new StringBuilder("where 1=1 ");

        for (Filter filtro : filters) {
            if (filtro.getField().equals("idTechnology")) {
                sql.append(" AND a.idTechnology = :idTechnology");
            }
            if (filtro.getField().equals("idCollaboratorCampaign")) {
                sql.append(" AND a.idCollaboratorCampaign = :idCollaboratorCampaign");
            }
            if (filtro.getField().equals("idCatalog")) {
                sql.append(" AND a.idCatalog = :idCatalog");
            }
            if (filtro.getField().equals("yearExpert")) {
                sql.append(" AND a.yearExpert = :yearExpert");
            }
            if (filtro.getField().equals("rank")) {
                sql.append(" AND a.rank = :rank");
            }
        }

        return sql;

    }

    @Override
    public Query setParams(List<Filter> filters, Query query) {
        for (Filter filtro : filters) {
            if (filtro.getField().equals("idTechnology")) {
                query.setParameter("idTechnology", filtro.getValue());
            }
            if (filtro.getField().equals("idCollaboratorCampaign")) {
                query.setParameter("idCollaboratorCampaign", filtro.getValue());
            }
            if (filtro.getField().equals("idCatalog")) {
                query.setParameter("idCatalog", filtro.getValue());
            }
            if (filtro.getField().equals("yearExpert")) {
                query.setParameter("yearExpert", filtro.getValue());
            }
            if (filtro.getField().equals("rank")) {
                query.setParameter("rank", filtro.getValue());
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
                if (sort.getColName().equals("idTechnology")) {
                    if (flagMore)
                        sql.append(", ");

                    sql.append(" idTechnology " + sort.getSort());
                    flagMore = true;
                }

                if (sort.getColName().equals("idCollaboratorCampaign")) {
                    if (flagMore)
                        sql.append(", ");

                    sql.append(" idCollaboratorCampaign " + sort.getSort());
                    flagMore = true;
                }

                if (sort.getColName().equals("idCatalog")) {
                    if (flagMore)
                        sql.append(", ");

                    sql.append(" idCatalog " + sort.getSort());
                    flagMore = true;
                }

                if (sort.getColName().equals("yearExpert")) {
                    if (flagMore)
                        sql.append(", ");

                    sql.append(" yearExpert " + sort.getSort());
                    flagMore = true;
                }

                if (sort.getColName().equals("rank")) {
                    if (flagMore)
                        sql.append(", ");

                    sql.append(" rank " + sort.getSort());
                    flagMore = true;
                }
            }
        }
        return sql;
    }
}
