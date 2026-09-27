package VictorFagundes_ux.ApiCocaCola.Produtos.controller;

import VictorFagundes_ux.ApiCocaCola.Produtos.model.Produtos;
import VictorFagundes_ux.ApiCocaCola.Produtos.repository.ProdutosRepository;
import VictorFagundes_ux.ApiCocaCola.Produtos.service.ProdutosService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutosController {

    private final ProdutosRepository repository;
    private final ProdutosService service;

    public ProdutosController(ProdutosRepository repository, ProdutosService service) {
        this.repository = repository;
        this.service = service;
    }

    @GetMapping
    public List<Produtos> listar(
            @RequestParam(required = false) String busca
    ) {
        if (busca == null || busca.isBlank()) {
            return repository.findAll();
        }

        return repository
                .findByDescricaoContainingIgnoreCase(busca);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produtos> buscarPorId(
            @PathVariable Long id
    ) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<Produtos> buscarPorCodigo(@PathVariable String codigo) {
        Produtos produto = service.buscarPorCodigo(codigo);
        return ResponseEntity.ok(produto);
    }

    @PostMapping
    public ResponseEntity<Produtos> criar(
            @Valid @RequestBody Produtos produto
    ) {
        Produtos salvo = repository.save(produto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(salvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Long id
    ) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
