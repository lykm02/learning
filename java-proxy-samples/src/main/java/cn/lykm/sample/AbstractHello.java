package cn.lykm.sample;

/**
 * Created by kmchu on 2017/2/6.
 */
public abstract class AbstractHello implements Hello {

    @Override public void hello() {
        System.out.println("hello " + getName());
    }

    protected  abstract String getName();
}
