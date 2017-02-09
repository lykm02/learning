package cn.lykm.origin;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by kmchu on 2017/2/9.
 */
public class TestInvocationHandler  implements InvocationHandler {

    @Override public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(proxy,args);
    }
}
