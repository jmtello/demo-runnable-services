package com.jtr.hilos.runnable.dto;

public class DatosPersonalesDTO
{
	private String dni;
	private String nombre;
	private String apellido;
	private int edad;
	private String direccion;
	
	public String getNombre()
	{
		return nombre;
	}
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}
	public String getApellido()
	{
		return apellido;
	}
	public void setApellido(String apellido)
	{
		this.apellido = apellido;
	}
	public int getEdad()
	{
		return edad;
	}
	public void setEdad(int edad)
	{
		this.edad = edad;
	}
	public String getDireccion()
	{
		return direccion;
	}
	public void setDireccion(String direccion)
	{
		this.direccion = direccion;
	}
	
	public String getDni()
	{
		return dni;
	}
	public void setDni(String dni)
	{
		this.dni = dni;
	}
	
}
