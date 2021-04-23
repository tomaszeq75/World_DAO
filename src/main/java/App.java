import DAO.WorldDAO;

public class App {
    public static void main(String[] args) {
        WorldDAO worldDAO = new WorldDAO();

        System.out.println(worldDAO.getAll());
        System.out.println(worldDAO.getByName("San").size());

    }
}
