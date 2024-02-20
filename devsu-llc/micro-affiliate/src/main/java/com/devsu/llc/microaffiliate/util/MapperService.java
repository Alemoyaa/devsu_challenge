package com.devsu.llc.microaffiliate.util;

import org.modelmapper.ModelMapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MapperService {
    private static final ModelMapper modelMapper = new ModelMapper();

    private MapperService() {}

    public static <D, T> D map(final T entidad, Class<D> claseSalida) {
        return modelMapper.map(entidad, claseSalida);
    }

    public static <D, T> List<D> mapAll(final Collection<T> listaEntidades, Class<D> claseSalida) {
        return listaEntidades.stream()
                .map(entidad -> map(entidad, claseSalida))
                .collect(Collectors.toList());
    }
}
