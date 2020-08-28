package io.github.scaamanho.sdr.repository;

import io.github.scaamanho.sdr.domain.RestDummy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestDummyRepository extends JpaRepository<RestDummy, String> {
	List<RestDummy> findByNameOrDescriptionContaining(String filter, String filter1);

	List<RestDummy> findByIdContainingIgnoreCaseOrNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String filter, String filter1, String filter2);
}
