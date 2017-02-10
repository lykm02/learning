package juice.inject;

import com.google.inject.Inject;

/**
 * Created by kmchu on 16/8/2.
 */
public class TopComposer {
    @Inject
    private  SubComposer subComposer;

    public void display(){
        System.out.println("displaying everything.");
        subComposer.act();
    }
}
