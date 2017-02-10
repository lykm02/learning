package jacksondiy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

/**
 * Created by kmchu on 16/4/21.
 */
public class JacksonDiy {

    public static void main(String [] args) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CASE);

        String value = mapper.writeValueAsString(plainBeanGenerator());
        System.out.println(value);
        value = mapper.writeValueAsString(plainBeanWithXettersGenerator());
        System.out.println(value);
    }

    static class PlainBean{

        public String namedString;

        public int value;

        public double totalPrice;

    }

    static class PlainBeanWithXetters{
        @JsonIgnore
        public String named_string;
        public double total_Price;

        public double getTotalPrice(){
            return this.total_Price;
        }
        public String getNamedString(){
            return this.named_string;
        }
    }

    static PlainBean plainBeanGenerator(){
        PlainBean bean = new PlainBean();
        bean.namedString = "whoknows";
        bean.value = 100;
        bean.totalPrice = 200.01d;
        return  bean;
    }

    static PlainBeanWithXetters plainBeanWithXettersGenerator(){
        PlainBeanWithXetters bean = new PlainBeanWithXetters();
        bean.named_string = "beanwithxetters";
        bean.total_Price = 100.32;
        return bean;
    }
}
