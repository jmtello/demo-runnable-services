package com.jtr.hilos.runnable.service;

import java.util.List;
import java.util.Map;

import com.jtr.hilos.runnable.dto.DatosPersonalesDTO;

public interface DatosPersonalesService 
{	
	public DatosPersonalesDTO find(String dni);
	
	public void create(DatosPersonalesDTO dto);
	
	public List<DatosPersonalesDTO> findAll();
	
}
