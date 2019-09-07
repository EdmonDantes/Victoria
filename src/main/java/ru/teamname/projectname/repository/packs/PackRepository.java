package ru.teamname.projectname.repository.packs;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.teamname.projectname.entity.packs.Pack;

public interface PackRepository extends JpaRepository<Pack, Integer> {}
