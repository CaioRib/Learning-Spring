package project.request;

import lombok.Data;

import javax.persistence.*;

@Data
public class TurmaPostRequestBody {
    private Long id;
    private String nome;
}
