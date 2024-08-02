package com.bussinesdomain.knowledge.controllers;

import java.util.List;
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

import com.bussinesdomain.knowledge.dto.CampaignRequestDTO;
import com.bussinesdomain.knowledge.dto.CampaignResponseDTO;
import com.bussinesdomain.knowledge.mapper.ICampaignMapper;
import com.bussinesdomain.knowledge.models.CampaignEntity;
import com.bussinesdomain.knowledge.services.ICampaignService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/campaign")
public class CampaignController {

	private final ICampaignMapper mapper;
	private final ICampaignService service;

	@GetMapping("/all")
	public ResponseEntity<List<CampaignResponseDTO>> findAll() {
		List<CampaignEntity> entities = this.service.getAll();
		List<CampaignResponseDTO> response = this.mapper.listEntityToDTO(entities);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CampaignResponseDTO> findById(@PathVariable("id") Long id) {
		CampaignEntity entity = this.service.readById(id).stream().findFirst().orElse(null);
		CampaignResponseDTO regionDTO = this.mapper.toGetDTO(entity);
		return new ResponseEntity<>(regionDTO, HttpStatus.OK);
	}
	
    @DeleteMapping("/{id}")
    public ResponseEntity<CampaignResponseDTO> delete(@PathVariable("id") Long id){
    	service.deleteById(id);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
    
	@PostMapping("/create")
	public ResponseEntity<CampaignResponseDTO> save(@Validated @RequestBody CampaignRequestDTO dto){
		CampaignEntity entity = this.service.create( this.mapper.toEntity(dto));
		CampaignResponseDTO response = this.mapper.toGetDTO(entity);
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<CampaignResponseDTO> update(@Validated @RequestBody CampaignRequestDTO dto, @PathVariable Long id){
		CampaignEntity entity = mapper.toEntity(dto);
		
		CampaignEntity obj =  service.update(entity, id);
		CampaignResponseDTO responseviaDTO = this.mapper.toGetDTO(obj);

        return new ResponseEntity<>(responseviaDTO, HttpStatus.OK);
	}
   
}
