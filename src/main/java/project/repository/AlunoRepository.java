package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.entity.Aluno;

public interface AlunoRepository extends JpaRepository <Aluno, Long> {
}
