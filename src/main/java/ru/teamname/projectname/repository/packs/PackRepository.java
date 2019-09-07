package ru.teamname.projectname.repository.packs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.teamname.projectname.entity.packs.Pack;

@Repository
public interface PackRepository extends JpaRepository<Pack, Integer> {}
