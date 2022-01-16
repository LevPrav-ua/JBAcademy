package banking;

public class Main {
    static enum Color {
        RED("red"), GREEN("GREEN"), BLUE("BLUE");
        private final String type;
        Color(String type) {
            this.type = type;
        }

        public String getType() {
            return  type;
        }
    }
    public static void main(String[] args) {
        String dataBaseName = ("-fileName".equals(args[0]) ? args[1] : "dataBase.s3db");
        BankingSystem bankingSystem = new BankingSystem(dataBaseName);
        bankingSystem.start();
    }
}