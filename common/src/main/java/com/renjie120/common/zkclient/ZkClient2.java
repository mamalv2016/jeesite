package com.renjie120.common.zkclient;

import com.github.zkclient.ZkClient;
import com.renjie120.common.utils.JsonUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

/**
 * Created by Administrator on 2017/8/17.
 */
public class ZkClient2
{
    private ZkClient client;

    public ZkClient2(String zkServer)
    {
        client = new ZkClient(zkServer, 10000, 10000);
    }

    public void createNode(User user){
        String json = JsonUtils.toJsonStr(user);
        if(!client.exists("/usernode" + user.getId())){
            //创建一个节点
            client.create("/usernode" + user.getId(), json.getBytes(), CreateMode.PERSISTENT);
        }else{
            //修改一个节点
            client.writeData("/usernode" + user.getId(), json.getBytes());
        }
    }

    public User getNode(int userId){
        Stat stat = new Stat();
        byte[] bytes = client.readData("/usernode" + userId,stat);
        String strs = new String(bytes);
        return JsonUtils.parseJsonStr(strs,User.class);
    }
    public static void main(String[] args){
        ZkClient2 client = new ZkClient2("127.0.0.1:2181");
        User u = new User();
        u.setId(1);
        u.setName("李水清222");
        client.createNode(u);

        User u2 = client.getNode(1);
        System.out.println(u2.getName());
    }
}
