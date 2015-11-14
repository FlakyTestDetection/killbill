/*
 * Copyright 2014-2015 Groupon, Inc
 * Copyright 2014-2015 The Billing Project, LLC
 *
 * The Billing Project licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package org.killbill.billing.util.broadcast;

import javax.inject.Inject;

import org.joda.time.DateTime;
import org.killbill.billing.broadcast.BroadcastApi;
import org.killbill.billing.util.broadcast.dao.BroadcastDao;
import org.killbill.billing.util.broadcast.dao.BroadcastModelDao;

public class DefaultBroadcastApi implements BroadcastApi {

    private final BroadcastDao dao;

    @Inject
    public DefaultBroadcastApi(final BroadcastDao dao) {
        this.dao = dao;
    }

    @Override
    public void broadcast(final String serviceName, final String type, final String event, final DateTime createdDate, final String createdBy) {
        final BroadcastModelDao modelDao = new BroadcastModelDao(serviceName, type, event, createdDate, createdBy);
        dao.create(modelDao);
    }
}
