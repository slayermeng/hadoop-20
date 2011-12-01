/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.hadoop.hdfs.server.datanode;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;

import org.apache.hadoop.hdfs.protocol.Block;
import org.apache.hadoop.hdfs.protocol.LocatedBlock;
import org.apache.hadoop.hdfs.server.datanode.DataNode.BlockRecord;
import org.apache.hadoop.hdfs.server.protocol.DatanodeProtocol;
import org.apache.hadoop.hdfs.server.protocol.DatanodeRegistration;
import org.apache.hadoop.hdfs.server.protocol.InterDatanodeProtocol;

interface NamespaceService extends Runnable {
  UpgradeManagerDatanode getUpgradeManager();
  DatanodeRegistration getNsRegistration();
  boolean isAlive();
  boolean initialized();
  void start();
  void join();
  void stop();
  void reportBadBlocks(LocatedBlock[] blocks) throws IOException;
  int getNamespaceId();
  InetSocketAddress getNNSocketAddress();
  DatanodeProtocol getDatanodeProtocol();
  void notifyNamenodeReceivedBlock(Block block, String delHint);
  void notifyNamenodeDeletedBlock(Block block);
  LocatedBlock syncBlock(Block block, List<BlockRecord> syncList,
      boolean closeFile, List<InterDatanodeProtocol> datanodeProxies)
      throws IOException;
  void scheduleBlockReport(long delay);
}
