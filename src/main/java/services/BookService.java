package services;

import models.Book;
import models.Category;
import repo.DBMainRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookService {
    public List<Book> load(String categoryName, String bookName, String bookAuthor) {

       List<Book> bookList = DBMainRepo.getBookListList();
       List<Book> result = new ArrayList<>();
       result = filterByCategory(categoryName, result, bookList);
       result = filterByBookName(bookName, result);
       result = filterByAuthorName(bookAuthor, result,bookList);
        return result;
    }


    private static List<Book> filterByBookName(String bookName, List<Book> result) {
        if (bookName != null && !bookName.trim().isEmpty()) {
            result =  result.stream().filter(m -> m.getTitle().contains(bookName)).collect(Collectors.toList());
        }
        return result;
    }

    private static List<Book> filterByCategory(String categoryName, List<Book> result, List<Book> bookList) {
        if (categoryName == null || categoryName.trim().isEmpty()) {
            result = bookList;
        } else {

            for (Book books : bookList) {
                for (Category category : books.getCategories()) {
                    if (category.getTitle().equals(categoryName)) {
                        result.add(books);
                        break;
                    }
                }
            }
        }
        return result;
    }
    private static List<Book> filterByAuthorName(String bookAuthor, List<Book> result, List<Book> bookList) {
        if (bookAuthor == null || bookAuthor.trim().isEmpty()) {
            result = bookList;
        } else {
//            return bookList.stream()
//                    .filter(m -> m.getAuthor()
//                            .stream()
//                            .anyMatch(c -> c.getTitle().equals(bookAuthor)))
//                    .collect(Collectors.toList());
            for (Book books : bookList) {
                    if (books.getAuthor().equals(bookAuthor)) {
                        result.add(books);
                        break;
                    }
            }
        }
        return result;
    }
}
