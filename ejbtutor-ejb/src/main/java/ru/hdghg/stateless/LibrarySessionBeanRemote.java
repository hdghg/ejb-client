package ru.hdghg.stateless;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface LibrarySessionBeanRemote {

    void addBook(String bookName);

    List<String> getBooks();
}
