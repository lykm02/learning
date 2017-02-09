package cn.lykm.sample;

/**
 * Created by kmchu on 2017/1/24.
 */
public class HelloWord extends AbstractHello{


    @Override protected String getName() {
        return "word";
    }

    public <T> T who(T t){
        return t;
    }
}
