package juice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import juice.domain.DomainModule;
import juice.domain.DomainQueryService;

/**
 * Created by kmchu on 16/7/13.
 */
public class GuiceMain {
    public static void main(String[] args){
        DomainModule domainModule = new DomainModule();
        Injector injector = Guice.createInjector(domainModule);
        DomainQueryService domainQueryService = injector.getInstance(DomainQueryService.class);
        System.out.println(domainQueryService.hashCode());
        for(int i=0;i<5;i++){
            System.out.println(i + " iterator is : " +injector.getInstance(DomainQueryService.class).hashCode() );
        }


    }
}
