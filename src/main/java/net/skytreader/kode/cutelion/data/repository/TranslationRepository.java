package net.skytreader.kode.cutelion.data.repository;

import net.skytreader.kode.cutelion.data.entity.Translation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TranslationRepository extends JpaRepository<Translation,
        Long> {
}
