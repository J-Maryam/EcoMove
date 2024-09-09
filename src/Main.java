import config.DbFunctions;
import UI.*;
import dao.Implementations.ContractDao;
import dao.Implementations.PartnerDao;
import dao.Implementations.PromoDao;
import dao.Implementations.TicketDao;
import services.Implementations.ContractService;
import services.Implementations.PartnerService;
import services.Implementations.PromoService;
import services.Implementations.TicketService;

import java.sql.Connection;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        DbFunctions db = DbFunctions.getInstance();
        Connection connection = db.connectToDb("EcoMove-v2", "postgres", "@aahmhmm28");

        ContractDao contractDao = new ContractDao(connection);
        ContractService contractService = new ContractService(contractDao);

        PartnerDao partnerDao = new PartnerDao(connection);
        PartnerService partnerService = new PartnerService(partnerDao);

        TicketDao ticketDao = new TicketDao(connection);
        TicketService ticketService = new TicketService(ticketDao);

        PromoDao promoDao = new PromoDao(connection);
        PromoService promoService = new PromoService(promoDao);

        MainMenu mainMenu = new MainMenu(contractService, partnerService, ticketService, promoService);
        mainMenu.displayMainMenu();
    }
}