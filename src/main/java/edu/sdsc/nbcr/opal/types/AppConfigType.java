/**
 * AppConfigType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package edu.sdsc.nbcr.opal.types;

public class AppConfigType  implements java.io.Serializable {
    private edu.sdsc.nbcr.opal.types.AppMetadataType metadata;

    private java.lang.String binaryLocation;

    private java.lang.String defaultArgs;

    private java.lang.Boolean validateArgs;

    private java.lang.String jobManagerFQCN;

    private org.apache.axis.types.URI globusGatekeeper;

    private org.apache.axis.types.URI gridftpBase;

    private boolean parallel;

    public AppConfigType() {
    }

    public AppConfigType(
           edu.sdsc.nbcr.opal.types.AppMetadataType metadata,
           java.lang.String binaryLocation,
           java.lang.String defaultArgs,
           java.lang.Boolean validateArgs,
           java.lang.String jobManagerFQCN,
           org.apache.axis.types.URI globusGatekeeper,
           org.apache.axis.types.URI gridftpBase,
           boolean parallel) {
           this.metadata = metadata;
           this.binaryLocation = binaryLocation;
           this.defaultArgs = defaultArgs;
           this.validateArgs = validateArgs;
           this.jobManagerFQCN = jobManagerFQCN;
           this.globusGatekeeper = globusGatekeeper;
           this.gridftpBase = gridftpBase;
           this.parallel = parallel;
    }


    /**
     * Gets the metadata value for this AppConfigType.
     * 
     * @return metadata
     */
    public edu.sdsc.nbcr.opal.types.AppMetadataType getMetadata() {
        return metadata;
    }


    /**
     * Sets the metadata value for this AppConfigType.
     * 
     * @param metadata
     */
    public void setMetadata(edu.sdsc.nbcr.opal.types.AppMetadataType metadata) {
        this.metadata = metadata;
    }


    /**
     * Gets the binaryLocation value for this AppConfigType.
     * 
     * @return binaryLocation
     */
    public java.lang.String getBinaryLocation() {
        return binaryLocation;
    }


    /**
     * Sets the binaryLocation value for this AppConfigType.
     * 
     * @param binaryLocation
     */
    public void setBinaryLocation(java.lang.String binaryLocation) {
        this.binaryLocation = binaryLocation;
    }


    /**
     * Gets the defaultArgs value for this AppConfigType.
     * 
     * @return defaultArgs
     */
    public java.lang.String getDefaultArgs() {
        return defaultArgs;
    }


    /**
     * Sets the defaultArgs value for this AppConfigType.
     * 
     * @param defaultArgs
     */
    public void setDefaultArgs(java.lang.String defaultArgs) {
        this.defaultArgs = defaultArgs;
    }


    /**
     * Gets the validateArgs value for this AppConfigType.
     * 
     * @return validateArgs
     */
    public java.lang.Boolean getValidateArgs() {
        return validateArgs;
    }


    /**
     * Sets the validateArgs value for this AppConfigType.
     * 
     * @param validateArgs
     */
    public void setValidateArgs(java.lang.Boolean validateArgs) {
        this.validateArgs = validateArgs;
    }


    /**
     * Gets the jobManagerFQCN value for this AppConfigType.
     * 
     * @return jobManagerFQCN
     */
    public java.lang.String getJobManagerFQCN() {
        return jobManagerFQCN;
    }


    /**
     * Sets the jobManagerFQCN value for this AppConfigType.
     * 
     * @param jobManagerFQCN
     */
    public void setJobManagerFQCN(java.lang.String jobManagerFQCN) {
        this.jobManagerFQCN = jobManagerFQCN;
    }


    /**
     * Gets the globusGatekeeper value for this AppConfigType.
     * 
     * @return globusGatekeeper
     */
    public org.apache.axis.types.URI getGlobusGatekeeper() {
        return globusGatekeeper;
    }


    /**
     * Sets the globusGatekeeper value for this AppConfigType.
     * 
     * @param globusGatekeeper
     */
    public void setGlobusGatekeeper(org.apache.axis.types.URI globusGatekeeper) {
        this.globusGatekeeper = globusGatekeeper;
    }


    /**
     * Gets the gridftpBase value for this AppConfigType.
     * 
     * @return gridftpBase
     */
    public org.apache.axis.types.URI getGridftpBase() {
        return gridftpBase;
    }


    /**
     * Sets the gridftpBase value for this AppConfigType.
     * 
     * @param gridftpBase
     */
    public void setGridftpBase(org.apache.axis.types.URI gridftpBase) {
        this.gridftpBase = gridftpBase;
    }


    /**
     * Gets the parallel value for this AppConfigType.
     * 
     * @return parallel
     */
    public boolean isParallel() {
        return parallel;
    }


    /**
     * Sets the parallel value for this AppConfigType.
     * 
     * @param parallel
     */
    public void setParallel(boolean parallel) {
        this.parallel = parallel;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AppConfigType)) return false;
        AppConfigType other = (AppConfigType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.metadata==null && other.getMetadata()==null) || 
             (this.metadata!=null &&
              this.metadata.equals(other.getMetadata()))) &&
            ((this.binaryLocation==null && other.getBinaryLocation()==null) || 
             (this.binaryLocation!=null &&
              this.binaryLocation.equals(other.getBinaryLocation()))) &&
            ((this.defaultArgs==null && other.getDefaultArgs()==null) || 
             (this.defaultArgs!=null &&
              this.defaultArgs.equals(other.getDefaultArgs()))) &&
            ((this.validateArgs==null && other.getValidateArgs()==null) || 
             (this.validateArgs!=null &&
              this.validateArgs.equals(other.getValidateArgs()))) &&
            ((this.jobManagerFQCN==null && other.getJobManagerFQCN()==null) || 
             (this.jobManagerFQCN!=null &&
              this.jobManagerFQCN.equals(other.getJobManagerFQCN()))) &&
            ((this.globusGatekeeper==null && other.getGlobusGatekeeper()==null) || 
             (this.globusGatekeeper!=null &&
              this.globusGatekeeper.equals(other.getGlobusGatekeeper()))) &&
            ((this.gridftpBase==null && other.getGridftpBase()==null) || 
             (this.gridftpBase!=null &&
              this.gridftpBase.equals(other.getGridftpBase()))) &&
            this.parallel == other.isParallel();
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getMetadata() != null) {
            _hashCode += getMetadata().hashCode();
        }
        if (getBinaryLocation() != null) {
            _hashCode += getBinaryLocation().hashCode();
        }
        if (getDefaultArgs() != null) {
            _hashCode += getDefaultArgs().hashCode();
        }
        if (getValidateArgs() != null) {
            _hashCode += getValidateArgs().hashCode();
        }
        if (getJobManagerFQCN() != null) {
            _hashCode += getJobManagerFQCN().hashCode();
        }
        if (getGlobusGatekeeper() != null) {
            _hashCode += getGlobusGatekeeper().hashCode();
        }
        if (getGridftpBase() != null) {
            _hashCode += getGridftpBase().hashCode();
        }
        _hashCode += (isParallel() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AppConfigType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "AppConfigType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("metadata");
        elemField.setXmlName(new javax.xml.namespace.QName("", "metadata"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "AppMetadataType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("binaryLocation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "binaryLocation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("defaultArgs");
        elemField.setXmlName(new javax.xml.namespace.QName("", "defaultArgs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("validateArgs");
        elemField.setXmlName(new javax.xml.namespace.QName("", "validateArgs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("jobManagerFQCN");
        elemField.setXmlName(new javax.xml.namespace.QName("", "jobManagerFQCN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("globusGatekeeper");
        elemField.setXmlName(new javax.xml.namespace.QName("", "globusGatekeeper"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyURI"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gridftpBase");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gridftpBase"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyURI"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parallel");
        elemField.setXmlName(new javax.xml.namespace.QName("", "parallel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
