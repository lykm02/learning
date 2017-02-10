package juice.inject;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Created by kmchu on 16/8/2.
 */
public class InjectMain {
    public static void main(String[] args){
        Injector injector = Guice.createInjector(new InjectModule());
        TopComposer topComposer = injector.getInstance(TopComposer.class);
        topComposer.display();
    }
}
