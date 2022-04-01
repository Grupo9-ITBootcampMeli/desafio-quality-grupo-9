package br.com.group9.desafio_quality.repository.interfaces;

import org.springframework.stereotype.Repository;

/**Interface implementada pela abstração na forma de Repositório de bairros responsável por gerenciar as leituras e
 * inserções de DistrictDTO (bairro).
 * @author Amanda Zara, André Veziane, Antônio Schappo, Guilherme Pereira, Joan Davi, Vinicius Clemente
 * @version 1.00
 * @since Release 01 da aplicação
 */
@Repository
public interface IDistrictRepository<T> {
    T create(T t);

    T findByName(String districtName);

}
