/*
 * Copyright 2010-2013 Ning, Inc.
 * Copyright 2014 Groupon, Inc
 * Copyright 2014 The Billing Project, LLC
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

package org.killbill.billing.util.glue;

import org.killbill.billing.DBTestingHelper;
import org.killbill.billing.GuicyKillbillTestWithEmbeddedDBModule;
import org.killbill.billing.api.TestApiListener;
import org.killbill.billing.platform.api.KillbillConfigSource;

public class TestUtilModuleWithEmbeddedDB extends TestUtilModule {

    public TestUtilModuleWithEmbeddedDB(final KillbillConfigSource configSource) {
        super(configSource);
    }

    @Override
    protected void configure() {
        super.configure();
        install(new GuicyKillbillTestWithEmbeddedDBModule(configSource));

        install(new AuditModule(configSource));
        install(new TagStoreModule(configSource));
        install(new CustomFieldModule(configSource));
        install(new NonEntityDaoModule(configSource));
        install(new GlobalLockerModule(DBTestingHelper.get().getInstance().getDBEngine(), configSource));

        bind(TestApiListener.class).asEagerSingleton();
    }
}
