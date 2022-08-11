package com.jtr.hilos.runnable.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jtr.hilos.runnable.dto.DatosPersonalesDTO;
import com.jtr.hilos.runnable.service.DatosPersonalesService;
import com.jtr.hilos.runnable.utils.MockManager;
import com.vodafone.edc.fwk.utils.CollectionUtils;
import com.vodafone.edc.fwk.utils.StringUtils;

@Service(value = "datosPersonalesService")
public class DatosPersonalesServiceImpl implements DatosPersonalesService
{

	private static final Logger LOG = LoggerFactory.getLogger(DatosPersonalesServiceImpl.class);
	
	private static Map<String, DatosPersonalesDTO> map = new HashMap<String, DatosPersonalesDTO>();
	
	static {
		DatosPersonalesDTO user = MockManager.getJsonMock("json/", "datosPersonales_12345678X.json", DatosPersonalesDTO.class);
		map.put(user.getDni(), user);
		
		DatosPersonalesDTO user2 = MockManager.getJsonMock("json/", "datosPersonales_77523322M.json", DatosPersonalesDTO.class);
		map.put(user2.getDni(), user2);
	}
	
	@Override
	public DatosPersonalesDTO find(String dni)
	{
		final String logTrailer = "[findDatosPersonales]";
		
		if(StringUtils.isBlank(dni)) {
			return null;
		}
		
		LOG.debug("{} INICIO", logTrailer);
		
		try
		{
			Thread.sleep(10000);

			LOG.debug("{} recuperada info de BBDD para la consulta de datos personales [dni:{}]", logTrailer, dni);
		}
		catch (InterruptedException e)
		{
			LOG.error("{} Ocurrido error al hacer el sleep del hilo", logTrailer, e);
		}
		
		
		return map.get(dni);
	}

	@Override
	public void create(DatosPersonalesDTO dto)
	{
		if(dto!=null && StringUtils.isNotBlank(dto.getDni()))
		{
			map.put(dto.getDni(), dto);
		}
		
	}

	@Override
	public List<DatosPersonalesDTO> findAll()
	{
		
		List<DatosPersonalesDTO> results = new ArrayList<DatosPersonalesDTO>();
		
		
		if(CollectionUtils.isNotEmpty(map))
		{
			for( DatosPersonalesDTO a : map.values())
			{
				results.add(a);
			}
		}
		
		
		return results;
		
	}

}
