package com.tours.tourism.validators;

import org.apache.commons.beanutils.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PriceValidator implements ConstraintValidator<PriceValidate, Object> {
    private String firstFieldName;
    private String secondFieldName;

    @Override
    public void initialize(final PriceValidate constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        try {
            final double price = Double.parseDouble(BeanUtils.getProperty(value, firstFieldName));
            final double priceDiscount = Double.parseDouble(BeanUtils.getProperty(value, secondFieldName));

            return priceDiscount <= price;
        } catch (final Exception ignore) {
            // ignore
        }
        return true;
    }
}