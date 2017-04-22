/*
 * Copyright (c) 2017, Michael Sonst, All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.sonsts.configuration.impl;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.log4j.Logger;

import de.sonsts.configuration.IConfiguration;

public class ConfigurationPersistence
{
    private static final Logger LOGGER = Logger.getLogger(ConfigurationPersistence.class.getName());
    
    public static <K extends IConfiguration> void writeConfiguration(K configuration, String configurationFile) throws FileNotFoundException
    {
        if (LOGGER.isTraceEnabled())
        {
            LOGGER.trace("ENTER configuration=" + configuration + ", configurationFile=" + configurationFile);
        }
        
        XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(configurationFile)));
        encoder.writeObject(configuration);
        encoder.close();
        
        if (LOGGER.isTraceEnabled())
        {
            LOGGER.trace("LEAVE");
        }
    }
    
    public static <K extends IConfiguration> K readConfiguration(String configurationFile)
    {
        if (LOGGER.isTraceEnabled())
        {
            LOGGER.trace("ENTER configurationFile=" + configurationFile);
        }
        
        K retVal = null;
        
        if (new File(configurationFile).exists())
        {
            FileInputStream fileInputStream = null;
            try
            {
                fileInputStream = new FileInputStream(configurationFile);
            }
            catch (FileNotFoundException e)
            {
                LOGGER.error("File not found." + e, e);
            }
            
            if (null != fileInputStream)
            {
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                XMLDecoder decoder = new XMLDecoder(bufferedInputStream);
                retVal = (K) decoder.readObject();
                
                decoder.close();
            }
        }
        
        if (LOGGER.isTraceEnabled())
        {
            LOGGER.trace("LEAVE retVal=" + retVal);
        }
        
        return retVal;
    }
}
