package jdk18;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by kmchu on 16/4/21.
 */
public class Lamda {
    public static void main(String[] args){
        List<IdEntity> entities = new LinkedList<IdEntity>();

        Random random = new Random(System.currentTimeMillis());

        for(int i=0;i<10;i++){
            entities.add(new IdEntity(random.nextInt()));
        }

        List<Integer> ids = entities.stream().filter(x->x!=null && x.id >0 ).collect(()-> new LinkedList<Integer>(),(list,item)->list.add(item.id),(list1, list2)->list1.addAll(list2));
        System.out.println(ids);
    }

    static class IdEntity{
        public int id;

        public IdEntity(){
            id = 1;
        }

        public IdEntity(int j){
            this.id = j;
        }
    }
}
