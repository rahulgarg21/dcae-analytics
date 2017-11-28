/*
 * ===============================LICENSE_START======================================
 *  dcae-analytics
 * ================================================================================
 *    Copyright © 2017 AT&T Intellectual Property. All rights reserved.
 * ================================================================================
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  ============================LICENSE_END===========================================
 */

package org.onap.dcae.analytics.model.util;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.onap.dcae.analytics.model.BaseAnalyticsModelUnitTest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

/**
 * @author Rajiv Singla . Creation Date: 10/17/2016.
 */
public class AnalyticsModelIOUtilsTest extends BaseAnalyticsModelUnitTest {

    private static final String TEST_CONFIG_FILE_LOCATION = "data/json/config/testAppConfig.json";
    private static final String INVALID_TEST_CONFIG_FILE_LOCATION = "data/json/config/invalidJsonConfig.json";
    private static final String TEST_PROPERTIES_FILE_LOCATION = "data/properties/testApp.properties";

    @Test
    public void testConvertToJsonObjectWhenFileLocationIsValid() throws Exception {
        ConfigHolder configHolder =
                AnalyticsModelIOUtils.convertToJsonObject(TEST_CONFIG_FILE_LOCATION, ConfigHolder.class);
        String appName = configHolder.getConfig().getAppName();
        assertEquals("App Name must match with json settings file value", "TestAppName", appName);
        String appDescription = configHolder.getConfig().getAppDescription();
        assertEquals("App Description much with json settings file value", "Test App Description", appDescription);
    }

    @Test
    public void testConvertToJsonObjectWhenFileLocationIsInvValid() throws Exception {
        IOException invalidFileLocation = Assertions.assertThrows(IOException.class, () ->
                AnalyticsModelIOUtils.convertToJsonObject("InvalidFileLocation", ConfigHolder.class));
        Assertions.assertEquals("Invalid File location: InvalidFileLocation",
                invalidFileLocation.getMessage());

    }

    @Test
    public void testConvertToJsonObjectWhenJsonFileHasInvalidJson() throws Exception {
        IOException invalidJsonFile = Assertions.assertThrows(IOException.class, () ->
                AnalyticsModelIOUtils.convertToJsonObject(INVALID_TEST_CONFIG_FILE_LOCATION, ConfigHolder.class));
        Assertions.assertEquals(
                "Json parsing error while parsing Json File location: " + INVALID_TEST_CONFIG_FILE_LOCATION,
                invalidJsonFile.getMessage());
    }

    @Test
    public void testValidPropertiesFileLoading() throws Exception {
        final Properties properties =
                AnalyticsModelIOUtils.loadPropertiesFile(TEST_PROPERTIES_FILE_LOCATION, new Properties());
        assertThat("Properties File must contain 2 properties", properties.size(), is(2));
    }

    @Test
    public void testNonExistingPropertiesFileLoading() throws Exception {
        RuntimeException runtimeException = Assertions.assertThrows(RuntimeException.class,
                () -> AnalyticsModelIOUtils.loadPropertiesFile(
                        "InvalidPropertiesFileLocation", new Properties()));
        Assertions.assertEquals(
                "Invalid Properties File at location: InvalidPropertiesFileLocation",
                runtimeException.getMessage());
    }

    @Test
    public void testLoadPropertiesFileWhenIOException() throws Exception {
        final Properties mockProperties = Mockito.mock(Properties.class);
        doThrow(new IOException()).when(mockProperties).load(any(InputStream.class));
        RuntimeException runtimeException = Assertions.assertThrows(RuntimeException.class,
                () -> AnalyticsModelIOUtils.loadPropertiesFile(TEST_PROPERTIES_FILE_LOCATION, mockProperties));
        Assertions.assertEquals(
                "IO Exception while reading Properties File at location: " + TEST_PROPERTIES_FILE_LOCATION,
                runtimeException.getMessage());
    }
}

