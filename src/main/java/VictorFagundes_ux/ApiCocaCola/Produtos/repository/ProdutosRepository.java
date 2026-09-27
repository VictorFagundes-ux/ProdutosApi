package VictorFagundes_ux.ApiCocaCola.Produtos.repository;

import VictorFagundes_ux.ApiCocaCola.Produtos.model.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProdutosRepository
        extends JpaRepository<Produtos, Long> {

    Optional<Produtos> findByCodigo(String codigo);

    List<Produtos> findByDescricaoContainingIgnoreCase(
            String descricao
    );
}