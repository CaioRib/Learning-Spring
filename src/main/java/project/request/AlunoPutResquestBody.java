package project.request;

import lombok.Data;

@Data
public class AlunoPutResquestBody {
    private Long id;
    private String nome;
    private String email;
    private String status;
}
