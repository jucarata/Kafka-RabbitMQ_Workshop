package co.edu.icesi.persistence.repository;

import co.edu.icesi.persistence.model.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<Classes,Integer> {
}