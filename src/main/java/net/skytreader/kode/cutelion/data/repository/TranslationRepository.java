package net.skytreader.kode.cutelion.data.repository;

import net.skytreader.kode.cutelion.data.entity.Project;
import net.skytreader.kode.cutelion.data.entity.Translation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TranslationRepository extends JpaRepository<Translation,
        Long> {

    List<Translation> findByLocaleAndProject(String locale, Project project);
}
