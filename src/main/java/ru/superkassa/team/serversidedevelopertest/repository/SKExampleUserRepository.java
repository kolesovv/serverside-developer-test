package ru.superkassa.team.serversidedevelopertest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.superkassa.team.serversidedevelopertest.entity.SKExampleUser;

@Repository
public interface SKExampleUserRepository extends CrudRepository<SKExampleUser, Integer> {

}
