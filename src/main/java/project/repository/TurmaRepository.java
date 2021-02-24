package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.entity.Turma;

public interface TurmaRepository extends JpaRepository <Turma, Long> {
}
