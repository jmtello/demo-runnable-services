package com.jtr.hilos.runnable.service.impl;

import java.util.List;
import java.util.Map;

import com.jtr.hilos.runnable.dto.AutoDTO;
import com.jtr.hilos.runnable.service.AutoService;
import com.vodafone.edc.fwk.utils.CollectionUtils;
import com.vodafone.edc.fwk.utils.StringUtils;

public class AutoRunnableServiceImpl implements Runnable
{

	private String dni;
	
	private AutoService autoService;
	
	private Map<String, List<AutoDTO>> response;
	
	
	
	public AutoRunnableServiceImpl(String dni, AutoService autoService, Map<String, List<AutoDTO>> response)
	{
		this.dni = dni;
		this.autoService = autoService;
		this.response = response;
	}



	@Override
	public void run()
	{
		List<AutoDTO> results = null;
		
		if(StringUtils.isNotBlank(dni) && autoService!=null && response!=null)
		{
			results = this.autoService.find(dni);
			
			synchronized(response)
			{
				if(CollectionUtils.isNotEmpty(results))
				{
					response.put(dni, results);
				}
			}
		}
		
	}

}
