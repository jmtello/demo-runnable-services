package com.jtr.hilos.runnable.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jtr.hilos.runnable.dto.AutoDTO;
import com.jtr.hilos.runnable.dto.CocheDTO;
import com.jtr.hilos.runnable.dto.MotoDTO;
import com.jtr.hilos.runnable.service.AutoService;
import com.jtr.hilos.runnable.utils.MockManager;
import com.vodafone.edc.fwk.utils.CollectionUtils;
import com.vodafone.edc.fwk.utils.StringUtils;

@Service(value = "autoService")
public class AutoServiceImpl implements AutoService
{
	
	private static final Logger LOG = LoggerFactory.getLogger(AutoServiceImpl.class);

	private static Map<String, List<AutoDTO>> map = null;
	
	static {
		
		map = new HashMap<String, List<AutoDTO>>();
		CocheDTO coche = MockManager.getJsonMock("json/", "coche_12345678X.json", CocheDTO.class);
		addAuto(coche);
		MotoDTO moto = MockManager.getJsonMock("json/", "moto_12345678X.json", MotoDTO.class);
		addAuto(moto);
		
		
	}
	
	private static void addAuto(AutoDTO dto)
	{
		String dni = dto.getDni();
		
		List<AutoDTO> vehiculos = map.get(dni);
		
		if(vehiculos == null) {
			vehiculos = new ArrayList<AutoDTO>();
		}
		
		vehiculos.add(dto);
		
		map.put(dni, vehiculos);
	}
	
	
	@Override
	public List<AutoDTO> find(String dni)
	{
		final String logTrailer = "[find]";
		
		if(StringUtils.isBlank(dni)) {
			return null;
		}
		
		LOG.debug("{} INICIO", logTrailer, dni);
		
		try
		{
			Thread.sleep(4000);

			LOG.debug("{} recuperada info de bbdd para recuperar el listado de autos para el  [dni:{}]", logTrailer, dni);
		}
		catch (InterruptedException e)
		{
			LOG.error("{} Ocurrido error al hacer el sleep del hilo", logTrailer, e);
		}
		
		return map.get(dni);
	}

	@Override
	public void createCocheDTO(CocheDTO dto)
	{
		if(dto !=null && StringUtils.isNotBlank(dto.getDni()))
		{
			addAuto(dto);
		}

	}

	@Override
	public void createMotoDTO(MotoDTO dto)
	{
		if(dto !=null && StringUtils.isNotBlank(dto.getDni()))
		{
			addAuto(dto);
		}

	}

	@Override
	public List<AutoDTO> findAll()
	{
		List<AutoDTO> results = new ArrayList<AutoDTO>();
		
		for(String keySet : map.keySet())
		{
			List<AutoDTO> temp = map.get(keySet);
			
			if(CollectionUtils.isNotEmpty(temp)) {
				results.addAll(temp);
			}
		}
		
		return results;
	}

}
