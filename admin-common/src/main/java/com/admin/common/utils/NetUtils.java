package com.admin.common.utils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @description: 网络连接工具类
 * @author: Zhaotianyi
 * @time: 2021/11/1 9:58
 */
public class NetUtils {
    /**
     * 端口号本地是是否被占用
     *
     * @param port 端口号
     * @return
     */
    public static boolean isLoclePortUsing(int port) {
        boolean flag;
        flag = isPortUsing(port);
        return flag;
    }

    public static boolean isPortUsing(int port) {
        Socket s = new Socket();
        try {
            bindPort("0.0.0.0", port);
            bindPort(InetAddress.getLocalHost().getHostAddress(), port);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static void bindPort(String host, int port) throws Exception {
        Socket s = new Socket();
        s.bind(new InetSocketAddress(host, port));
        s.close();
    }


}
