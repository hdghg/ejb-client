package ru.hdghg.stateless;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateful
public class LibrarySessionBean implements LibrarySessionBeanRemote {
    private List<String> bookshelf;

    public LibrarySessionBean() {
        bookshelf = new ArrayList<>();
        System.out.println("Created shelf");
    }

    @Override
    public void addBook(String bookName) {
        bookshelf.add(bookName);
        System.out.printf("Added book %s, new size: %s%n", bookName, bookshelf.size());
    }

    @Override
    public List<String> getBooks() {
        System.out.printf("Returned books size: %s%n", bookshelf.size());
        return bookshelf;
    }
}
