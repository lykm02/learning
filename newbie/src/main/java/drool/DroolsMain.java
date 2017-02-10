package drool;

import org.kie.api.KieServices;

import java.io.File;

/**
 * Created by kmchu on 16/9/14.
 */
public class DroolsMain {

    public static void main(String[] args){
        KieServices kieService = KieServices.Factory.get();
        kieService.newKieBuilder(new File(""));


    }
}
