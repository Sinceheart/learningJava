package com.sx.jvm.test;

import com.sx.jvm.loader.ClassFileLoader;
import com.sx.jvm.util.Util;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Mj on 2017/9/11.
 */
public class ClassFileLoaderTest {
    //Clas文件的装载



    private static final String FULL_QUALIFIED_CLASS_NAME = "com/sx/jvm/test/EmployeeV1";

//    static String path1 = "C:\\Users\\liuxin\\git\\coding2017\\liuxin\\mini-jvm\\answer\\bin";
    static String path1 = "D:\\Code\\JustForFun\\coding2017\\learningJava\\out\\production\\learningJava";
    static String path2 = "C:\temp";

//    static ClassFile clzFile = null;
//    static {
//        ClassFileLoader loader = new ClassFileLoader();
//        loader.addClassPath(path1);
//        String className = "com.coderising.jvm.test.EmployeeV1";
//
//        clzFile = loader.loadClass(className);
//
//    }
    //支持简单的classpath的设置
    @Test
    public void testClassPath(){

        ClassFileLoader loader = new ClassFileLoader();
        loader.addClassPath(path1);
        loader.addClassPath(path2);

        String clzPath = loader.getClassPath();

        Assert.assertEquals(path1+";"+path2,clzPath);

    }
    //从文件系统读取一个文件，形成字节数组
    @Test
    public void testClassFileLength() {

        ClassFileLoader loader = new ClassFileLoader();
        loader.addClassPath(path1);

        String className = "com.sx.jvm.test.EmployeeV1";

        byte[] byteCodes = loader.readBinaryCode(className);

        // 注意：这个字节数可能和你的JVM版本有关系， 你可以看看编译好的类到底有多大
        Assert.assertEquals(1000, byteCodes.length);

    }

    //验证该文件的魔数
    @Test
    public void testMagicNumber(){
        ClassFileLoader loader = new ClassFileLoader();
        loader.addClassPath(path1);
        String className = "com.sx.jvm.test.EmployeeV1";
        byte[] byteCodes = loader.readBinaryCode(className);
        byte[] codes = new byte[]{byteCodes[0],byteCodes[1],byteCodes[2],byteCodes[3]};


        String acctualValue = Util.byteToHexString(codes);

        Assert.assertEquals("cafebabe", acctualValue);
    }
}
