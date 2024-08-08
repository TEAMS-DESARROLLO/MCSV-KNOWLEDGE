package com.bussinesdomain.knowledge.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bussinesdomain.knowledge.client.master.MasterClient;
import com.bussinesdomain.knowledge.client.master.dto.CollaboratorResponseDTO;
import com.bussinesdomain.knowledge.commons.Filter;
import com.bussinesdomain.knowledge.commons.IPaginationCommons;
import com.bussinesdomain.knowledge.commons.PaginationModel;
import com.bussinesdomain.knowledge.commons.SortModel;
import com.bussinesdomain.knowledge.config.ConfigToken;
import com.bussinesdomain.knowledge.dto.ParticipantResponseDTO;
import com.bussinesdomain.knowledge.exception.ServiceException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ParticipantPaginationServiceImpl implements IPaginationCommons<ParticipantResponseDTO>{

    private final EntityManager entityManager;
    private final MasterClient masterClient;
	
	@Override
	public Page<ParticipantResponseDTO> pagination(PaginationModel pagination) {
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
			List<ParticipantResponseDTO> lista = querySelect.getResultList();
            PageRequest pageable = PageRequest.of(pagination.getPageNumber(), pagination.getRowsPerPage());

			Page<ParticipantResponseDTO> page = new PageImpl<ParticipantResponseDTO>(lista, pageable, total);

			return page;
		} catch (RuntimeException e) {
			throw new ServiceException("error when generating the pagination " + e.getMessage(), e.getCause());
		}
	}



	@Override
	public StringBuilder getSelect() {
		StringBuilder sql = new StringBuilder("SELECT new com.bussinesdomain.maestros.dto.ParticipantResponseDTO("+
        "r.idParticipant,"+
        "r.email,"+
        "r.code,"+
        "r.responseStatus,"+
        "r.idCampaign,"+
        "c.campaignDescription,"+
        "r.idCollaborator,"+
        "r.namesCollaborator,"+
        "r.lastnameCollaborator,"+
        "r.idLeader,"+
        "r.namesLeader,"+
        "r.idRegion,"+
        "r.descriptionRegion,"+
        "r.idCommunity,"+
        "r.descriptionCommunity,"+
        "r.idFunctionalLeader,"+
        "r.namesFunctionalLeader"+
        ") ");
        return sql;
	}

	@Override
	public StringBuilder getFrom() {
		StringBuilder sql = new StringBuilder(" FROM ParticipantEntity r  "+
        "inner join CampaignEntity c r.campaign=c ");
        return sql;
	}

	@Override
	public StringBuilder getFilters(List<Filter> filters) {
		StringBuilder sql = new StringBuilder("where 1=1 ");

        for(Filter filtro:filters){
            if(filtro.getField().equals("idParticipant")){
                sql.append(" AND r.idParticipant = :idParticipant");
            }
            if(filtro.getField().equals("email")){
                sql.append(" AND r.email LIKE :email ");
            }
            if(filtro.getField().equals("code")){
                sql.append(" AND r.code LIKE :code ");
            }
            if(filtro.getField().equals("responseStatus")){
                sql.append(" AND r.responseStatus LIKE :responseStatus");
            }
            if(filtro.getField().equals("idCampaign")){
                sql.append(" AND r.idCampaign = :idCampaign");
            }
            if(filtro.getField().equals("campaignDescription")){
                sql.append(" AND c.description LIKE :campaignDescription ");
            }
            if(filtro.getField().equals("idCollaborator")){
                sql.append(" AND r.idCollaborator = :idCollaborator");
            }
            if(filtro.getField().equals("namesCollaborator")){
                sql.append(" AND r.namesCollaborator LIKE :namesCollaborator ");
            }
            if(filtro.getField().equals("lastnameCollaborator")){
                sql.append(" AND r.lastnameCollaborator LIKE :lastnameCollaborator ");
            }
            if(filtro.getField().equals("idLeader")){
                sql.append(" AND r.idLeader = :idLeader");
            }
            if(filtro.getField().equals("namesLeader")){
                sql.append(" AND r.namesLeader LIKE :namesLeader ");
            }
            if(filtro.getField().equals("idRegion")){
                sql.append(" AND r.idRegion = :idRegion");
            }
            if(filtro.getField().equals("descriptionRegion")){
                sql.append(" AND r.descriptionRegion LIKE :descriptionRegion ");
            }
            if(filtro.getField().equals("idCommunity")){
                sql.append(" AND r.idCommunity = :idCommunity");
            }
            if(filtro.getField().equals("descriptionCommunity")){
                sql.append(" AND r.descriptionCommunity LIKE :descriptionCommunity ");
            }
            if(filtro.getField().equals("idFunctionalLeader")){
                sql.append(" AND r.idFunctionalLeader = :idFunctionalLeader");
            }
            if(filtro.getField().equals("namesFunctionalLeader")){
                sql.append(" AND r.namesFunctionalLeader LIKE :namesFunctionalLeader ");
            }
        }

        return sql;
	}

	@Override
	public Query setParams(List<Filter> filters, Query query) {
		for(Filter filtro:filters){
            if(filtro.getField().equals("idParticipant")){
                query.setParameter("idParticipant",filtro.getValue() );
            }
            if(filtro.getField().equals("email")){
                query.setParameter("email","%"+filtro.getValue()+"%");
            }
            if(filtro.getField().equals("code")){
                query.setParameter("code","%"+filtro.getValue()+"%");
            }
            if(filtro.getField().equals("responseStatus")){
                query.setParameter("responseStatus","%"+filtro.getValue()+"%");
            }
            if(filtro.getField().equals("idCampaign")){
                query.setParameter("idCampaign",filtro.getValue() );
            }
            if(filtro.getField().equals("campaignDescription")){
                query.setParameter("campaignDescription","%"+filtro.getValue()+"%");
            }
            if(filtro.getField().equals("idCollaborator")){
                query.setParameter("idCollaborator",filtro.getValue() );
            }
            if(filtro.getField().equals("namesCollaborator")){
                query.setParameter("namesCollaborator","%"+filtro.getValue()+"%");
            }
            if(filtro.getField().equals("lastnameCollaborator")){
                query.setParameter("lastnameCollaborator","%"+filtro.getValue()+"%");
            }
            if(filtro.getField().equals("idLeader")){
                query.setParameter("idLeader",filtro.getValue() );
            }
            if(filtro.getField().equals("namesLeader")){
                query.setParameter("namesLeader","%"+filtro.getValue()+"%");
            }
            if(filtro.getField().equals("idRegion")){
                query.setParameter("idRegion",filtro.getValue() );
            }
            if(filtro.getField().equals("descriptionRegion")){
                query.setParameter("descriptionRegion","%"+filtro.getValue()+"%");
            }
            if(filtro.getField().equals("idCommunity")){
                query.setParameter("idCommunity",filtro.getValue() );
            }
            if(filtro.getField().equals("descriptionCommunity")){
                query.setParameter("descriptionCommunity","%"+filtro.getValue()+"%");
            }
            if(filtro.getField().equals("idFunctionalLeader")){
                query.setParameter("idFunctionalLeader",filtro.getValue() );
            }
            if(filtro.getField().equals("namesFunctionalLeader")){
                query.setParameter("namesFunctionalLeader","%"+filtro.getValue()+"%");
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
                if(sort.getColName().equals("idParticipant")){
                    if(flagMore)
                        sql.append(", ");

                    sql.append( " r.idParticipant " + sort.getSort() );
                    flagMore = true;
                }

                if(sort.getColName().equals("email")){
                    if(flagMore)
                        sql.append(", ");
                    sql.append( " r.email " + sort.getSort() );
                    flagMore = true;
                }

                if(sort.getColName().equals("code")){
                    if(flagMore)
                        sql.append(", ");
                    sql.append( " r.code " + sort.getSort() );
                    flagMore = true;
                }

                if(sort.getColName().equals("responseStatus")){
                    if(flagMore)
                        sql.append(", ");

                    sql.append( " r.responseStatus " + sort.getSort() );
                    flagMore = true;
                }

                if(sort.getColName().equals("idCampaign")){
                    if(flagMore)
                        sql.append(", ");

                    sql.append( " r.idCampaign " + sort.getSort() );
                    flagMore = true;
                }

                if(sort.getColName().equals("campaignDescription")){
                    if(flagMore)
                        sql.append(", ");

                    sql.append( " c.description " + sort.getSort() );
                    flagMore = true;
                }
                if(sort.getColName().equals("idCollaborator")){
                    if(flagMore)
                        sql.append(", ");

                    sql.append( " r.idCollaborator " + sort.getSort() );
                    flagMore = true;
                }
                if(sort.getColName().equals("namesCollaborator")){
                    if(flagMore)
                        sql.append(", ");

                    sql.append( " r.namesCollaborator " + sort.getSort() );
                    flagMore = true;
                }
                if(sort.getColName().equals("lastnameCollaborator")){
                    if(flagMore)
                        sql.append(", ");

                    sql.append( " r.lastnameCollaborator " + sort.getSort() );
                    flagMore = true;
                }
                if(sort.getColName().equals("idLeader")){
                    if(flagMore)
                        sql.append(", ");

                    sql.append( " r.idLeader " + sort.getSort() );
                    flagMore = true;
                }
                if(sort.getColName().equals("namesLeader")){
                    if(flagMore)
                        sql.append(", ");

                    sql.append( " r.namesLeader " + sort.getSort() );
                    flagMore = true;
                }
                if(sort.getColName().equals("idRegion")){
                    if(flagMore)
                        sql.append(", ");

                    sql.append( " r.idRegion " + sort.getSort() );
                    flagMore = true;
                }
                if(sort.getColName().equals("descriptionRegion")){
                    if(flagMore)
                        sql.append(", ");

                    sql.append( " r.descriptionRegion " + sort.getSort() );
                    flagMore = true;
                }
                if(sort.getColName().equals("idCommunity")){
                    if(flagMore)
                        sql.append(", ");

                    sql.append( " r.idCommunity " + sort.getSort() );
                    flagMore = true;
                }
                if(sort.getColName().equals("descriptionCommunity")){
                    if(flagMore)
                        sql.append(", ");

                    sql.append( " r.descriptionCommunity " + sort.getSort() );
                    flagMore = true;
                }
                if(sort.getColName().equals("idFunctionalLeader")){
                    if(flagMore)
                        sql.append(", ");

                    sql.append( " r.idFunctionalLeader " + sort.getSort() );
                    flagMore = true;
                }
                if(sort.getColName().equals("namesFunctionalLeader")){
                    if(flagMore)
                        sql.append(", ");

                    sql.append( " r.namesFunctionalLeader " + sort.getSort() );
                    flagMore = true;
                }
                
           }
        }
         return sql;
	}

}

