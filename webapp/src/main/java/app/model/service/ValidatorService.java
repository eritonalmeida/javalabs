package app.model.service;

import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.HibernateValidatorConfiguration;

public class ValidatorService<T> {

    private Validator validator;

    private Set<ConstraintViolation<T>> errors;

    public ValidatorService() {

        HibernateValidatorConfiguration configuration = Validation.byProvider(HibernateValidator.class).configure();
        ValidatorFactory factory = configuration.failFast(true)
                .buildValidatorFactory();

        validator = factory.getValidator();
    }

    public boolean hasError() {
        return (errors.size() > 0);
    }

    public String getError() {
        if (errors.size() > 0) {
            return errors.iterator().next().getMessage();
        }

        return null;
    }

    public void process(T entity) {
        errors = validator.validate(entity);
    }

}
