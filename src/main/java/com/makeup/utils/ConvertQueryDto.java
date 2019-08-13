package com.makeup.utils;

@FunctionalInterface
public interface ConvertQueryDto<T> {
    T entityToQueryDto();
}