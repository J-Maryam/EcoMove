import config.DbFunctions;
import console.*;
import dao.ContractDao;
import dao.PartnerDao;
import dao.PromoDao;
import dao.TicketDao;
import models.entities.Partner;
import models.entities.Promotion;
import models.entities.Ticket;
import models.enums.PartnerStatus;
import models.enums.TransportType;
import services.ContractService;
import services.PartnerService;
import services.PromoService;
import services.TicketService;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        DbFunctions db = DbFunctions.getInstance();
        Connection connection = db.connectToDb("EcoMove", "postgres", "@aahmhmm28");

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