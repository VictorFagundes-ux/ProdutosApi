package VictorFagundes_ux.ApiCocaCola.Produtos.repository;

import VictorFagundes_ux.ApiCocaCola.Produtos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByLogin(String login);
}