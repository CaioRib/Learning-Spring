package project.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import project.entity.Aluno;
import project.util.AlunoCreator;

import javax.validation.ConstraintViolationException;
import java.util.Optional;

@DataJpaTest
@DisplayName("Tests for Aluno Repository")
class AlunoRepositoryTest {

    @Autowired
    private AlunoRepository alunoRepository;

    @Test
    @DisplayName("Save persists aluno when Successful")
    void save_PersistsAluno_WhenSuccessful(){
        Aluno alunoToBeSaved = AlunoCreator.createAlunoToBeSaved("Name1", "name1@pagseguro.com");
        Aluno alunoSaved = this.alunoRepository.save(alunoToBeSaved);

        Assertions.assertThat(alunoSaved).isNotNull();
        Assertions.assertThat(alunoSaved.getId()).isNotNull();
        Assertions.assertThat(alunoSaved.getNome()).isEqualTo(alunoToBeSaved.getNome());
        Assertions.assertThat(alunoSaved.getEmail()).isEqualTo(alunoToBeSaved.getEmail());

    }

    @Test
    @DisplayName("Save updates aluno when Successful")
    void save_UpdatesAluno_WhenSuccessful(){
        Aluno alunoToBeSaved = AlunoCreator.createAlunoToBeSaved("Name2", "name2@pagseguro.com");
        Aluno alunoSaved = this.alunoRepository.save(alunoToBeSaved);
        alunoSaved.setNome("Nome3");
        Aluno alunoUpdated = this.alunoRepository.save(alunoSaved);

        Assertions.assertThat(alunoUpdated).isNotNull();
        Assertions.assertThat(alunoUpdated.getId()).isNotNull();
        Assertions.assertThat(alunoUpdated.getNome()).isEqualTo(alunoSaved.getNome());

    }

    @Test
    @DisplayName("Delete removes aluno when Successful")
    void delete_RemovesAluno_WhenSuccessful(){
        Aluno alunoToBeSaved = AlunoCreator.createAlunoToBeSaved("Name4", "name4@pagseguro.com");
        Aluno alunoSaved = this.alunoRepository.save(alunoToBeSaved);

        Aluno alunoToBeDeleted = this.alunoRepository.save(alunoSaved);
        alunoRepository.delete(alunoToBeDeleted);

        Optional<Aluno> alunoDeleted = this.alunoRepository.findById(alunoToBeDeleted.getId());

        Assertions.assertThat(alunoDeleted).isEmpty();
    }

    @Test
    @DisplayName("Save throws ConstraintViolationException when fields are invalid")
    void save_ThrowsConstraintsAluno_WhenFieldIsInvalid(){
        Aluno alunoToBeSaved = AlunoCreator.createAlunoToBeSaved("Name5", "name5");
        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.alunoRepository.save(alunoToBeSaved));

        Aluno alunoToBeSaved2 = AlunoCreator.createAlunoToBeSaved("", "name5@pagseguro.com");
        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.alunoRepository.save(alunoToBeSaved2));

    }
}