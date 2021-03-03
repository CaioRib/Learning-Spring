package project.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import project.entity.Aluno;
import project.request.AlunoPostResquestBody;
import project.response.AlunoGetResponseBody;
import project.service.AlunoService;
import project.util.AlunoCreator;
import project.util.AlunoGetResponseBodyCreator;
import project.util.AlunoPostRequestBodyCreator;

import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
public class AlunoControllerTest {

    @InjectMocks
    private AlunoController alunoController;

    @Mock
    private AlunoService alunoServiceMock;

    @BeforeEach
    void setup(){
        Aluno aluno1 = AlunoCreator.createAlunoToBeSaved("Name1", "name1@pagseguro.com");
        Aluno aluno2 = AlunoCreator.createAlunoToBeSaved("Name2", "name2@pagseguro.com");
        AlunoGetResponseBody alunoGet = AlunoGetResponseBodyCreator
                .createAlunoGetResponseBody("Name3", "name3@pagseguro.com");

        List<Aluno> alunoList = Arrays.asList(aluno1, aluno2);
        BDDMockito.when(alunoServiceMock.findAll())
                .thenReturn(alunoList);

        BDDMockito.when(alunoServiceMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(alunoGet);

        BDDMockito.when(alunoServiceMock.meanGrade(ArgumentMatchers.anyLong()))
                .thenReturn("Aprovado com nota 5.0");

        BDDMockito.when(alunoServiceMock.save(ArgumentMatchers.any()))
                .thenReturn(aluno1);
    }

    @Test
    @DisplayName("FindAll returns list of Aluno when successful")
    void findAll_ReturnsListOfAluno_WhenSuccessful(){
        List<Aluno> alunoList = alunoController.findAll().getBody();

        Assertions.assertThat(alunoList)
                .isNotNull()
                .hasSize(2);
    }

    @Test
    @DisplayName("FindById returns list of Aluno when successful")
    void findById_ReturnsAlunoGetResponseBody_WhenSuccessful(){
        AlunoGetResponseBody aluno = alunoController.findById(1L).getBody();

        AlunoGetResponseBody expectedAluno = AlunoGetResponseBodyCreator
                .createAlunoGetResponseBody("Name3", "name3@pagseguro.com");


        Assertions.assertThat(aluno).isNotNull();
        Assertions.assertThat(aluno.getNome()).isEqualTo(expectedAluno.getNome());
        Assertions.assertThat(aluno.getEmail()).isEqualTo(expectedAluno.getEmail());

    }

    @Test
    @DisplayName("meanGrade returns String when successful")
    void meanGrade_ReturnsString_WhenSuccessful(){
        String meanGrade = alunoController.meanGrade(1L).getBody();

        Assertions.assertThat(meanGrade)
                .isNotNull()
                .isNotEmpty();
    }

    @Test
    @DisplayName("save returns Aluno when successful")
    void save_ReturnsAluno_WhenSuccessful() {
        AlunoPostResquestBody alunoToBeSaved = AlunoPostRequestBodyCreator
                .createAlunoAlunoPostResquestBody("Name1", "name1@pagseguro.com");

        Aluno aluno = alunoController.save(alunoToBeSaved).getBody();

        Assertions.assertThat(alunoToBeSaved).isNotNull();
        Assertions.assertThat(alunoToBeSaved.getNome()).isEqualTo(aluno.getNome());
        Assertions.assertThat(alunoToBeSaved.getEmail()).isEqualTo(aluno.getEmail());
    }



}
