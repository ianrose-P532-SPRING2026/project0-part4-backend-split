package edu.iu.habahram.ducksservice.repository;

import edu.iu.habahram.ducksservice.model.DuckData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DuckRepository extends JpaRepository<DuckData, Integer> {

    List<DuckData> findByTypeIgnoreCase(String type);

}