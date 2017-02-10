package juice.domain;

import com.google.inject.Inject;

/**
 * Created by kmchu on 16/7/13.
 */
public class DomainQueryImpl implements DomainQueryService {
    @Inject
    private DomainRepo<Integer> repo;

    @Override
    public void run() {
        repo.describeSelf();
    }
}
