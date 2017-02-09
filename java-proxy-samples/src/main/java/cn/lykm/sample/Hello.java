package cn.lykm.sample;

/**
 * Created by kmchu on 2017/2/6.
 */
public interface Hello {
    default void hello(){
        System.out.println("hello, default");
    }
}
