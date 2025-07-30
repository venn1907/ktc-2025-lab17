package classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContactManagement {
    private List<Contact> contactList;

    public ContactManagement() {
        contactList = new ArrayList<>();
    }

    public void printContact(Contact contact) {
        System.out.printf("ID: %d; Name: %s; Phone: %s; Email: %s; Address: %s%n",
                contact.getId(), contact.getName(), contact.getPhone(), contact.getEmail(), contact.getAddress());
    }

    public boolean isExisted(int id) {
        return contactList.stream()
                .anyMatch(contact -> contact.getId() == id);
    }

    public boolean isExisted(String input) {
        return contactList.stream()
                .anyMatch(contact -> contact.getPhone().equals(input) || contact.getEmail().equals(input));
    }

    public void getContactList() {
        System.out.println("Danh sach lien he:");
        contactList.forEach(this::printContact);
    }

    public void getContact(String phone) {
        Optional<Contact> found = contactList.stream().filter(c -> c.getPhone().equals(phone)).findFirst();

        if (found.isPresent()) {
            printContact(found.get());
        } else {
            System.out.println("Khong tim thay lien he nay");
        }
    }

    public void addContact(String name, String phone, String email, String address) {
        int id = contactList.isEmpty() ? 0 : contactList.get(contactList.size() - 1).getId() + 1;

        Contact contact = new Contact(id, name, phone, email, address);
        contactList.add(contact);
        System.out.println("Da them lien he thanh cong");
    }

    public void updateContact(Contact contact) {
        Optional<Contact> found = contactList.stream().filter(c -> c.getId() == contact.getId()).findFirst();
        if (found.isPresent()) {
            Contact c = found.get();
            if (!"".equals(contact.getName())) {
                c.setName(contact.getName());
            }
            if (!"".equals(contact.getPhone())) {
                c.setPhone(contact.getPhone());
            }
            if (!"".equals(contact.getEmail())) {
                c.setEmail(contact.getEmail());
            }
            if (!"".equals(contact.getAddress())) {
                c.setAddress(contact.getAddress());
            }
            System.out.println("Da cap nhat lien he thanh cong");
        } else {
            System.out.println("Khong tim thay lien he nay");
        }
    }

    public void removeContact(int id) {
        boolean removed = contactList.removeIf(c -> c.getId() == id);

        if (removed) {
            System.out.println("Da xoa lien he thanh cong");
        } else {
            System.out.println("Khong tim thay lien he nay");
        }

    }

}
