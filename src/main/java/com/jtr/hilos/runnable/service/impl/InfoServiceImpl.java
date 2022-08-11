package com.jtr.hilos.runnable.service.impl;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jtr.hilos.runnable.dto.AutoDTO;
import com.jtr.hilos.runnable.dto.DatosPersonalesDTO;
import com.jtr.hilos.runnable.dto.InfoUserDTO;
import com.jtr.hilos.runnable.service.AutoService;
import com.jtr.hilos.runnable.service.DatosPersonalesService;
import com.jtr.hilos.runnable.service.InfoService;
import com.vodafone.edc.fwk.utils.AutorizadoContext;
import com.vodafone.edc.fwk.utils.CollectionUtils;
import com.vodafone.edc.fwk.utils.LogUtils;
import com.vodafone.edc.fwk.utils.StringUtils;

@Service("infoService")
public class InfoServiceImpl implements InfoService
{

	private static final Logger LOG = LoggerFactory.getLogger(InfoServiceImpl.class);
	
	@Autowired
	private DatosPersonalesService datosPersonalesService;
	
	@Autowired
	private AutoService autoService;
	

	@Override
	public InfoUserDTO getInfo(String dni)
	{
//		return this.getInfoUser(dni);
		
		return this.getCallableInfoUser(dni);
	}
	
	private InfoUserDTO getCallableInfoUser(String dni)
	{
		final String logTrailer = "[getCallableInfoUser]";
		
		InfoUserDTO result = new InfoUserDTO();
		
		try
		{
		
			//sleep 10 segundos
			CompletableFuture<DatosPersonalesDTO> futureDatos = CompletableFuture.supplyAsync(() ->{
				return this.datosPersonalesService.find(dni);
			});
			
			//sleep 4 segundos
			CompletableFuture<List<AutoDTO>> futureAuto = CompletableFuture.supplyAsync(() ->{
				return this.autoService.find(dni);
			});
			
			CompletableFuture.allOf(futureDatos, futureAuto).get();
			
			DatosPersonalesDTO datosPersonales = futureDatos.get();
			List<AutoDTO> autos = futureAuto.get();
			
			result.setDni(dni);
			
			result.setDatosPersonales(datosPersonales);
			
			if (CollectionUtils.isNotEmpty(autos))
			{
				result.setAutos(autos);
			}
		}
		catch(Exception exc)
		{
			LOG.error("{} Ocurrido error no controlado ", logTrailer, exc);
		}
		
		return result;
	}
	
//	private void executeFutures(CompletableFuture<?>... futures) throws InterruptedException, ExecutionException
//	{
//		CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(futures);
//		
//		combinedFuture.get();
//	}
	
	
	private InfoUserDTO getInfoUser(String dni)
	{
		if (StringUtils.isBlank(dni))
		{
			return null;
		}

		Map<String, DatosPersonalesDTO> datosPersonalesMap = new HashMap<String, DatosPersonalesDTO>();
		Map<String, List<AutoDTO>> autoServiceMap = new HashMap<String, List<AutoDTO>>();

		Runnable datosPersonalesRunnable = new DatosPersonalesRunnableServiceImpl(dni, datosPersonalesService, datosPersonalesMap);
		
		Runnable autoServiceRunnable = new AutoRunnableServiceImpl(dni, autoService, autoServiceMap);

		this.asyncServices(datosPersonalesRunnable, autoServiceRunnable);
		
		InfoUserDTO result = new InfoUserDTO();
		result.setDni(dni);

		DatosPersonalesDTO datosPersonales = datosPersonalesMap.get(dni);
		result.setDatosPersonales(datosPersonales);

		List<AutoDTO> autos = autoServiceMap.get(dni);
		if (CollectionUtils.isNotEmpty(autos))
		{
			result.setAutos(autos);
		}

		return result;
	}
	

	private void asyncServices(Runnable... runnableServices)
	{
		String logTrailer = "[asyncServices]";
		if (runnableServices != null && runnableServices.length > 0)
		{
			Instant start = Instant.now();
			List<Thread> threads = new ArrayList<Thread>();

			for (Runnable runnableService : runnableServices)
			{
				Thread thread = new Thread(runnableService);
				threads.add(thread);
				thread.start();
			}

			// Cuando terminen todos los hilos continuamos
			for (Thread thread : threads)
			{
				try
				{
					thread.join();
				}
				catch (InterruptedException e)
				{
					LogUtils.error(LOG, "[{}] Error al invocar al metodo join de los hilos", logTrailer, e);
				}
			}

			Duration duration = Duration.between(start, Instant.now());
			long time = duration.toMillis();
			LogUtils.info(LOG, "{} [duration:{}]", logTrailer, time);
		}
	}
}
