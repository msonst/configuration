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

import java.io.FileNotFoundException;

import org.apache.log4j.Logger;

import de.sonsts.configuration.IConfiguration;

public class DefaultConfiguration implements IConfiguration
{
    private static final Logger LOGGER = Logger.getLogger(DefaultConfiguration.class.getName());
    
    public static String FILE_NAME = DefaultConfiguration.class.getSimpleName() + ".cfg";
    
    public DefaultConfiguration()
    {
        if (LOGGER.isTraceEnabled())
        {
            LOGGER.trace("ENTER");
        }
        
        if (LOGGER.isTraceEnabled())
        {
            LOGGER.trace("LEAVE");
        }
    }
    
    public static IConfiguration createDefaultConfiguration()
    {
        if (LOGGER.isTraceEnabled())
        {
            LOGGER.trace("ENTER");
        }
        
        IConfiguration retVal = new DefaultConfiguration();
        
        if (LOGGER.isTraceEnabled())
        {
            LOGGER.trace("LEAVE retVal=" + retVal);
        }
        
        return retVal;
    }
    
    public static IConfiguration loadConfiguration() throws FileNotFoundException
    {
        if (LOGGER.isTraceEnabled())
        {
            LOGGER.trace("ENTER");
        }
        
        IConfiguration retVal = null;
        
        IConfiguration readConfiguration = ConfigurationPersistence.readConfiguration("./" + FILE_NAME);
        if (null == readConfiguration)
        {
            retVal = createDefaultConfiguration();
            storeConfiguration(retVal);
        }
        else
        {
            retVal = readConfiguration;
        }
        
        if (LOGGER.isTraceEnabled())
        {
            LOGGER.trace("LEAVE retVal=" + retVal);
        }
        
        return retVal;
    }
    
    public static void storeConfiguration(IConfiguration configuration) throws FileNotFoundException
    {
        if (LOGGER.isTraceEnabled())
        {
            LOGGER.trace("ENTER configuration=" + configuration);
        }
        
        ConfigurationPersistence.writeConfiguration(configuration, "./" + FILE_NAME);
        
        if (LOGGER.isTraceEnabled())
        {
            LOGGER.trace("LEAVE");
        }
    }
}
