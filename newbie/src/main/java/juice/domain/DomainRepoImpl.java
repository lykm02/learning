package juice.domain;

import java.util.Collections;
import java.util.List;

/**
 * Created by kmchu on 16/7/13.
 */
public class DomainRepoImpl implements  DomainRepo<Integer> {


    @Override
    public List<Integer> findByCritera() {
        return Collections.emptyList();
    }

    @Override
    public Integer findOne(Integer key) {
        return 0;
    }

    @Override
    public void describeSelf() {
        System.out.println("describe myself as domainRepoImpl<Integer>");
    }
}
