import DAO.WorldDAO;

public class App {
    public static void main(String[] args) {
        WorldDAO worldDAO = new WorldDAO();

        System.out.println(worldDAO.getAll());
        System.out.println(worldDAO.getByName("San A%").size());

        System.out.println("\n By id: " + worldDAO.getById(4070));
        System.out.println("-------------------------\n" + worldDAO.getByCountryCode("pol"));

        System.out.println("-------------------------\n" + worldDAO.getByPopulationBetween(1000000, 1100000));


    }
}
