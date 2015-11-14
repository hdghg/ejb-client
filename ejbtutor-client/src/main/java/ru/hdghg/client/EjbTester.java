package ru.hdghg.client;

import ru.hdghg.stateless.LibrarySessionBeanRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.*;
import java.util.Hashtable;
import java.util.List;

public class EjbTester {

    InitialContext context;
    {
        final Hashtable<String, String> jndiProperties = new Hashtable<>();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        try {
            context = new InitialContext(jndiProperties);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        EjbTester ejbTester = new EjbTester();
        ejbTester.test();
    }

    private void test() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));) {
            int choice = 1;
            LibrarySessionBeanRemote librararySessionBeanRemote =
                (LibrarySessionBeanRemote) context.lookup(
                        "ejb:/remote//LibrarySessionBean!ru.hdghg.stateless.LibrarySessionBeanRemote?stateful");
            while (2 != choice) {
                String bookName;
                System.out.print("Options \n1. Add Book\n2. Exit \nEnter Choice: ");
                String strChoice = br.readLine();
                choice = Integer.parseInt(strChoice);
                if (1 == choice) {
                    System.out.print("Enter book name: ");
                    bookName = br.readLine();
                    librararySessionBeanRemote.addBook(bookName);
                }
            }
            List<String> booksList = librararySessionBeanRemote.getBooks();
            System.out.println("Book(s) entered so far: " + booksList.size());
            for (int i = 0; i < booksList.size(); ++i) {
                System.out.println((i+1)+". " + booksList.get(i));
            }
            LibrarySessionBeanRemote libraryBean1 =
                    (LibrarySessionBeanRemote)context.lookup("ejb:/remote//LibrarySessionBean!ru.hdghg.stateless.LibrarySessionBeanRemote?stateful");
            List<String> booksList1 = libraryBean1.getBooks();
            System.out.println(
                    "***Using second lookup to get library stateless object***");
            System.out.println(
                    "Book(s) entered so far: " + booksList1.size());
            for (int i = 0; i < booksList1.size(); ++i) {
                System.out.println((i+1)+". " + booksList1.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
