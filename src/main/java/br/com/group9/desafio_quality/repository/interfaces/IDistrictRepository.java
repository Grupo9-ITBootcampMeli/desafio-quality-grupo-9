package br.com.group9.desafio_quality.repository.interfaces;

import org.springframework.stereotype.Repository;


@Repository
public interface IDistrictRepository<T> {
    T create(T t);

    T findByName(String districtName);

}
