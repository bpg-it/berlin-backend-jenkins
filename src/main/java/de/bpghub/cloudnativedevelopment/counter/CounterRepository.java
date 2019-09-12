package de.bpghub.cloudnativedevelopment.counter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CounterRepository extends JpaRepository<Counter, String> {

    Counter findByName(String name);

}
