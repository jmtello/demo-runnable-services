package com.jtr.hilos.runnable.dto;

public class MotoDTO extends AutoDTO
{
	private TipoMotoEnum tipoMotoEnum;
	private Double consumo;
	private int autonomia;
	
	
	public TipoMotoEnum getTipoMotoEnum()
	{
		return tipoMotoEnum;
	}
	public void setTipoMotoEnum(TipoMotoEnum tipoMotoEnum)
	{
		this.tipoMotoEnum = tipoMotoEnum;
	}
	public Double getConsumo()
	{
		return consumo;
	}
	public void setConsumo(Double consumo)
	{
		this.consumo = consumo;
	}
	public int getAutonomia()
	{
		return autonomia;
	}
	public void setAutonomia(int autonomia)
	{
		this.autonomia = autonomia;
	}
	
	
}
