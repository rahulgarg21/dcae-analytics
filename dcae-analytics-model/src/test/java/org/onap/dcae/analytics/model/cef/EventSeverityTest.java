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

package org.onap.dcae.analytics.model.cef;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.onap.dcae.analytics.model.domain.cef.EventSeverity;
import org.onap.dcae.analytics.test.BaseDCAEAnalyticsUnitTest;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Rajiv Singla . Creation Date: 11/10/2016.
 */
public class EventSeverityTest extends BaseDCAEAnalyticsUnitTest {

    @Test
    @DisplayName("Confirm Event Severity Ordering")
    public void testEventSeverityOrdering() throws Exception {

        List<EventSeverity> eventSeverities = new LinkedList<>();
        Collections.addAll(eventSeverities,
                EventSeverity.NORMAL,
                EventSeverity.WARNING,
                EventSeverity.MINOR,
                EventSeverity.MAJOR,
                EventSeverity.CRITICAL);

        Collections.sort(eventSeverities);

        List<EventSeverity> expectedEventSeverities = new LinkedList<>();
        Collections.addAll(expectedEventSeverities,
                EventSeverity.CRITICAL,
                EventSeverity.MAJOR,
                EventSeverity.MINOR,
                EventSeverity.WARNING,
                EventSeverity.NORMAL
        );

        Assertions.assertTrue(eventSeverities.equals(expectedEventSeverities),
                "Severity Order must be CRITICAL, MAJOR, MINOR, WARNING, NORMAL");

    }
}
