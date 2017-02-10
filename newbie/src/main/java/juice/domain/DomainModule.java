package juice.domain;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

/**
 * Created by kmchu on 16/7/13.
 */
public class DomainModule extends AbstractModule {

    public DomainModule(){

    }

    @Override
    protected void configure() {
        bind(new TypeLiteral<DomainRepo<Integer>>(){}).toInstance(new DomainRepoImpl());
        bind(DomainQueryService.class).to(DomainQueryImpl.class);
    }
}
