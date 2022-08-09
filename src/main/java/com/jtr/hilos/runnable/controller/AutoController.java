package com.jtr.hilos.runnable.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jtr.hilos.runnable.dto.AutoDTO;
import com.jtr.hilos.runnable.dto.CocheDTO;
import com.jtr.hilos.runnable.dto.MotoDTO;
import com.jtr.hilos.runnable.service.AutoService;
import com.vodafone.edc.fwk.utils.CollectionUtils;
import com.vodafone.edc.fwk.utils.StringUtils;

@RestController
public class AutoController
{

	private static final Logger LOG = LoggerFactory.getLogger(AutoController.class);
	
	@Autowired
	private AutoService autoService;
	
	
	@PostMapping(value = "/auto/create-moto")
	public ResponseEntity<String> createMoto(@RequestBody @NotNull MotoDTO dto)
	{
		
		String message = "Operacion realizada con Exito";
		
		
		if(dto!=null && StringUtils.isNotBlank(dto.getDni()))
		{
		
			this.autoService.createMotoDTO(dto);
		}
		else
		{
			message = "Parametros de entrada incorrectos, no se ha guardado la solicitud";
		}
		
		
		
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(message);
	}
	
	
	@PostMapping(value = "/auto/create-coche")
	public ResponseEntity<String> createCoche(@RequestBody @NotNull CocheDTO dto)
	{
		
		String message = "Operacion realizada con Exito";
		
		
		if(dto!=null && StringUtils.isNotBlank(dto.getDni()))
		{
		
			this.autoService.createCocheDTO(dto);
		}
		else
		{
			message = "Parametros de entrada incorrectos, no se ha guardado la solicitud";
		}
		
		
		
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(message);
	}
	
	@GetMapping(value = "/auto/find-auto/{dni}")
	public ResponseEntity<?> findAuto(@PathVariable("dni") String dni)
	{
		List<AutoDTO> autosDTO = this.autoService.find(dni);
		
		if(CollectionUtils.isNotEmpty(autosDTO)) {
			return ResponseEntity.ok().body(autosDTO);
		}
		else {
			return ResponseEntity.ok().body("No se ha encontrado en BBDD auto para DNI=".concat(dni));
		}
		
		
	}
	
	@GetMapping(value = "/auto/find-all")
	public ResponseEntity<List<AutoDTO>> findAll()
	{
		List<AutoDTO> results = this.autoService.findAll();
		
		return ResponseEntity.ok().body(results);
	}
}
