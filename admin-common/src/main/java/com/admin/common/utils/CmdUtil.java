package com.admin.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: Cmd执行工具
 * @author: Zhaotianyi
 * @time: 2021/10/25 10:56
 */
public class CmdUtil {
    /**
     * 执行Cmd命令 返回执行结果字符串列表
     *
     * @param command
     * @return
     * @throws IOException
     */
    public static List<String> executeCommandAndGetLines(String command) throws IOException {
        ArrayList<String> stringList = new ArrayList<>();

        try {
            Process process = Runtime.getRuntime().exec(command);
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line = bufferedReader.readLine();
                while (line != null) {
                    stringList.add(line);
                    line = bufferedReader.readLine();
                }
            }
            process.destroy();
        } catch (IOException ioE) {
            throw ioE;
        }

        return stringList;
    }
}
