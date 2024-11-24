package puppy.code;

public class ObjetosEspacialesFactorySingleton {
    private static ObjetosEspacialesFactoryImpl instance;

    private ObjetosEspacialesFactorySingleton() {}

    public static ObjetosEspacialesFactoryImpl getInstance() {
        if (instance == null) {
            instance = new ObjetosEspacialesFactoryImpl();
        }
        return instance;
    }
}
