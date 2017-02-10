package generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.TypeVariable;

/**
 * Created by kmchu on 16/7/8.
 */
public class TestGenericMain {
    public static void main(String[] args){
        Long id = 1L;
        System.out.println("class name  is :" + id.getClass());
        Class idType = id.getClass();
        String value = "for test";
        System.out.println(Long.class);
        System.out.println();


        System.out.println("It's for type parameters");
        System.out.println(idType.getTypeParameters().length);
        for(TypeVariable variable : idType.getTypeParameters()){
            System.out.println(variable);
        }
        System.out.println("END");

    }
}
