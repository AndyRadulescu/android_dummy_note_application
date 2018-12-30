package com.dragoiu.nodebackup_backend.Persistence;

import com.dragoiu.nodebackup_backend.Model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
