package com.bussinesdomain.knowledge.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bussinesdomain.knowledge.commons.PaginationModel;
import com.bussinesdomain.knowledge.dto.CollaboratorCampaignRequestDTO;
import com.bussinesdomain.knowledge.dto.CollaboratorCampaignResponseDTO;
import com.bussinesdomain.knowledge.mapper.ICollaboratorCampaignMapper;
import com.bussinesdomain.knowledge.models.CollaboratorCampaignEntity;
import com.bussinesdomain.knowledge.services.ICollaboratorCampaignService;
import com.bussinesdomain.knowledge.services.impl.CollaboratorCampaignPaginationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/collaborator_campaign")
public class CollaboratorCampaignController {

    private final ICollaboratorCampaignMapper mapper;
    private final ICollaboratorCampaignService service;

	private final CollaboratorCampaignPaginationService paginationCommons;

	@PostMapping("/pagination")
	public ResponseEntity<?> paginator(@RequestBody PaginationModel pagination){
		log.info("PAGINATION ..... " + pagination);
		Page<CollaboratorCampaignResponseDTO> lst = paginationCommons.pagination(pagination);
		return new ResponseEntity<>(lst,HttpStatus.OK);
	}

    @GetMapping("/all")
	public ResponseEntity<List<CollaboratorCampaignResponseDTO>> findAll() {
		List<CollaboratorCampaignEntity> entities = this.service.getAll();
		List<CollaboratorCampaignResponseDTO> response = this.mapper.listEntity(entities);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CollaboratorCampaignResponseDTO> findById(@PathVariable("id") Long id) {
		CollaboratorCampaignEntity entity = this.service.readById(id).stream().findFirst().orElse(null);
		CollaboratorCampaignResponseDTO regionDTO = this.mapper.toGetResponseDTO(entity);
		return new ResponseEntity<>(regionDTO, HttpStatus.OK);
	}
	
    @DeleteMapping("/{id}")
    public ResponseEntity<CollaboratorCampaignResponseDTO> delete(@PathVariable("id") Long id){
    	service.deleteById(id);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
    
	@PostMapping("/create")
	public ResponseEntity<CollaboratorCampaignResponseDTO> save(@Validated @RequestBody CollaboratorCampaignRequestDTO dto){
		CollaboratorCampaignEntity entity = this.service.create( this.mapper.toEntity(dto));
		CollaboratorCampaignResponseDTO response = this.mapper.toGetResponseDTO(entity);
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<CollaboratorCampaignResponseDTO> update(@Validated @RequestBody CollaboratorCampaignRequestDTO dto, @PathVariable Long id){
		CollaboratorCampaignEntity entity = mapper.toEntity(dto);
		
		CollaboratorCampaignEntity obj =  service.update(entity, id);
		CollaboratorCampaignResponseDTO responseviaDTO = this.mapper.toGetResponseDTO(obj);

        return new ResponseEntity<>(responseviaDTO, HttpStatus.OK);
	}

}
