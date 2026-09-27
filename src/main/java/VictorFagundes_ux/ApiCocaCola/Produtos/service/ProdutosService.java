package VictorFagundes_ux.ApiCocaCola.Produtos.service;

import VictorFagundes_ux.ApiCocaCola.Produtos.model.Produtos;
import VictorFagundes_ux.ApiCocaCola.Produtos.repository.ProdutosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutosService {

    private final ProdutosRepository repository;

    public ProdutosService(ProdutosRepository repository) {
        this.repository = repository;
    }

    public List<Produtos> listarTodos() {
        return repository.findAll();
    }

    public Produtos buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Produto não encontrado"
                        )
                );
    }

    public Produtos salvar(Produtos produto) {
        return repository.save(produto);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }

    public List<Produtos> buscarPorDescricao(String descricao) {
        return repository
                .findByDescricaoContainingIgnoreCase(descricao);
    }
}

