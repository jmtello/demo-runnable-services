package com.jtr.hilos.runnable.dto;

public class CocheDTO extends AutoDTO
{
	private int numeroPuertas;
	private TipoCocheEnum tipoCocheEnum;
	private TipoCombustibleEnum tipoCombustibleEnum;
	
	public int getNumeroPuertas()
	{
		return numeroPuertas;
	}
	public void setNumeroPuertas(int numeroPuertas)
	{
		this.numeroPuertas = numeroPuertas;
	}
	public TipoCocheEnum getTipoCocheEnum()
	{
		return tipoCocheEnum;
	}
	public void setTipoCocheEnum(TipoCocheEnum tipoCocheEnum)
	{
		this.tipoCocheEnum = tipoCocheEnum;
	}
	public TipoCombustibleEnum getTipoCombustibleEnum()
	{
		return tipoCombustibleEnum;
	}
	public void setTipoCombustibleEnum(TipoCombustibleEnum tipoCombustibleEnum)
	{
		this.tipoCombustibleEnum = tipoCombustibleEnum;
	}
	
	
}
