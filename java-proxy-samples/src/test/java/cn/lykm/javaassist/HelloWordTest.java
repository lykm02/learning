package cn.lykm.javaassist;

import cn.lykm.sample.HelloWord;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by kmchu on 2017/2/6.
 */
public class HelloWordTest {

    private List<String> methodList;

    @Before
    public void setup(){
        Method[] methods = HelloWord.class.getMethods();
        methodList = new LinkedList<>();
        for(Method method : methods){
            methodList.add(method.getName());
        }
        Collections.sort(methodList);
    }


    @Test
    public void listAllMethodByJavassit() throws Exception{
        ClassPool classPool = ClassPool.getDefault();
        CtClass ctClass = classPool.get("cn.lykm.sample.HelloWord");
        CtMethod[] methods = ctClass.getMethods();
        List<String> methodList = new ArrayList<>();
        for(CtMethod method : methods){
            methodList.add(method.getName());
        }
        Collections.sort(methodList);

        assertNotEquals(methodList.size(),this.methodList.size());
    }

    @Test
    public void listDeclaredMethodsByJavassit()throws Exception{
        ClassPool classPool = ClassPool.getDefault();
        CtClass ctClass = classPool.get("cn.lykm.sample.HelloWord");
        CtMethod[] methods = ctClass.getDeclaredMethods();
        List<String> methodList = new ArrayList<>();
        for(CtMethod method : methods){
            methodList.add(method.getName());
        }
        Collections.sort(methodList);

        Method[] originMethods = HelloWord.class.getDeclaredMethods();
        List originMethodList = new LinkedList<>();
        for(Method method : originMethods){
            originMethodList.add(method.getName());
        }
        Collections.sort(originMethodList);

        assertEquals(methodList.size(),originMethodList.size());
    }

    @After
    public void teardown(){
        methodList.clear();
    }

}
