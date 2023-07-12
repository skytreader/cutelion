package net.skytreader.kode.cutelion.data.repository;

import jakarta.transaction.Transactional;
import net.skytreader.kode.cutelion.data.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Transactional
public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query("SELECT id, name, defaultLanguage FROM Project")
    List<Project> fetchProjects();

    @Query("SELECT id, name, defaultLanguage FROM Project WHERE id=:projectId")
    Project fetchProject(@Param("projectId") Integer projectId);

    @Modifying
    @Query(value = "INSERT INTO projects (name, default_language) VALUES (:p" +
            ".getName(), :p.getDefaultLanguage())", nativeQuery = true)
    void addProject(@Param("p") Project p);
}
