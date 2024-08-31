package com.api.book.bootrestbook.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.api.book.bootrestbook.dao.BookRepository;
import com.api.book.bootrestbook.entities.Book;

@Configuration
public class Bookservices {
    
    @Autowired
    private BookRepository bookRepository;

//    private static List<Book> list=new ArrayList<>();

    // static{
    //     list.add(new Book(12,"Java complete reference","XYZ"));
    //     list.add(new Book(13,"Head first to java","ABC"));
    //     list.add(new Book(14,"Think in java","LMN"));
    // }

    public List<Book> getAllBooks(){
        // return list;
        List<Book> list= (List<Book>) bookRepository.findAll();
        return list; 
    }

    public Book getBookById(int id){
    //    for(Book book:list){
    //         if(book.getId()==id){
    //             return book;
    //         }
    //    }
    //    return null;
        Book b=bookRepository.findById(id);
        return b;
    }

    public Book addBook(Book book) {
        try {
            Book b=bookRepository.save(book);
            return b;
        } catch (Exception e) {
            // Log or print the exception details
            e.printStackTrace();
            throw new RuntimeException("Failed to add book: " + e.getMessage());
        }
    }
    

    public void deleteBook(int bid){
        // for (Book book : list) {
        //     if (book.getId() == bid) {
        //         list.remove(book);
        //         return book; 
        //     }
        // }
        // return null;
        bookRepository.deleteById(bid);
    }

    public void updatebook(Book book,int id){
        // list.stream().map(b->{
        //     if(b.getId()==id){
        //         b.setAuthor(book.getAuthor());
        //         b.setTitle(book.getTitle());
        //     }
        //     return b;                    
        // }).collect(Collectors.toList());
        book.setId(id);
        bookRepository.save(book);
    }

}
