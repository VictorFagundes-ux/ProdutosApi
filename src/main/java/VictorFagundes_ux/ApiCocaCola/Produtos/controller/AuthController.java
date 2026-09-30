package VictorFagundes_ux.ApiCocaCola.Produtos.controller;

import VictorFagundes_ux.ApiCocaCola.Produtos.dto.LoginDTO;
import VictorFagundes_ux.ApiCocaCola.Produtos.dto.TokenResponseDTO;
import VictorFagundes_ux.ApiCocaCola.Produtos.model.Usuario;
import VictorFagundes_ux.ApiCocaCola.Produtos.security.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody LoginDTO login) {
        // Valida as credenciais no banco de dados e checa a senha criptografada
        var usernamePassword = new UsernamePasswordAuthenticationToken(login.usuario(), login.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        // Se a validação passar, gera o token JWT com o usuário logado
        var token = tokenService.gerarToken(((Usuario) auth.getPrincipal()).getUsername());

        return ResponseEntity.ok(new TokenResponseDTO(token));
    }
}