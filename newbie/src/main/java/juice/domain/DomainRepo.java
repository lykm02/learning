package juice.domain;

import java.util.List;

/**
 * Created by kmchu on 16/7/13.
 */
public interface DomainRepo <T>{

    public List<T> findByCritera();

    public T findOne(T key);

    public void describeSelf();
}
