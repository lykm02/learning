package jdk18;

import org.springframework.util.StopWatch;

import java.util.*;

/**
 * Created by kmchu on 16/5/17.
 */
public class CollectionRemoveTest {

    public static void test(){
        List<Integer>  id1 = new ArrayList<>(600000);
        List<Integer>  removedList = new ArrayList<>(300000);

        Random  random = new Random(System.currentTimeMillis());
        for (int i=0;i< 600000; i++){
             id1.add(random.nextInt());
        }

        for(int i=0;i< 300000;i++){
            removedList.add(random.nextInt());
        }

        StopWatch watch = new StopWatch();
        watch.start();
        id1.removeAll(removedList);
        watch.stop();
        //274324
        System.out.println(watch.getTotalTimeMillis());
    }

    public static void testRemoveAfterSort(){
        Set<Integer> id1 = new TreeSet<Integer>();
        List<Integer>  removedList = new ArrayList<>(300000);

        Random  random = new Random(System.currentTimeMillis());
        for (int i=0;i< 600000; i++){
            id1.add(random.nextInt());
        }
        StopWatch watch = new StopWatch();

        for(int i=0;i< 300000;i++){
            removedList.add(random.nextInt());
        }
        watch.start();
        Collections.sort(removedList);
        watch.stop();
        System.out.println("sort 300k data: " + watch.getTotalTimeMillis());
        watch.start();
        id1.removeAll(removedList);
        watch.stop();
        //274324
        System.out.println(watch.getTotalTimeMillis());
    }

    public  static void main(String[] args){
//        test();
        testRemoveAfterSort();
    }
}
