package net.skytreader.kode.cutelion.data.repository;

import net.skytreader.kode.cutelion.data.entity.InstallationConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface InstallationConfigRepository extends JpaRepository<InstallationConfig, Long> {

    @Query("SELECT key, value FROM InstallationConfig WHERE key in :keys")
    List<InstallationConfig> fetchConfig(@Param("keys") Collection<String> keys);
}
