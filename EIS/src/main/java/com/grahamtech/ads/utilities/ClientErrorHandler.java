package com.grahamtech.ads.utilities;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResponseErrorHandler;

public class ClientErrorHandler implements ResponseErrorHandler
{
    private static final Logger logger =
    LoggerFactory.getLogger(ClientErrorHandler.class);
    
    @Override
    public void handleError(ClientHttpResponse response) throws IOException
   {
       if (response.getStatusCode() == HttpStatus.NOT_FOUND)
       {
	    // throw new Exception();
	   logger.error("ResourceNotFoundException");
       }

       // handle other possibilities, then use the catch all... 
	// throw new Exception(response.getStatusCode().toString());
	logger.error("HttpStatusCodeException: " + response.getStatusCode());
   }

   @Override
   public boolean hasError(ClientHttpResponse response) throws IOException 
   {
       if ( (response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR)
         || (response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) )
       {
           return true;
       }
       return false;
   }
}