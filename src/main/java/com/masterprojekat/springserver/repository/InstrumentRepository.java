package com.masterprojekat.springserver.repository;

import com.masterprojekat.springserver.model.Instrument;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface InstrumentRepository extends CrudRepository<Instrument, Integer> {
    Optional<Instrument> findByName(String name);
}
