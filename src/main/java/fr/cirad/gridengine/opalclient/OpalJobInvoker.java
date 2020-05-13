/*******************************************************************************
 * OpalClient - Copyright (C) 2020 <CIRAD>
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Affero General Public License, version 3 as published by
 * the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 *
 *
 * See <http://www.gnu.org/licenses/agpl.html> for details about GNU General
 * Public License V3.
 *******************************************************************************/
package fr.cirad.gridengine.opalclient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceException;

import org.apache.axis.types.URI;
import org.apache.log4j.Logger;

import edu.sdsc.nbcr.opal.AppServicePortTypeProxy;
import edu.sdsc.nbcr.opal.types.BlockingOutputType;
import edu.sdsc.nbcr.opal.types.FaultType;
import edu.sdsc.nbcr.opal.types.InputFileType;
import edu.sdsc.nbcr.opal.types.JobInputType;
import edu.sdsc.nbcr.opal.types.JobSubOutputType;
import edu.sdsc.nbcr.opal.types.StatusOutputType;

@SuppressWarnings("serial")
public class OpalJobInvoker extends HttpServlet
{
	private static final Logger LOG = Logger.getLogger(OpalJobInvoker.class);
	
	/* constants below are copied from org.globus.gram.internal.GRAMConstants: we just don't want to add a dependency for them */	
	public static final int STATUS_PENDING = 1;  
	public static final int STATUS_ACTIVE = 2;	  
	public static final int STATUS_FAILED = 4;  
	public static final int STATUS_DONE = 8;  
	public static final int STATUS_SUSPENDED = 16;	  
	public static final int STATUS_UNSUBMITTED = 32;	 
	public static final int STATUS_STAGE_IN = 64;	  
	public static final int STATUS_STAGE_OUT = 128;	  
	  
	private static HashMap<String, AppServicePortTypeProxy> serviceProxies = new HashMap<String, AppServicePortTypeProxy>();

    public static BlockingOutputType invokeBlocking(String sServiceName, String cmdArgs, URI[] inputDocs, Integer numProcs, String notificationEmail) throws IOException
    {
    	try
    	{
	    	return (BlockingOutputType) invoke(sServiceName, cmdArgs, inputDocs, numProcs, notificationEmail, true);
		}
		catch (FaultType ft)
		{
			throw new IOException(ft.getMessage() == null ? ft.getMessage1() : ft.getMessage());
		}
    }
    
    public static JobSubOutputType invokeNonBlocking(String sServiceName, String cmdArgs, URI[] inputDocs, Integer numProcs, String notificationEmail) throws IOException
    {
    	try
    	{
    		return (JobSubOutputType) invoke(sServiceName, cmdArgs, inputDocs, numProcs, notificationEmail, false);
    	}
    	catch (FaultType ft)
    	{
    		throw new IOException(ft.getMessage() == null ? ft.getMessage1() : ft.getMessage());
    	}
    }
	
