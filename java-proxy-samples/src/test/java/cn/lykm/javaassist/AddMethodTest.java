package cn.lykm.javaassist;

import cn.lykm.sample.HelloWord;
import javassist.*;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by kmchu on 2017/2/10.
 */
public class AddMethodTest {



    @Test
    public void testMethodAdded() throws Exception {
        final List<String> originMethods = getOriginMethods(HelloWord.class);
        Assert.assertNotEquals(originMethods.size(),0);

        List<String> newerMethods = getNewerMethods();
        System.out.println(newerMethods);
        List<String> addedMethods = newerMethods.stream().filter((m)-> !originMethods.contains(m)).collect(
            Collectors.toList());

        Assert.assertTrue(!addedMethods.isEmpty());
        Assert.assertEquals(addedMethods.get(0),"other");

        List<String> dupOriginMethods = getOriginMethods(HelloWord.class);
        Assert.assertTrue(dupOriginMethods.contains("other"));
    }

    private List<String> getOriginMethods(Class<?> clazz){
        Method[] methods = clazz.getDeclaredMethods();
        return Arrays.stream(methods).map((method)->method.getName()).collect(Collectors.toList());
    }


    private List<String> getNewerMethods() throws Exception{
        ClassPool classPool = ClassPool.getDefault();
   //     classPool.insertClassPath(new ClassClassPath(HelloWord.class));
        CtClass ctClass = classPool.get("cn.lykm.sample.HelloWord");

        CtMethod ctMethod = CtMethod.make("public int other(){return 1;}",ctClass);

        ctClass.addMethod(ctMethod);
        // cannot register into classloader for it already exists. It will throw Exception
        // if we force it to classloader
    //    ctClass.toClass(AddMethodTest.class.getClassLoader(),null);

        CtMethod[] methods = ctClass.getDeclaredMethods();
        List<String> methodList = new ArrayList<>();
        for(CtMethod method : methods){
            methodList.add(method.getName());
        }
        Collections.sort(methodList);

        return methodList;
    }
}
