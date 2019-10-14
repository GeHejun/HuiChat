package com.ghj.androidsdk;

import com.ghj.common.base.Constant;
import com.ghj.common.util.PropertiesUtil;
import com.ghj.common.util.ZookeeperUtil;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicInteger;
import org.apache.curator.retry.RetryNTimes;

import java.util.List;
import java.util.TreeMap;

/**
 * @author gehj
 * @version 1.0
 * @description TODO
 * @date 2019/10/14 13:30
 */
public class RoutingStrategy {


    public static String[] findBestServer() {
        String connect = PropertiesUtil.getInstance().getValue(Constant.ZOOKEEPER_CONNECT);
        CuratorFramework client = ZookeeperUtil.getInstance(connect);
        String path = Constant.SERVER_NODE;
        List<String> children = ZookeeperUtil.showChildren(client, path);
        TreeMap<Integer, String> nodeTreeMap = new TreeMap<>();
        children.forEach(node -> {
            String nodePath = path + node;
            DistributedAtomicInteger atomicInteger = new DistributedAtomicInteger(client, nodePath, new RetryNTimes(3, 1000));
            try {
                Integer connectNums = atomicInteger.get().postValue();
                nodeTreeMap.put(connectNums, node);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return nodeTreeMap.firstEntry().getValue().split(":");
    }
}
