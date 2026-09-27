package VictorFagundes_ux.ApiCocaCola.Produtos.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}