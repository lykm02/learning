package jdk18;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by kmchu on 16/5/14.
 */
public class ConverterLambda {

    public static void main(String[] args){
        String[] array = "1,3,4".split(",");
        List<String> idStrArray = Arrays.asList(array);
        List<Integer> ids = idStrArray.stream().map(x->Integer.valueOf(x.trim())).collect(Collectors.toList());
        Stream<Integer> stream = Stream.of(1,2);
        List<Integer> whos = stream.collect(Collectors.toList());

        List<Pair>  pairList = new ArrayList<>();
        pairList.add(new Pair(0,1));
        pairList.add(new Pair(1,2));

        List<Integer> list = pairList.stream().map(pair -> {System.out.println(pair.first); return pair.second;}).collect(Collectors.toList());
        System.out.println(list);

        Integer first =2000;
        Integer next = null;
        System.out.println(first == next);
//        System.out.println(first.intValue() == next);
    }

    public static class Pair{
        public int first;
        public int second;

        public Pair(){

        }

        public Pair(int x,int y){
            this.first = x;
            this.second = y;
        }
    }
}
