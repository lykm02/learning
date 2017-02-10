package validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

/**
 * Created by kmchu on 16/5/31.
 */
public class RequiredIfValidator implements ConstraintValidator<NotNullDependsOnOtherField,Object> {

    @Override
    public void initialize(NotNullDependsOnOtherField constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return false;
    }
}
