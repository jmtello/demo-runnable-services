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

import com.jtr.hilos.runnable.dto.DatosPersonalesDTO;
import com.jtr.hilos.runnable.service.DatosPersonalesService;
import com.vodafone.edc.fwk.utils.StringUtils;

@RestController
public class DatosPersonalesController
{
	private static final Logger LOG = LoggerFactory.getLogger(DatosPersonalesController.class);
	
	@Autowired
	private DatosPersonalesService datosPersonalesService;
	
	
	@GetMapping(value = "/datos-personales/find-all")
	public ResponseEntity<List<DatosPersonalesDTO>> findAll()
	{
		List<DatosPersonalesDTO> results = this.datosPersonalesService.findAll();
		
		return ResponseEntity.ok().body(results);
	}
	
	

	@PostMapping(value = "/datos-personales/create")
	public ResponseEntity<String> create(@RequestBody @NotNull DatosPersonalesDTO dto)
	{
		
		String message = "Operacion realizada con Exito";
		
		
		if(dto!=null && StringUtils.isNotBlank(dto.getDni()))
		{
		
			this.datosPersonalesService.create(dto);
			
		}
		else
		{
			message = "Parametros de entrada incorrectos, no se ha guardado la solicitud";
		}
		
		
		
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(message);
	}
	
	
	@GetMapping(value = "/datos-personales/find/{dni}")
	public ResponseEntity<?> findAuto(@PathVariable("dni") String dni)
	{
		
		DatosPersonalesDTO result = this.datosPersonalesService.find(dni);
		
		if(result!=null) {
			return ResponseEntity.ok().body(result);
		}
		else {
			return ResponseEntity.ok().body("No se ha encontrado en BBDD auto para DNI=".concat(dni));
		}
		
		
	}
}
