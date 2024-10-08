package App.controller;

import App.controller.validator.PersonValidator;
import App.controller.validator.UserValidator;
import App.dto.DetalinvoiceDto;
import App.dto.InvoiceDto;
import App.dto.PartnerDto;
import App.dto.PersonDto;
import App.dto.UserDto;
import App.service.interfaces.AdminService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@Setter
@Getter
@NoArgsConstructor
public class AdminController implements ControllerInterface {

    @Autowired
    private PersonValidator personValidator;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private AdminService service;

    private static final String MENU
            = "Ingrese la opción que desea:\n"
            + "1. Crear socio.\n"
            + "2. Ver factura club.\n"
            + "3. Ver factura socio.\n"
            + "4. Ver factura invitado.\n"
            + "5. Aprobar promoción.\n"
            + "6. Cerrar sesión.";

    public void session() throws Exception {
        Scanner scanner = Utils.getReader();
        boolean session = true;
        while (session) {
            session = menu(scanner);
        }
    }

    private boolean menu(Scanner scanner) {
        try {
            System.out.println(MENU);
            String option = scanner.nextLine();

            if (option.isEmpty()) {
                return false;
            }
            return options(option, scanner); // Pass the scanner object
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return true;
        }
    }

    private boolean options(String option, Scanner scanner) throws Exception {
        switch (option) {
            case "1": {
                this.createPartner(scanner);
                return true;
            }
            case "2": {
                this.viewClubInvoices(scanner);
                return true;
            }
            case "3": {
                this.viewPartnerInvoices(scanner);
                return true;
            }
            case "4": {
                this.viewGuestInvoices(scanner);
                return true;
            }
            case "5": {
                this.approvePromotion();
                return true;
            }
            case "6": {
                System.out.println("Se ha cerrado sesión");
                return false;
            }
            default: {
                System.out.println("Ingrese una opción válida");
                return true;
            }
        }
    }

    private void createPartner(Scanner scanner) throws Exception {
        System.out.print("Ingrese el nombre del socio: ");
        String name = scanner.nextLine();
        personValidator.validName(name);

        System.out.print("Ingrese la cédula del socio: ");
        String idCardInput = scanner.nextLine();
        long Document = personValidator.validDocument(idCardInput);

        System.out.print("Ingrese el nombre de usuario del socio: ");
        String username = scanner.nextLine();
        userValidator.validUsername(username);

        System.out.print("Ingrese la contraseña del socio: ");
        String password = scanner.nextLine();
        userValidator.validPassword(password);

        // Create the DTOs
        PersonDto personDto = new PersonDto();
        personDto.setName(name);
        personDto.setDocument(Document);
        personDto.setCelphone(3501235896L); // Assuming a default phone number
        UserDto userDto = new UserDto();
        userDto.setPersonId(personDto);
        userDto.setUsername(username);
        userDto.setPassword(password);
        userDto.setRol("Socio");

        PartnerDto partnerDto = new PartnerDto();
        partnerDto.setUserId(userDto);
        service.createPartner(partnerDto);

        System.out.println("¡Se ha creado el socio exitosamente!");
    }

    private void viewClubInvoices(Scanner scanner) {
        // Obtener todas las facturas del club
        List<InvoiceDto> invoices = service.getClubInvoices();

        // Mostrar las facturas al administrador
        StringBuilder message = new StringBuilder("Facturas del Club:\n\n");
        for (InvoiceDto invoice : invoices) {
            message.append(invoice.toString()).append("\n");
        }

        System.out.println(message.toString());

        // Agregar una pausa para que el usuario pueda ver las facturas
        System.out.println("Presiona Enter para continuar...");
        scanner.nextLine();
    }

    private void viewPartnerInvoices(Scanner scanner) throws Exception {
        System.out.print("Ingrese el ID del socio: ");
        String partnerIdInput = scanner.nextLine();
        long partnerId = Long.parseLong(partnerIdInput);

        List<InvoiceDto> invoices = service.getPartnerInvoices(partnerId);

        StringBuilder message = new StringBuilder("Facturas del Socio:\n\n");
        for (InvoiceDto invoice : invoices) {
            message.append(invoice.toString()).append("\n");
        }

        System.out.println(message.toString());

        // Agregar una pausa para que el usuario pueda ver las facturas
        System.out.println("Presiona Enter para continuar...");
        scanner.nextLine();
    }

