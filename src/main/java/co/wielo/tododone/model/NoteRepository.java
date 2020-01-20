package co.wielo.tododone.model;

import co.wielo.tododone.model.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long>
{
    Page<Note> findByDescriptionLikeAndDeletedFalse(String description, Pageable pageable);
    Page<Note> findByDescriptionLikeAndDeletedTrue(String description, Pageable pageable);
    Page<Note> findByDescriptionLikeAndDeletedFalseAndDoneFalse(String description, Pageable pageable);
    Page<Note> findByDescriptionLikeAndDeletedFalseAndDoneTrue(String description, Pageable pageable);
}
