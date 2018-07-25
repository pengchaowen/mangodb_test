import com.mongodb.*;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

import java.util.ArrayList;

public class MangoDB {
    public static void main(String[] args) {

        /*
        final String uriString = "mongodb://$[username]:$[password]@$[hostlist]/$[database]?authSource=$[authSource]";
        MongoClientURI uri = new MongoClientURI(uriString);
        MongoClient mongoClient = new MongoClient(uri);
        */


        MongoClientOptions.Builder builder = MongoClientOptions.builder();
        /*
        builder.connectionsPerHost(10); //最大连接数
        builder.minConnectionsPerHost(5);//最小连接数
        builder.connectTimeout(1000 * 3); //超时时间
        builder.maxWaitTime(5000); // 一个线程成功获取到一个可用数据库之前的最大等待时间
        builder.threadsAllowedToBlockForConnectionMultiplier(5); //此参数跟connectionsPerHost的乘机为一个线程变为可用的最大阻塞数，超过此乘机数之后的所有线程将及时获取一个异常.eg.connectionsPerHost=10 and threadsAllowedToBlockForConnectionMultiplier=5,最多50个线程等级一个链接，推荐配置为5
        builder.maxConnectionIdleTime(1000 * 10); //最大空闲时间
        builder.maxConnectionLifeTime(1000 * 10); //设置池连接的最大生命时间。
        builder.socketTimeout(1000 * 10);//连接超时时间
        */
        MongoClientOptions myOptions = builder.build();

        ArrayList<ServerAddress> serverAddressList = new ArrayList();
        ServerAddress record = new ServerAddress("127.0.0.1");
        serverAddressList.add(record);

        MongoCredential credential = MongoCredential.createCredential("loger", "db_test", "apple".toCharArray());

        MongoClient mongoClient = new MongoClient(serverAddressList, credential, myOptions);

        MongoDatabase dbTest = mongoClient.getDatabase("db_test");

        String name = dbTest.getName();

        System.out.println("数据库名字：" + name);

        MongoIterable<String> it = dbTest.listCollectionNames();

        Block<String> printBlock = new Block<String>() {
            public void apply(final String document) {
                System.out.println(document);
            }
        };

        it.forEach(printBlock);




        // for (String str : strings) {

        // System.out.println("数据库表名：" + str);


        /*
        String ip = "127.0.0.1";
        int port = 27017;
        MongoClient mongoClient = new MongoClient(ip, port);
        // 大部分用户使用mongodb都在安全内网下，但如果将mongodb设为安全验证模式，就需要在客户端提供用户名和密码：
        // boolean auth = db.authenticate(myUserName, myPassword);
        MongoClientOptions.Builder options = new MongoClientOptions.Builder();
        options.cursorFinalizerEnabled(true);
        // options.autoConnectRetry(true);// 自动重连true
        // options.maxAutoConnectRetryTime(10); // the maximum auto connect retry time
        options.connectionsPerHost(300);// 连接池设置为300个连接,默认为100
        options.connectTimeout(30000);// 连接超时，推荐>3000毫秒
        options.maxWaitTime(5000); //
        options.socketTimeout(0);// 套接字超时时间，0无限制
        options.threadsAllowedToBlockForConnectionMultiplier(5000);// 线程队列数，如果连接线程排满了队列就会抛出“Out of semaphores to get db”错误。
        options.writeConcern(WriteConcern.SAFE);//
        options.build();*/

    }
}
