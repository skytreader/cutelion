package net.skytreader.kode.cutelion.data.repository;

import net.skytreader.kode.cutelion.data.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query("SELECT p FROM Project p ORDER BY p.lastEntryAddedAt DESC, p" +
            ".modifiedAt DESC LIMIT 10")
    List<Project> fetchDashboard();
}
