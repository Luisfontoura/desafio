package Environment;
public class Client {
    public static String getEmail() {
        return System.getenv("EMAIL"); // Nome da variável de ambiente para o email
    }

    public static String getPassword() {
        return System.getenv("PASSWORD"); // Nome da variável de ambiente para a senha
    }
}