package generic;

import java.util.List;
import java.util.Map;

/**
 * Created by kmchu on 16/5/30.
 */
public class GenericPlay {

    public interface IdGetter{
        Integer getId();
    }


    public static <T extends IdGetter> Map<Integer, List<T>> convert(List<T> userList, List<T> secondList){

        return null;
    }
}
