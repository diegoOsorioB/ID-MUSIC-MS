package MS_Clientes.Clientes.excepction;

public class Excepciones {

    public static class ClienteNotFoundException extends RuntimeException {
        public ClienteNotFoundException(int id) {
            super("No se encontro el cliente con id " + id);
        }
        public ClienteNotFoundException(String correo) {
            super("Cliente con correo " + correo + " no encontrado");
        }
    }

    public static class DatabaseException extends RuntimeException {
        public DatabaseException(String mensaje) {
            super(mensaje);
        }
    }

    public static class AuthException extends RuntimeException {
        public AuthException(String msg) {
            super(msg);
        }
    }

    public static class ValidationException extends RuntimeException {
        public ValidationException(String mensaje) {
            super(mensaje);
        }
    }

}
