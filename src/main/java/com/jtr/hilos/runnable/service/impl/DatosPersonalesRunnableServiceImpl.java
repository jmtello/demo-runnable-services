package com.jtr.hilos.runnable.service.impl;

import java.util.Map;

import com.jtr.hilos.runnable.dto.DatosPersonalesDTO;
import com.jtr.hilos.runnable.service.DatosPersonalesService;
import com.vodafone.edc.fwk.utils.StringUtils;

public class DatosPersonalesRunnableServiceImpl implements Runnable
{

	private String dni;
	
	private DatosPersonalesService datosPersonalesService;

	private Map<String, DatosPersonalesDTO> response;
	
	
	public DatosPersonalesRunnableServiceImpl(String dni, DatosPersonalesService datosPersonalesService, Map<String, DatosPersonalesDTO> response)
	{
		this.dni = dni;
		this.datosPersonalesService = datosPersonalesService;
		this.response = response;
	}
	
	@Override
	public void run()
	{
		if(StringUtils.isNotBlank(dni) && this.datosPersonalesService!=null && response!=null)
		{
			DatosPersonalesDTO result = this.datosPersonalesService.find(dni);
			
			synchronized(response)
			{
				if(result !=null)
				{
					response.put(dni, result);
				}
			}
		}
		
		
	}


	public Map<String, DatosPersonalesDTO> getResponse()
	{
		return response;
	}

}
