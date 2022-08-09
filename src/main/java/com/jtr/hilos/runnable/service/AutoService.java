package com.jtr.hilos.runnable.service;

import java.util.List;

import com.jtr.hilos.runnable.dto.AutoDTO;
import com.jtr.hilos.runnable.dto.CocheDTO;
import com.jtr.hilos.runnable.dto.MotoDTO;

public interface AutoService
{
	public List<AutoDTO> find(String dni);
	
	public void createCocheDTO(CocheDTO dto);
	
	public void createMotoDTO(MotoDTO dto);
	
	public List<AutoDTO> findAll();
}
