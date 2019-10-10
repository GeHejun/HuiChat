package com.ghj.common.util;

import io.netty.util.internal.StringUtil;
import org.apache.commons.io.Charsets;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.*;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.data.Stat;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * zookeeper util
 *
 * @author GeHejun
 */
public class ZookeeperUtil {


    private static final Integer SESSION_TIMEOUT = 50000;
    private volatile static CuratorFramework client;


    public static CuratorFramework getInstance(String zkAddress) {
        if (client == null) {
            synchronized (CuratorFramework.class) {
                if (client == null) {
                    client = CuratorFrameworkFactory.builder().connectString(zkAddress).sessionTimeoutMs(SESSION_TIMEOUT)
                            .connectionTimeoutMs(SESSION_TIMEOUT).canBeReadOnly(true)
                            .retryPolicy(new ExponentialBackoffRetry(10000, 3)).build();
                    client.start();
                }
            }
        }
        return client;
    }


    /**
     * 创建节点
     *
     * @param nodeName
     * @param value
     * @return
     */
    public static boolean createNode(CuratorFramework client, String nodeName, String value) {
        boolean isSuccessFlag = false;
        try {
            Stat stat = client.checkExists().forPath(nodeName);
            if (stat == null) {
                String opResult;
                if (StringUtils.isEmpty(value)) {
                    opResult = client.create().creatingParentsIfNeeded().forPath(nodeName);
                } else {
                    // 不为空就设置节点的数据值
                    opResult = client.create().creatingParentsIfNeeded().forPath(nodeName, value.getBytes(Charsets.UTF_8));
                }
                isSuccessFlag = Objects.equals(nodeName, opResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccessFlag;

    }


    /**
     * 更新节点
     *
     * @param path
     * @param value
     * @return
     */
    public static boolean updateNode(CuratorFramework client, String path, String value) {
        boolean isSuccessFlag = false;
        try {
            Stat stat = client.checkExists().forPath(path);
            if (!Objects.isNull(stat)) {
                Stat returnResult = client.setData().forPath(path, value.getBytes(Charsets.UTF_8));
                if (!Objects.isNull(returnResult)) {
                    isSuccessFlag = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccessFlag;
    }


    /**
     * 删除节点
     *
     * @param path
     */

    public static void delNode(CuratorFramework client, String path) {
        try {
            client.delete().deletingChildrenIfNeeded().forPath(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取指定节点下的所有子节点的名称与值
     *
     * @param path
     * @return
     */

    public static Map<String, String> showChildrenDetail(CuratorFramework client, String path) {
        Map<String, String> nodeMap = new HashMap<>(16);
        try {
            GetChildrenBuilder childrenBuilder = client.getChildren();
            List<String> childrenList = childrenBuilder.forPath(path);
            GetDataBuilder dataBuilder = client.getData();
            if (!CollectionUtils.isEmpty(childrenList)) {
                childrenList.forEach(item -> {
                    String propPath = ZKPaths.makePath(path, item);
                    try {
                        nodeMap.put(item, new String(dataBuilder.forPath(propPath), Charsets.UTF_8));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nodeMap;
    }


    /**
     * 列出节点下所有的子节点，但是不带子节点的数据
     *
     * @param path
     * @return
     */
    public static List<String> showChildren(CuratorFramework client, String path) {
        List<String> children = new ArrayList<>();
        try {
            GetChildrenBuilder childrenBuilder = client.getChildren();
            children = childrenBuilder.forPath(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return children;
    }


    /**
     * 对节点则增加监听，flag如果是true，就对节点本身监听，false是该节点的子节点增加监听
     *
     * @param path
     * @param flag
     * @throws Exception
     */
    public static void addWatch(CuratorFramework client, String path, boolean flag) throws Exception {
        if (flag) {
            client.getData().watched().forPath(path);
        } else {
            client.getChildren().watched().forPath(path);
        }
    }


    /**
     * 销毁资源
     */

    public static void destory() {
        if (client != null) {
            client.close();
        }
    }


    /**
     * 对节点则增加监听，flag如果是true，就对节点本身监听，false是该节点的子节点增加监听
     *
     * @param path
     * @param flag
     * @param watcher 监视节点
     * @throws Exception
     */

    public static void addWatch(CuratorFramework client, String path, boolean flag, CuratorWatcher watcher) throws Exception {
        if (flag) {
            client.getData().usingWatcher(watcher).forPath(path);
        } else {
            client.getChildren().usingWatcher(watcher).forPath(path);
        }

    }


    final class ZKNodeEventListener implements CuratorListener {
        @Override
        public void eventReceived(CuratorFramework client, CuratorEvent event) {
            System.out.println(event.toString() + "............");
            final WatchedEvent watchEvent = event.getWatchedEvent();
            if (!Objects.isNull(watchEvent)) {
                System.out.println(watchEvent.getState() + "----------" + watchEvent.getType());
                if (watchEvent.getState() == Watcher.Event.KeeperState.SyncConnected) {
                    switch (watchEvent.getType()) {
                        case NodeChildrenChanged:
                            break;
                        case NodeDataChanged:
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }



     class ZKNodeWather implements CuratorWatcher {
        @Override
        public void process(WatchedEvent event)  {
            if (event.getType() == Watcher.Event.EventType.NodeChildrenChanged) {
                System.out.println("监视节点");
            }
        }
    }
}
