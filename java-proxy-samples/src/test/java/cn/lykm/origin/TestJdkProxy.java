package cn.lykm.origin;

import cn.lykm.sample.Hello;
import cn.lykm.sample.HelloWord;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by kmchu on 2017/2/9.
 */
public class TestJdkProxy {

    @Test
    public void testProxy(){
        //dynamic loop
        InvocationHandler handler = new TestInvocationHandler();
        Object object = Proxy.newProxyInstance(this.getClass().getClassLoader(),new Class[]{Hello.class},handler);
        Hello hi = (Hello) object;

        //hi.hello();
    }

    @Test
    public void testNormalProxy(){
        Hello hello = new HelloWord();
        InvocationHandler handler = new NormalInvocationHandler(hello);
        Object object = Proxy.newProxyInstance(this.getClass().getClassLoader(),new Class[]{Hello.class},handler);
        Hello hi = (Hello) object;
        hi.hello();
    }
}
