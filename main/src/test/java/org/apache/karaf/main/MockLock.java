package org.apache.karaf.main;

import org.apache.karaf.util.properties.Properties;
import java.util.logging.Logger;

import org.apache.karaf.main.lock.Lock;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
public class MockLock implements Lock {

    private boolean lock = true;
    private boolean isAlive = true; 
    private static final Logger LOG = Logger.getLogger(MockLock.class.getName());
    private Object lockLock = new Object();
    
    public MockLock(Properties props) {
    }
    
    public boolean lock() throws Exception {
        synchronized (lockLock) {
            LOG.fine("lock = " + lock);        
            lockLock.notifyAll();
        }
        return lock;
    }

    public void release() throws Exception {
        LOG.fine("release");
    }

    public boolean isAlive() throws Exception {
        LOG.fine("isAlive = " + isAlive);
        return isAlive;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }
    
    public void waitForLock() throws InterruptedException {
        synchronized (lockLock) {
            lockLock.wait(1000 * 60 * 5);
        }
    }
}
