package com.jtr.hilos.runnable.dto;

import java.util.List;

public class InfoUserDTO
{
	private String dni;
	
	private DatosPersonalesDTO datosPersonales;
	
	private List<AutoDTO> autos;

	public String getDni()
	{
		return dni;
	}

	public void setDni(String dni)
	{
		this.dni = dni;
	}

	public DatosPersonalesDTO getDatosPersonales()
	{
		return datosPersonales;
	}

	public void setDatosPersonales(DatosPersonalesDTO datosPersonales)
	{
		this.datosPersonales = datosPersonales;
	}

	public List<AutoDTO> getAutos()
	{
		return autos;
	}

	public void setAutos(List<AutoDTO> autos)
	{
		this.autos = autos;
	}
	
	
}
