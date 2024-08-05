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
import com.bussinesdomain.knowledge.dto.ParticipantRequestDTO;
import com.bussinesdomain.knowledge.dto.ParticipantResponseDTO;
import com.bussinesdomain.knowledge.mapper.IParticipantMapper;
import com.bussinesdomain.knowledge.models.ParticipantEntity;
import com.bussinesdomain.knowledge.services.IParticipantService;
import com.bussinesdomain.knowledge.services.impl.ParticipantPaginationServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/participant")
public class ParticipantController {

	private final IParticipantMapper mapper;
	private final IParticipantService service;

	private final ParticipantPaginationServiceImpl paginationCommons;
	
	
	@PostMapping("/pagination")
	public ResponseEntity<?> paginator(@RequestBody PaginationModel pagination){
		log.info("PAGINATION ..... " + pagination);
		Page<ParticipantResponseDTO> lst = paginationCommons.pagination(pagination);
		return new ResponseEntity<>(lst,HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<ParticipantResponseDTO>> findAll() {
		List<ParticipantEntity> entities = this.service.getAll();
		List<ParticipantResponseDTO> response = this.mapper.listEntityToDTO(entities);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ParticipantResponseDTO> findById(@PathVariable("id") Long id) {
		ParticipantEntity entity = this.service.readById(id).stream().findFirst().orElse(null);
		ParticipantResponseDTO regionDTO = this.mapper.toGetDTO(entity);
		return new ResponseEntity<>(regionDTO, HttpStatus.OK);
	}
	
    @DeleteMapping("/{id}")
    public ResponseEntity<ParticipantResponseDTO> delete(@PathVariable("id") Long id){
    	service.deleteById(id);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
    
	@PostMapping("/create")
	public ResponseEntity<ParticipantResponseDTO> save(@Validated @RequestBody ParticipantRequestDTO dto){
		ParticipantEntity entity = this.service.create( this.mapper.toEntity(dto));
		ParticipantResponseDTO response = this.mapper.toGetDTO(entity);
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<ParticipantResponseDTO> update(@Validated @RequestBody ParticipantRequestDTO dto, @PathVariable Long id){
		ParticipantEntity entity = mapper.toEntity(dto);
		
		ParticipantEntity obj =  service.update(entity, id);
		ParticipantResponseDTO responseviaDTO = this.mapper.toGetDTO(obj);

        return new ResponseEntity<>(responseviaDTO, HttpStatus.OK);
	}
   
}

