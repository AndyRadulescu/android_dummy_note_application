package com.dragoiu.nodebackup_backend.persistence;

import com.dragoiu.nodebackup_backend.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
