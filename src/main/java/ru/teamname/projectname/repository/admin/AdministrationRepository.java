package ru.teamname.projectname.repository.admin;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.teamname.projectname.entity.admin.Administrator;

@Service
@Repository
@Transactional
public interface AdministrationRepository extends CrudRepository<Administrator, Integer> {

    @Query("select admin from Administrator admin where admin.login = :login")
    public Administrator getAdministratorByLogin(@Param("login") String login);

}