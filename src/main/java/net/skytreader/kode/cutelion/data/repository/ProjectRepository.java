package net.skytreader.kode.cutelion.data.repository;

import net.skytreader.kode.cutelion.data.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query(value = "SELECT * FROM projects p ORDER BY p.last_entry_added_at " +
            "DESC NULLS LAST, p.modified_at DESC  NULLS LAST LIMIT 10",
            nativeQuery = true)
    List<Project> fetchDashboard();
}
