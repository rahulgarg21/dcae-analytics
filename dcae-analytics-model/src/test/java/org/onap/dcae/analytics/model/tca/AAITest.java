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

package org.onap.dcae.analytics.model.tca;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.onap.dcae.analytics.model.BaseAnalyticsModelUnitTest;
import org.onap.dcae.analytics.model.facade.tca.AAI;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Rajiv Singla . Creation Date: 12/16/2016.
 */
public class AAITest extends BaseAnalyticsModelUnitTest {

    private AAI aai;
    private static final String GENERIC_VNF_ID = "testVNFID";
    private static final String CHANGED_GENERIC_VNF_ID = "changedVNFID";

    @BeforeEach
    public void before() {
        aai = new AAI();
    }

    @Test
    @DisplayName("Confirm AAI Generic VNF ID setter method")
    public void getGenericVNFId() throws Exception {
        aai.setGenericVNFName(GENERIC_VNF_ID);
        assertThat("VNFID must be same", aai.getGenericVNFName(), is(GENERIC_VNF_ID));
    }

    @Test
    @DisplayName("Confirm Generic VNF Id can be changed")
    public void setGenericVNFId() throws Exception {
        final String genericVNFId = "testVNFID";
        aai.setGenericVNFName(GENERIC_VNF_ID);
        aai.setGenericVNFName(CHANGED_GENERIC_VNF_ID);
        assertThat("VNFID must be same as changed VNFID", aai.getGenericVNFName(), is(CHANGED_GENERIC_VNF_ID));
    }
}
