package fr.cirad.gridengine.opalclient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.UnknownServiceException;
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

import org.apache.axis.types.URI;
import org.apache.log4j.Logger;

import edu.sdsc.nbcr.opal.AppServicePortTypeProxy;
import edu.sdsc.nbcr.opal.types.BlockingOutputType;
import edu.sdsc.nbcr.opal.types.FaultType;
import edu.sdsc.nbcr.opal.types.InputFileType;
import edu.sdsc.nbcr.opal.types.JobInputType;
import edu.sdsc.nbcr.opal.types.JobSubOutputType;
import edu.sdsc.nbcr.opal.types.StatusOutputType;

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
	//public static final int STATUS_ALL = 65535;
	  
	private static HashMap<String, AppServicePortTypeProxy> serviceProxies = new HashMap<String, AppServicePortTypeProxy>();
	
    public static void main(String args[]) throws Exception 
    {
    	invoke("sleep", "", new URI[0], null, 1, "guilhem.sempere@cirad.fr", false);

    	//invoke("gevalt", "-phasedGen -hwcutoff 0.0 -pedfile ALLELE.txt.ped", new URI[] { new URI("http://gohelle:8080/Haplophyle/uploaded/D411D5BBFD4E667008F4F668BFB6DC4E/ALLELE.txt.ped") }, null, true);    	
//    	invoke("sleeper", "20", new URI[0], 1, null, false);
    	//invoke("blastall", "-d \"/bank/musa_acuminata/Musa_acuminata.fa\" -i Carto_markers_jaune_10062010.fa -o Carto_markers_jaune_10062010.blast -p blastn", new URI[] { new URI("http://cc2-web1:8080/test/Carto_markers_jaune_10062010.fa"), new URI("http://cc2-web1:8080/test/context.txt") }, null, false);
    	//invoke("splittedBlast", "--q web --input liste_forward_reverse_primer.fa --program blastn --database \"/bank/musa_acuminata/Musa_acuminata.fa\" --output liste_forward_reverse_primer.blast --evalue 1e-10 --max_target_seq 3 --format 5 --directory .", new URI[] { new URI("http://cc2-web1:8080/test/liste_forward_reverse_primer.fa") }, null, false);
//    	invoke("blastn", "-db \"/bank/musa_acuminata/Musa_acuminata.fa\" -query liste_forward_reverse_primer.fa -out liste_forward_reverse_primer.blast", new URI[] { new URI("http://cc2-web1:8080/test/liste_forward_reverse_primer.fa"), new URI("http://cc2-web1:8080/test/context.txt") }, null, "guilhem.sempere@cirad.fr", false);
    	
		/* OPAL Call */
//		JobSubOutputType subOut = OpalJobInvoker.invokeNonBlocking("blastp", "-db \"/bank/greenphyl/ORYSA\" -evalue 1e-10 -query sample.fa -out blastp.out", new URI[] { new URI("http://cc2-web1:8080/opal2/test_data/blastp/sample.fa"), new URI("http://cc2-web1:8080/opal2/test_data/blastp/context.txt") }, 1, null);
//    	JobSubOutputType subOut = OpalJobInvoker.invokeNonBlocking("smartpca", "-k 791 -i exported.eigenstratgeno -a exported.snp -b exported.ind", new URI[] { new URI[] { new URI("http://cc2-web1:8080/opal2/test_data/smartpca/exported.eigenstratgeno"), new URI("http://cc2-web1:8080/opal2/test_data/smartpca/exported.snp"), new URI("http://cc2-web1:8080/opal2/test_data/smartpca/exported.ind"), new URI("http://cc2-web1:8080/opal2/test_data/smartpca/context.txt")) }, null, null);
    	JobSubOutputType subOut = OpalJobInvoker.invokeNonBlocking("assignment", 
    			"cattleV1_51050variants_1945individuals.snp cattleV1_51050variants_1945individuals.ind cattleV1_51050variants_1945individuals.eigenstratgeno variantSynonyms.txt codeConversion.txt to_assign.map 3ind_to_assign.ped 3 0.2", 
    			new URI[] { new URI("http://cc2-web1:8080/opal2/test_data/assignment/cattleV1_51050variants_1945individuals.snp"), new URI("http://cc2-web1:8080/opal2/test_data/assignment/cattleV1_51050variants_1945individuals.ind"), new URI("http://cc2-web1:8080/opal2/test_data/assignment/cattleV1_51050variants_1945individuals.eigenstratgeno"), new URI("http://cc2-web1:8080/opal2/test_data/assignment/variantSynonyms.txt"), new URI("http://cc2-web1:8080/opal2/test_data/assignment/codeConversion.txt"), new URI("http://cc2-web1:8080/opal2/test_data/assignment/to_assign.map"), new URI("http://cc2-web1:8080/opal2/test_data/assignment/3ind_to_assign.ped"), new URI("http://cc2-web1:8080/opal2/test_data/assignment/context.txt") },
    			null, null, null);
    	
    	StatusOutputType status = subOut.getStatus();

		int code = status.getCode();
		if (code != OpalJobInvoker.STATUS_PENDING && code != OpalJobInvoker.STATUS_ACTIVE && code != OpalJobInvoker.STATUS_DONE && code != OpalJobInvoker.STATUS_STAGE_IN && code != OpalJobInvoker.STATUS_STAGE_OUT)			    
		    throw new Exception("The execution of the OpalClient failed with the following error: " + status.getMessage() + ". You can consult output and error file here: " + status.getBaseURL() + "/stdout.txt, " + status.getBaseURL() + "/stderr.txt" );
		
		String jobID = subOut.getJobID();
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		
		StatusOutputType sot = null;
		while (sot == null || (sot.getCode() != OpalJobInvoker.STATUS_DONE && sot.getCode() != OpalJobInvoker.STATUS_FAILED))
		{
			sot = OpalJobInvoker.getJobStatus("smartpca", jobID);
			System.out.println(sdf.format(new Date()) + " | " + jobID + " -> " + sot.getCode());
			Thread.sleep(1000);
		}			
    }

    public static BlockingOutputType invokeBlocking(String sServiceName, String cmdArgs, URI[] inputDocs, String[] attachedFiles, Integer numProcs, String notificationEmail) throws IOException
    {
    	try
    	{
	    	return (BlockingOutputType) invoke(sServiceName, cmdArgs, inputDocs, attachedFiles, numProcs, notificationEmail, true);
		}
		catch (FaultType ft)
		{
			throw new IOException(ft.getMessage() == null ? ft.getMessage1() : ft.getMessage());
		}
    }
    
    public static JobSubOutputType invokeNonBlocking(String sServiceName, String cmdArgs, URI[] inputDocs, String[] attachedFiles, Integer numProcs, String notificationEmail) throws IOException
    {
    	try
    	{
    		return (JobSubOutputType) invoke(sServiceName, cmdArgs, inputDocs, attachedFiles, numProcs, notificationEmail, false);
    	}
    	catch (FaultType ft)
    	{
    		throw new IOException(ft.getMessage() == null ? ft.getMessage1() : ft.getMessage());
    	}
    }
	
	private static Serializable invoke(String sServiceName, String cmdArgs, URI[] inputDocs, String[] attachedFiles, Integer numProcs, String notificationEmail, boolean fBlocking) throws IOException
	{
		JobInputType in = new JobInputType();

		// initialize list of files
		Vector<InputFileType> inputFileVector = new Vector<InputFileType>();
		
		AppServicePortTypeProxy appServicePort = getServiceProxyByName(sServiceName);
		
		if (inputDocs != null)
			for (URI uri : inputDocs) {
				String[] splittedInputURL = uri.toString().split("/");
				InputFileType infile = new InputFileType();
				String sFileName = splittedInputURL[splittedInputURL.length - 1];
				infile.setName(sFileName);
				infile.setLocation(uri);
				inputFileVector.add(infile);			
			}

		if (attachedFiles != null) {
			for (int i = 0; i < attachedFiles.length; i++) {
				DataHandler dh = new DataHandler(new FileDataSource(attachedFiles[i]));
				InputFileType infile = new InputFileType();
				File f = new File(attachedFiles[i]);
				infile.setName(f.getName());
				infile.setAttachment(dh);
				inputFileVector.add(infile);
			}
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
	
	private static AppServicePortTypeProxy getServiceProxyByName(String sServiceName) throws UnknownServiceException
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
			throw new UnknownServiceException("No such service declared in properties file: " + sServiceName);
		}
	}
	
	public static StatusOutputType getJobStatus(String sServiceName, String sJobID) throws FaultType, RemoteException, UnknownServiceException
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