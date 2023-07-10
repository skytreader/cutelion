package net.skytreader.kode.cutelion.data.repository;

import net.skytreader.kode.cutelion.data.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query("SELECT id, name, defaultLanguage FROM Project")
    List<Project> fetchProjects();

    @Modifying
    @Query(value = "INSERT INTO projects (name, default_language) VALUES (:p.name, :p" +
            ".defaultLanguage)", nativeQuery = true)
    void addProject(@Param("p") Project p);
}
