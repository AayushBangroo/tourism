package com.tours.tourism.validators;

import org.apache.commons.beanutils.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object>
{
    private String firstFieldName;
    private String secondFieldName;

    @Override
    public void initialize(final FieldMatch constraintAnnotation)
    {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context)
    {
        try
        {
            final double firstObj = Double.parseDouble(BeanUtils.getProperty(value, firstFieldName));
            final double secondObj = Double.parseDouble(BeanUtils.getProperty(value, secondFieldName));

            return firstObj >= secondObj;
        }
        catch (final Exception ignore)
        {
            // ignore
        }
        return true;
    }
}