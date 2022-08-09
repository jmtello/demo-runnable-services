package com.jtr.hilos.runnable.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.jtr.hilos.runnable.dto.InfoUserDTO;
import com.jtr.hilos.runnable.service.InfoService;

@RestController
public class InfoUserController
{
	private static final Logger LOG = LoggerFactory.getLogger(InfoUserController.class);

	@Autowired
	private InfoService infoService;

	@GetMapping(value = "/info-user/find/{dni}")
	public ResponseEntity<?> findAuto(@PathVariable("dni") String dni)
	{

		InfoUserDTO result = this.infoService.getInfo(dni);

		if (result != null)
		{
			return ResponseEntity.ok().body(result);
		}
		else
		{
			String message = "No se ha encontrado en BBDD info para DNI=".concat(dni);

			LOG.error(message);
			return ResponseEntity.ok().body(message);
		}

	}

}
