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
import com.bussinesdomain.knowledge.dto.CollaboratorCampaignResponseDTO;
import com.bussinesdomain.knowledge.dto.TechnologyRequestDTO;
import com.bussinesdomain.knowledge.dto.TechnologyResponseDTO;
import com.bussinesdomain.knowledge.mapper.ITechnologyMapper;
import com.bussinesdomain.knowledge.models.TechnologyEntity;
import com.bussinesdomain.knowledge.services.ITechnologyService;
import com.bussinesdomain.knowledge.services.impl.TechnologyPaginationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/technology")
public class TechnologyController {

    private final ITechnologyMapper mapper;
    private final ITechnologyService service;

	private final TechnologyPaginationService paginationCommons;

	
	@PostMapping("/pagination")
	public ResponseEntity<?> paginator(@RequestBody PaginationModel pagination){
		log.info("PAGINATION ..... " + pagination);
		Page<TechnologyResponseDTO> lst = paginationCommons.pagination(pagination);
		return new ResponseEntity<>(lst,HttpStatus.OK);
	}

    @GetMapping("/all")
	public ResponseEntity<List<TechnologyResponseDTO>> findAll() {
		List<TechnologyEntity> entities = this.service.getAll();
		List<TechnologyResponseDTO> response = this.mapper.listEntityToDTO(entities);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TechnologyResponseDTO> findById(@PathVariable("id") Long id) {
		TechnologyEntity entity = this.service.readById(id).stream().findFirst().orElse(null);
		TechnologyResponseDTO regionDTO = this.mapper.toGetResponseDTO(entity);
		return new ResponseEntity<>(regionDTO, HttpStatus.OK);
	}
	
    @DeleteMapping("/{id}")
    public ResponseEntity<TechnologyResponseDTO> delete(@PathVariable("id") Long id){
    	service.deleteById(id);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
    
	@PostMapping("/create")
	public ResponseEntity<TechnologyResponseDTO> save(@Validated @RequestBody TechnologyRequestDTO dto){
		TechnologyEntity entity = this.service.create( this.mapper.toEntity(dto));
		TechnologyResponseDTO response = this.mapper.toGetResponseDTO(entity);
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<TechnologyResponseDTO> update(@Validated @RequestBody TechnologyRequestDTO dto, @PathVariable Long id){
		TechnologyEntity entity = mapper.toEntity(dto);
		
		TechnologyEntity obj =  service.update(entity, id);
		TechnologyResponseDTO responseviaDTO = this.mapper.toGetResponseDTO(obj);

        return new ResponseEntity<>(responseviaDTO, HttpStatus.OK);
	}
}