    private void viewGuestInvoices(Scanner scanner) throws Exception {
        System.out.print("Ingrese el ID del invitado: ");
        String guestIdInput = scanner.nextLine();
        long guestId = Long.parseLong(guestIdInput);

        List<InvoiceDto> invoices = service.getGuestInvoices(guestId);

        StringBuilder message = new StringBuilder("Facturas del Invitado:\n\n");
        for (InvoiceDto invoice : invoices) {
            message.append(invoice.toString()).append("\n");
        }

        System.out.println(message.toString());

        // Agregar una pausa para que el usuario pueda ver las facturas
        System.out.println("Presiona Enter para continuar...");
        scanner.nextLine();
    }

    private void approvePromotion() throws Exception {
        List<PartnerDto> candidates = service.getVipCandidates();

        if (candidates.isEmpty()) {
            System.out.println("No hay candidatos disponibles para promoción a VIP.");
            return;
        }

        StringBuilder message = new StringBuilder("Candidatos a VIP:\n\n");
        for (PartnerDto candidate : candidates) {
            message.append(candidate.toString()).append("\n");
        }

        System.out.println(message.toString());

        System.out.print("¿Desea aprobar la promoción? (S/N): ");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();

        if (answer.equalsIgnoreCase("S")) {
            service.approveVipPromotion(candidates);
            System.out.println("Promoción a VIP aprobada.");
        } else {
            System.out.println("Promoción a VIP cancelada.");
        }
    }

    @Override
    public void login() {
        // Implementar la lógica de login o dejarlo como método vacío para evitar la excepción
    }

    public void facturacion(Scanner scanner) throws Exception {
        System.out.println("Iniciando proceso de facturación...");

        System.out.print("Ingrese el ID de la persona que realiza el consumo: ");
        String personIdInput = scanner.nextLine();
        long personId = Long.parseLong(personIdInput);

        System.out.print("Ingrese el ID del socio que paga el consumo: ");
        String partnerIdInput = scanner.nextLine();
        long partnerId = Long.parseLong(partnerIdInput);

        InvoiceDto invoiceDto = new InvoiceDto();
        invoiceDto.setCreacionDate(new Date()); 
        invoiceDto.setStatus(false); 

        UserDto userDto = new UserDto();
        userDto.setId(personId);  
        invoiceDto.setUserId(userDto);

        PartnerDto partnerDto = new PartnerDto();
        partnerDto.setId(partnerId);  
        invoiceDto.setPartnerId(partnerDto);

        List<DetalinvoiceDto> detallesFactura = new ArrayList<>();
        double totalFactura = 0.0;
        boolean agregarMasItems = true;
        int numeroItem = 1;

        while (agregarMasItems) {
            System.out.print("Ingrese el concepto del ítem consumido: ");
            String concepto = scanner.nextLine();

            System.out.print("Ingrese el valor del ítem: ");
            String valorItemInput = scanner.nextLine();
            double valorItem = Double.parseDouble(valorItemInput);

            DetalinvoiceDto detalle = new DetalinvoiceDto();
            detalle.setInvoiceid(invoiceDto); 
            detalle.setItem(numeroItem);  
            detalle.setDescription(concepto);  
            detalle.setAmount(valorItem);  

            detallesFactura.add(detalle);

            totalFactura += valorItem;
            numeroItem++;

            System.out.print("¿Desea agregar otro ítem? (S/N): ");
            String respuesta = scanner.nextLine();
            if (respuesta.equalsIgnoreCase("N")) {
                agregarMasItems = false;
            }
        }

        invoiceDto.setTotalAmount(totalFactura);
        System.out.println("Valor total de la factura: " + totalFactura);

        System.out.print("¿Desea pagar la factura ahora? (S/N): ");
        String respuestaPago = scanner.nextLine();
        if (respuestaPago.equalsIgnoreCase("S")) {
            invoiceDto.setStatus(true);
            System.out.println("La factura ha sido pagada con éxito.");
        } else {
            System.out.println("La factura quedará como pendiente de pago.");
        }

        service.createInvoice(invoiceDto, detallesFactura);

        System.out.println("Factura creada con éxito.");
    }
    
}
