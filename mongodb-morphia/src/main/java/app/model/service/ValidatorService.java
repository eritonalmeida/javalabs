package app.model.service;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.ValidationExtension;

public class ValidatorService<T> {

    private final Validator validator;

    private Set<ConstraintViolation<T>> errors;

    public ValidatorService() {
        ValidatorFactory factory = new ValidationExtension(new Morphia())
                .getValidatorFactory();
        validator = factory.getValidator();
    }

    public boolean hasError() {
        return (errors.size() > 0);
    }

    public String getError() {
        if (errors.size() > 0) {
            return errors.iterator()
                    .next()
                    .getMessage();
        }

        return null;
    }

    public void process(T entity) {
        errors = validator.validate(entity);
    }
}
