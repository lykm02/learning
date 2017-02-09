package cn.lykm.javassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

/**
 * Created by kmchu on 2017/1/25.
 */
public class HelloWrapper {

    public static void main(String[] args) {
        ClassPool classPool = ClassPool.getDefault();
        try {
            CtClass ctClass = classPool.get("cn.lykm.sample.HelloWord");
            CtMethod[] methods = ctClass.getDeclaredMethods();
            for(CtMethod method : methods){
                System.out.println(method.getLongName() + " name is: " + method.getName() + " return type is: " + method.getGenericSignature());
            }

        } catch (NotFoundException e) {
            e.printStackTrace();
        }

    }
}