	private static Serializable invoke(String sServiceName, String cmdArgs, URI[] inputDocs, Integer numProcs, String notificationEmail, boolean fBlocking) throws IOException
	{
		JobInputType in = new JobInputType();

		// initialize list of files
		Vector<InputFileType> inputFileVector = new Vector<InputFileType>();
		
		AppServicePortTypeProxy appServicePort = getServiceProxyByName(sServiceName);
		
		for (URI uri : inputDocs)
		{
			String[] splittedInputURL = uri.toString().split("/");
			InputFileType infile = new InputFileType();
			String sFileName = splittedInputURL[splittedInputURL.length - 1];
			infile.setName(sFileName);
			infile.setLocation(uri);
			inputFileVector.add(infile);			
		}

		if (cmdArgs != null) {
			LOG.debug("Command line arguments: " + cmdArgs);
			in.setArgList(cmdArgs);
		}					

		if (numProcs == null || numProcs.intValue() < 1)
			numProcs = 1;
		LOG.debug("Number of processors: " + numProcs);
		in.setNumProcs(new Integer(numProcs));

		if (notificationEmail != null) {
			LOG.debug("User email for notification: " + notificationEmail);
			in.setUserEmail(notificationEmail);

			LOG.debug("Instructing server to notify on job completion");
			in.setSendNotification(true);
		}

		
		//////////////////////////////////////////////////////////////////////////////////
		//	BEGINNING OF DISABLED BLOCK													//
		//////////////////////////////////////////////////////////////////////////////////


		boolean extractInputs = /*line.hasOption("z");*/	false;
		if (extractInputs) {
			LOG.debug("Instructing server to unzip/untar zipped files");
			in.setExtractInputs(true);
		}



		// get list of input files
		String[] inputFiles = /*line.getOptionValues("f");*/	null;
		if (inputFiles != null) {
			for (int i = 0; i < inputFiles.length; i++) {
				File f = new File(inputFiles[i]);
				byte[] data = new byte[(int) f.length()];
				FileInputStream fIn = new FileInputStream(f);
				fIn.read(data);
				fIn.close();
				InputFileType infile = new InputFileType();
				infile.setName(f.getName());
				infile.setContents(data);
				inputFileVector.add(infile);
			}
		}

		// get list of input urls
		String[] inputURLs = /*line.getOptionValues("u");*/	null;
		if (inputURLs != null) {
			for (int i = 0; i < inputURLs.length; i++) {
				String address = inputURLs[i];
				int lastSlashIndex = address.lastIndexOf('/');
				String fileName = null;
				if ((lastSlashIndex >= 0)
						&& (lastSlashIndex < address.length() - 1)) {
					fileName = address.substring(lastSlashIndex + 1);
				} else {
					System.err.println("Could not figure out local file name for " + address);
					System.exit(1);
				}

				InputFileType infile = new InputFileType();
				infile.setName(fileName);
				infile.setLocation(new URI(address));
				inputFileVector.add(infile);
			}
		}
		
		// get list of attachments from command-line
		String[] attachFiles = /*line.getOptionValues("b");*/	null;
		if (attachFiles != null) {
			for (int i = 0; i < attachFiles.length; i++) {
				DataHandler dh = new DataHandler(new FileDataSource(
						attachFiles[i]));
				InputFileType infile = new InputFileType();
				File f = new File(attachFiles[i]);
				infile.setName(f.getName());
				infile.setAttachment(dh);
				inputFileVector.add(infile);
			}
		}
		
		//////////////////////////////////////////////////////////////////////////////////
		//	END OF DISABLED BLOCK														//
		//////////////////////////////////////////////////////////////////////////////////

				
		
		
		// add the files to the parameters
		int arraySize = inputFileVector.size();
		if (arraySize > 0) {
			InputFileType[] infileArray = new InputFileType[arraySize];
			for (int i = 0; i < arraySize; i++) {
				infileArray[i] = (InputFileType) inputFileVector.get(i);
			}
			in.setInputFile(infileArray);
		}

		LOG.info("Calling " + appServicePort.getEndpoint());
		
		Serializable subOut;
		StatusOutputType status;

		if (fBlocking)
		{	
			// set up a blocking call
			LOG.debug("Making blocking invocation on Opal service -");
			subOut = appServicePort.launchJobBlocking(in);
			
			LOG.debug("Blocking job submitted");
			status = ((BlockingOutputType)subOut).getStatus();
		}
		else
		{
			// set up a non-blocking call
			LOG.debug("Making non-blocking invocation on Opal service -");
			subOut = appServicePort.launchJob(in);
			
			LOG.debug("Received jobID: " + ((JobSubOutputType)subOut).getJobID());
			status = ((JobSubOutputType)subOut).getStatus();
		}

		LOG.debug("Current Status:\n" + "\tCode: "
				+ status.getCode() + "\n" + "\tMessage: "
				+ status.getMessage() + "\n" + "\tOutput Base URL: "
				+ status.getBaseURL());		

		return subOut;
	}
	
	private static AppServicePortTypeProxy getServiceProxyByName(String sServiceName)
	{		
		try
		{
			String sEndPoint = OpalClientConfig.get("SERVICE_" + sServiceName + "_ENDPOINT");		
			
			AppServicePortTypeProxy appServicePort = serviceProxies.get(sEndPoint);
			if (appServicePort == null)
			{
				appServicePort = new AppServicePortTypeProxy(sEndPoint);
				serviceProxies.put(sEndPoint, appServicePort);
			}
			return appServicePort;
		}
		catch (java.util.MissingResourceException mre)
		{
			throw new WebServiceException("No such service declared in properties file: " + sServiceName);
		}
	}
	
	public static StatusOutputType getJobStatus(String sServiceName, String sJobID) throws FaultType, RemoteException
	{
		return getServiceProxyByName(sServiceName).queryStatus(sJobID);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/plain"); 
		try
		{
			String sServiceName = request.getParameter("service");
			String sJobID = request.getParameter("job");
			response.getWriter().write("" + getJobStatus(sServiceName, sJobID).getCode());
		}
		catch (Exception e)
		{
			LOG.warn("Unable to retrieve job status for querystring " + request.getQueryString() + " : " + e.getMessage(), e);
		}
	}

}