package services;

import models.Book;
import models.Category;
import repo.DBMainRepo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BookService {
    private static List<Book> bookList;

    public BookService(){
        bookList = DBMainRepo.getBookListList();
    }

    public List<Book> load(String categoryName, String bookName, String bookAuthor) {
        Stream<Book> streamResult = bookList.stream();
        if(categoryName != null && !categoryName.trim().isEmpty()){
            streamResult = streamResult
                    .filter(book -> book.getCategories().stream()
                            .anyMatch(category -> category.getTitle().equals(categoryName)));
        }

        if(bookName != null && !bookName.trim().isEmpty()){
            streamResult = streamResult
                    .filter(book -> book.getTitle().equals(bookName));
        }

        if(bookAuthor != null && !bookAuthor.trim().isEmpty()){
            streamResult = streamResult
                    .filter(book -> book.getAuthor().equals(bookAuthor));
        }

        return streamResult.toList();
    }

}
