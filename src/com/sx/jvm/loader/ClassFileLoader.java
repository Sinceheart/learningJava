package com.sx.jvm.loader;

import com.sun.deploy.util.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mj on 2017/9/11.
 */
public class ClassFileLoader {
    private List<String> clzPathList = new ArrayList<String>();

    public void addClassPath(String clzPath) {
        if (clzPathList.contains(clzPath))
            return;
        clzPathList.add(clzPath);
    }

    public String getClassPath() {
        String clzPathStr = "";
        for (String clzPath : clzPathList) {
            clzPathStr += clzPath + ";";
        }
        return StringUtils.join(clzPathList, ";");
    }

    public byte[] readBinaryCode(String className) {
        className = className.replace('.',File.separatorChar)+".class";
        for (String clzPah : clzPathList) {
           String clzFileName = clzPah + File.separatorChar + className;
            byte[] codes = loadClassFile(clzFileName);
            if(codes != null){
                return codes;
            }
        }
        return null;
    }

    private byte[] loadClassFile(String fileName) {
        BufferedInputStream in = null;
        byte[] content = null;
        try {
            in = new BufferedInputStream(new FileInputStream(fileName));
            ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
            int size = 0;
            byte[] temp = new byte[1024];
            while ((size = in.read(temp)) != -1) {
                out.write(temp, 0, size);
            }
            content = out.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }


}
