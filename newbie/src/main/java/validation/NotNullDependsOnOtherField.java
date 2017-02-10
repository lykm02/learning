package validation;

import javax.validation.Constraint;
import java.lang.annotation.*;

/**
 * Created by kmchu on 16/5/30.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = {RequiredIfValidator.class})
public @interface NotNullDependsOnOtherField {

    public String dependedFieldName() ;

}
