package com.jtr.hilos.runnable.utils;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vodafone.edc.fwk.utils.StreamUtils;
import com.vodafone.edc.fwk.utils.StringUtils;

public class MockManager
{
	private static final Logger LOG = LoggerFactory.getLogger(MockManager.class);
	
	public static <T> T getJsonMock(String path, String mockFile, Class<T> mapper)
	{
		T response = null;
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		try
		{
			if(StringUtils.isNotBlank(mockFile))
			{
				ClassLoader classLoader = MockManager.class.getClassLoader();
				
				InputStream inputStream = StringUtils.isNotBlank(path) ? classLoader.getResourceAsStream(path + mockFile) : classLoader.getResourceAsStream("mocks/"+mockFile);
				String content = StreamUtils.toString(inputStream);
				
				if(mapper == byte[].class)
				{
					return (T)  content.getBytes();
				}
				else if( mapper == String.class)
				{
					return (T) content;
				}
				else
				{
					response = objectMapper.readValue(content, mapper);
				}
				
			}
		}
		catch (Exception e)
		{
			LOG.error("Ocurrido error no controlado", e);
		}

		return response;
	}
}
