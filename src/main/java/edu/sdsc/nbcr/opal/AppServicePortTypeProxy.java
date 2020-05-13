package edu.sdsc.nbcr.opal;

public class AppServicePortTypeProxy implements edu.sdsc.nbcr.opal.AppServicePortType {
  private String _endpoint = null;
  private edu.sdsc.nbcr.opal.AppServicePortType appServicePortType = null;
  
  public AppServicePortTypeProxy() {
    _initAppServicePortTypeProxy();
  }
  
  public AppServicePortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initAppServicePortTypeProxy();
  }
  
  private void _initAppServicePortTypeProxy() {
    try {
      appServicePortType = (new edu.sdsc.nbcr.opal.AppServiceLocator()).getAppServicePort();
      if (appServicePortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)appServicePortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)appServicePortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (appServicePortType != null)
      ((javax.xml.rpc.Stub)appServicePortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public edu.sdsc.nbcr.opal.AppServicePortType getAppServicePortType() {
    if (appServicePortType == null)
      _initAppServicePortTypeProxy();
    return appServicePortType;
  }
  
  public edu.sdsc.nbcr.opal.types.AppMetadataType getAppMetadata(edu.sdsc.nbcr.opal.types.AppMetadataInputType getAppMetadataInput) throws java.rmi.RemoteException, edu.sdsc.nbcr.opal.types.FaultType{
    if (appServicePortType == null)
      _initAppServicePortTypeProxy();
    return appServicePortType.getAppMetadata(getAppMetadataInput);
  }
  
  public edu.sdsc.nbcr.opal.types.AppConfigType getAppConfig(edu.sdsc.nbcr.opal.types.AppConfigInputType getAppConfigInput) throws java.rmi.RemoteException, edu.sdsc.nbcr.opal.types.FaultType{
    if (appServicePortType == null)
      _initAppServicePortTypeProxy();
    return appServicePortType.getAppConfig(getAppConfigInput);
  }
  
  public edu.sdsc.nbcr.opal.types.JobSubOutputType launchJob(edu.sdsc.nbcr.opal.types.JobInputType launchJobInput) throws java.rmi.RemoteException, edu.sdsc.nbcr.opal.types.FaultType{
    if (appServicePortType == null)
      _initAppServicePortTypeProxy();
    return appServicePortType.launchJob(launchJobInput);
  }
  
  public edu.sdsc.nbcr.opal.types.BlockingOutputType launchJobBlocking(edu.sdsc.nbcr.opal.types.JobInputType launchJobBlockingInput) throws java.rmi.RemoteException, edu.sdsc.nbcr.opal.types.FaultType{
    if (appServicePortType == null)
      _initAppServicePortTypeProxy();
    return appServicePortType.launchJobBlocking(launchJobBlockingInput);
  }
  
  public edu.sdsc.nbcr.opal.types.StatusOutputType queryStatus(java.lang.String queryStatusInput) throws java.rmi.RemoteException, edu.sdsc.nbcr.opal.types.FaultType{
    if (appServicePortType == null)
      _initAppServicePortTypeProxy();
    return appServicePortType.queryStatus(queryStatusInput);
  }
  
  public edu.sdsc.nbcr.opal.types.JobStatisticsType getJobStatistics(java.lang.String getJobStatisticsInput) throws java.rmi.RemoteException, edu.sdsc.nbcr.opal.types.FaultType{
    if (appServicePortType == null)
      _initAppServicePortTypeProxy();
    return appServicePortType.getJobStatistics(getJobStatisticsInput);
  }
  
  public edu.sdsc.nbcr.opal.types.JobOutputType getOutputs(java.lang.String getOutputsInput) throws java.rmi.RemoteException, edu.sdsc.nbcr.opal.types.FaultType{
    if (appServicePortType == null)
      _initAppServicePortTypeProxy();
    return appServicePortType.getOutputs(getOutputsInput);
  }
  
  public byte[] getOutputAsBase64ByName(edu.sdsc.nbcr.opal.types.OutputsByNameInputType getOutputAsBase64ByNameInput) throws java.rmi.RemoteException, edu.sdsc.nbcr.opal.types.FaultType{
    if (appServicePortType == null)
      _initAppServicePortTypeProxy();
    return appServicePortType.getOutputAsBase64ByName(getOutputAsBase64ByNameInput);
  }
  
  public edu.sdsc.nbcr.opal.types.StatusOutputType destroy(java.lang.String destroyInput) throws java.rmi.RemoteException, edu.sdsc.nbcr.opal.types.FaultType{
    if (appServicePortType == null)
      _initAppServicePortTypeProxy();
    return appServicePortType.destroy(destroyInput);
  }
  
  
}