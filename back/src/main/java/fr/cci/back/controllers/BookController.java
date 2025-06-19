package fr.cci.back.controllers;

import fr.cci.back.payloads.requests.SaveBookDTO;
import fr.cci.back.payloads.responses.GetBookByTitleResponseDTO;
import fr.cci.back.payloads.responses.GetBookResponseDTO;
import fr.cci.back.services.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{booktitle}")
    public GetBookByTitleResponseDTO getByUsername(@PathVariable(value = "booktitle") String bookname) {
        return bookService.getByTitle(bookname);
    }

    @GetMapping
    public List<GetBookResponseDTO> get() {
        System.out.println("here");
        return bookService.get();
    }

    @PostMapping
    public void save(@RequestBody SaveBookDTO user) {
        bookService.save(user);
    }
}
