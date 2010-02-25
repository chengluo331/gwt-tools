/**
 * Copyright 2010 Yaakov Chaikin (yaakov.chaikin@gmail.com) Licensed under the Apache
 * License, Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed
 * to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under the
 * License.
 */
package org.tbiq.gwt.tools.rpcservice.server;

import org.tbiq.gwt.tools.rpcservice.browser.RpcRequest;
import org.tbiq.gwt.tools.rpcservice.browser.RpcServiceException;
import org.tbiq.gwt.tools.rpcservice.browser.RpcService;
import org.tbiq.gwt.tools.rpcservice.browser.RpcResponse;

/**
 * RpcRequestHandler interface extends {@link RpcService} interface which
 * necessitates the implementing classes to implement the Command pattern
 * {@link RpcService#execute(org.tbiq.gwt.tools.rpcservice.browser.RpcRequest)}
 * method. In addition, it defines a method which allows some handler registry to figure
 * out if a particular handler is compatible with a given {@link RpcRequest} object.
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
public interface RpcRequestHandler<RpcRequestT extends RpcRequest<RpcResponseT>, RpcResponseT extends RpcResponse>
{
  /**
   * Examines the <code>rpcRequest</code> objects and decides if this handler compatible
   * with (i.e., able to execute) that particular {@link RpcRequest}. Returns
   * <code>true</code> if it is compatible, <code>false</code> otherwise.
   * 
   * @param rpcRequest RPC request to handle/execute.
   * @return <code>true</code> if this handler is compatible with (i.e., able to execute)
   *         <code>rpcRequest</code>, <code>false</code> otherwise.
   */
  public boolean isCompatibleWith(Class<?> rpcRequestClass);


  public RpcResponseT execute(RpcRequestT rpcRequest)
    throws RpcServiceException;
}