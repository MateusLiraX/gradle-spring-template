package application.dto;

public class CursoDTO {
    private Long id;
    private String nome;
    private String descricao;
    private int cargaHoraria;

    public CursoDTO(Long id, String nome, String descricao, int cargaHoraria) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.cargaHoraria = cargaHoraria;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }
}
