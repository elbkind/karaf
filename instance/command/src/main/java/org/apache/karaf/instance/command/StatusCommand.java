/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.karaf.instance.command;

import org.apache.karaf.instance.command.completers.InstanceCompleter;
import org.apache.karaf.instance.core.Instance;
import org.apache.karaf.shell.commands.Argument;
import org.apache.karaf.shell.commands.Command;
import org.apache.karaf.shell.commands.Completer;
import org.apache.karaf.shell.commands.Option;
import org.apache.karaf.shell.inject.Service;

@Command(scope = "instance", name = "status", description = "Check the current status of an instance.")
@Service
public class StatusCommand extends InstanceCommandSupport {

    @Argument(index = 0, name = "name", description = "The name of the instance", required = true, multiValued = false)
    @Completer(InstanceCompleter.class)
    private String name;

    protected Object doExecute() throws Exception {
        Instance instance = getExistingInstance(name);
        System.out.println(instance.getState());
        return null;
    }

}
