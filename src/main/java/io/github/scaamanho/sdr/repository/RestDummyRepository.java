package io.github.scaamanho.sdr.repository;

import io.github.scaamanho.sdr.domain.RestDummy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestDummyRepository extends JpaRepository<RestDummy, Long> {
}
