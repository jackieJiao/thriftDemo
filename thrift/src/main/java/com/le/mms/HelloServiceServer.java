package com.le.mms;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TBinaryProtocol.Factory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

import com.le.mms.HelloService.Processor;

/**

 * 启动thrift的java服务器端

 *

 * @author david

 *

 */

public class HelloServiceServer {

   

    public static void main(String[] args) {

        try {

            // 设置服务器端口

            TServerSocket serverTransport = new TServerSocket(9090);

            // 设置二进制协议工厂

            Factory protocolFactory = new TBinaryProtocol.Factory();

            //处理器关联业务实现

            Processor<HelloService.Iface> processor = new HelloService.Processor<HelloService.Iface>(new HelloServiceImpl());

            // 1. 使用单线程标准阻塞I/O模型

            TServer.Args simpleArgs = new TServer.Args(serverTransport);

            simpleArgs.processor(processor);

            simpleArgs.protocolFactory(protocolFactory);

            TServer server = new TSimpleServer(simpleArgs);

            // 2. 使用线程池服务模型

//            TThreadPoolServer.Args poolArgs = new TThreadPoolServer.Args(serverTransport);

//            poolArgs.processor(processor);

//            poolArgs.protocolFactory(protocolFactory);

//            TServer poolServer = new TThreadPoolServer(poolArgs);

//            poolServer.serve();

            System.out.println("开启thrift服务器，监听端口：9090");

            server.serve();

        } catch (TTransportException e) {

            e.printStackTrace();

        }

    }

}