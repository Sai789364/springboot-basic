package com.api.book.bootrestbook.Controllers;


import org.springframework.web.bind.annotation.RestController;

import com.api.book.bootrestbook.Services.Bookservices;
import com.api.book.bootrestbook.entities.Book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
public class BootController {

    @Autowired
    public Bookservices bookservices;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks(){
        List<Book> list=bookservices.getAllBooks();
        if(list.size()<=0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") int id){
        Book book=bookservices.getBookById(id);
        if(book==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok().body(book);
    }

    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book b){
        Book book=null;
        try {
            book=this.bookservices.addBook(b);
            return ResponseEntity.ok().body(book);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Book> deletebook(@PathVariable("id") int id){
        
        try {
            bookservices.deleteBook(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updatebook(@RequestBody Book b,@PathVariable("id") int id){
        try {
            bookservices.updatebook(b,id);
            return ResponseEntity.ok().body(b);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        
    }
    
}
