package VictorFagundes_ux.ApiCocaCola.Produtos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "produtos")
public class Produtos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @NotBlank
    @Column(nullable = false, unique = true, length = 50)
    private String codigo;

    @Setter
    @NotBlank
    @Column(nullable = false, length = 200)
    private String descricao;

    @Setter
    @NotNull
    @Min(1)
    @Column(name = "quantidade_por_caixa", nullable = false)
    private Integer quantidadePorCaixa;

    protected Produtos() {
    }

    public Produtos(
            String codigo,
            String descricao,
            Integer quantidadePorCaixa
    ) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.quantidadePorCaixa = quantidadePorCaixa;
    }

}