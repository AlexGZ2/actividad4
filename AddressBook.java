import java.io.*;
import java.util.*;

//Actividad 4 programa para ver contactos en un txt

public class AddressBook {
    private Map<String, String> contacts;
    private String fileName;

    public AddressBook(String fileName) {
        this.fileName = fileName;
        contacts = new HashMap<>();
    }

    public void loadContacts() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String number = parts[0].trim();
                    String name = parts[1].trim();
                    contacts.put(number, name);
                }
            }
            System.out.println("Contactos cargados exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al cargar los contactos: " + e.getMessage());
        }
    }

    public void saveContacts() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Map.Entry<String, String> entry : contacts.entrySet()) {
                String number = entry.getKey();
                String name = entry.getValue();
                writer.write(number + "," + name);
                writer.newLine();
            }
            System.out.println("Cambios guardados exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar los cambios: " + e.getMessage());
        }
    }

    public void listContacts() {
        System.out.println("Contactos:");
        for (Map.Entry<String, String> entry : contacts.entrySet()) {
            String number = entry.getKey();
            String name = entry.getValue();
            System.out.println(number + " : " + name);
        }
    }

    public void createContact(String number, String name) {
        contacts.put(number, name);
        System.out.println("Contacto creado exitosamente.");
    }

    public void deleteContact(String number) {
        contacts.remove(number);
        System.out.println("Contacto eliminado exitosamente.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AddressBook addressBook = new AddressBook("contacts.txt");

        // aqui se carga los contactos desde el txt
        addressBook.loadContacts();

        boolean exit = false;
        while (!exit) {
            System.out.println("\n---- Menú ----");
            System.out.println("1. Mostrar contactos");
            System.out.println("2. Crear contacto");
            System.out.println("3. Eliminar contacto");
            System.out.println("4. Guardar cambios");
            System.out.println("5. Salir");
            System.out.print("Elige una opción: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    addressBook.listContacts();
                    break;
                case 2:
                    System.out.print("Ingrese el número de teléfono: ");
                    String newNumber = scanner.nextLine();
                    System.out.print("Ingrese el nombre del contacto: ");
                    String newName = scanner.nextLine();
                    addressBook.createContact(newNumber, newName);
                    break;
                case 3:
                    System.out.print("Ingrese el número de teléfono para borrarlo: ");
                    String deleteNumber = scanner.nextLine();
                    addressBook.deleteContact(deleteNumber);
                    break;
                case 4:
                    addressBook.saveContacts();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Opción inválida. Inténtalo nuevamente.");
            }
        }
        scanner.close();
    }
}
