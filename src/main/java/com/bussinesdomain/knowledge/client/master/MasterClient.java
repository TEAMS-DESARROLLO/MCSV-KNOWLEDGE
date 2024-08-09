package com.bussinesdomain.knowledge.client.master;


import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.bussinesdomain.knowledge.client.master.dto.CollaboratorResponseDTO;
import com.bussinesdomain.knowledge.client.master.dto.CommunityResponseDTO;

@FeignClient( name="mcsv-master")
public interface MasterClient {

    @GetMapping("/collaborator/{idCollaborator}")
	public ResponseEntity<CollaboratorResponseDTO> findCollaboratorById(@RequestHeader("Authorization") String authorization,@PathVariable("idCollaborator") Long idCollaborator);
	
	@GetMapping("/collaborator/")
	public ResponseEntity<List<CollaboratorResponseDTO>> findCollaboratorsByListId(@RequestHeader("Authorization") String authorization,@RequestParam("idCollaboratorList") List<Long> idCollaboratorList);
	

    @GetMapping("/community/{idCommunity}")
	public ResponseEntity<CommunityResponseDTO> findCommunityById(@RequestHeader("Authorization") String authorization,@PathVariable("idCommunity") Long idCommunity);
	
	@GetMapping("/community/")
	public ResponseEntity<List<CommunityResponseDTO>> findCommunitiesByListId(@RequestHeader("Authorization") String authorization,@RequestParam("idCommunityList") List<Long> idCommunityList);
	
}
