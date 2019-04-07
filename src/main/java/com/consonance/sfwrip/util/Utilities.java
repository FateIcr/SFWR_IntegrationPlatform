package com.consonance.sfwrip.util;

import com.consonance.sfwrip.exception.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.FeatureDescriptor;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Utilities {


    private static String[] getNullPropertyNames(Object source) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
        return Stream.of(wrappedSource.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null)
                .toArray(String[]::new);
    }

    public static void copyProperties(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }

    public static <T> T fetch(Supplier<? extends Optional<T>> supplier) {
        return supplier.get().orElseThrow(NotFoundException::new);
    }
}
