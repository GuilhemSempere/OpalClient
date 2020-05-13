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

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

public class OpalClientConfig
{
	private static final Logger LOG = Logger.getLogger(OpalClientConfig.class);

	private static ResourceBundle bundle;
	
	static {
		try {
			bundle = ResourceBundle.getBundle("OpalClient");
		}
		catch (MissingResourceException mre) {
			LOG.warn(mre.getMessage() + " ; attempting to load properties file from deprecated location: fr.cirad.gridengine.opalclient.TextConstants");
			bundle = ResourceBundle.getBundle("fr.cirad.gridengine.opalclient.TextConstants");
		}
	}
	
	public static String get(String sLookupID)
	{
		return bundle.getString(sLookupID);
	}
}
