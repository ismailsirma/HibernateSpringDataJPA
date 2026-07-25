package sirmam.springdatajpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sirmam.springdatajpa.domain.joinedtable.ElectricGuitar;

public interface ElectricGuitarRepository extends JpaRepository<ElectricGuitar, Long> {
}
