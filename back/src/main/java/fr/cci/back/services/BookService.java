package fr.cci.back.services;

import fr.cci.back.entities.Book;
import fr.cci.back.entities.Loan;
import fr.cci.back.payloads.requests.SaveBookDTO;
import fr.cci.back.payloads.responses.GetBookByTitleResponseDTO;
import fr.cci.back.payloads.responses.GetBookResponseDTO;
import fr.cci.back.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<GetBookResponseDTO> get() {
        List<Book> entityBooks = bookRepository.findAll();
        List<GetBookResponseDTO> dtoBooks = new ArrayList<>();

        entityBooks.forEach(entity -> {
            GetBookResponseDTO dto = new GetBookResponseDTO();
            dto.setTitle(entity.getTitle());
            dto.setAuthor(entity.getAuthor());
            dto.setIsbn(entity.getIsbn());
            dtoBooks.add(dto);
        });

        return dtoBooks;
    }

    public GetBookByTitleResponseDTO getByTitle(String bookname) {
        Book entity = bookRepository.findByTitle(bookname);

        if (entity == null) {
            return null;
        }

        GetBookByTitleResponseDTO dtoBooks = new GetBookByTitleResponseDTO();
        dtoBooks.setTitle(entity.getTitle());
        dtoBooks.setAuthor(entity.getAuthor());
        dtoBooks.setIsbn(entity.getIsbn());
        List<String> loans = entity.getLoan().stream()
                .map(Loan::toString)
                .collect(Collectors.toList());

        dtoBooks.setLoans(loans);


        return dtoBooks;
    }

    public void save(SaveBookDTO bookDTO) {
        Book entity = new Book();
        entity.setTitle(bookDTO.getTitle());
        entity.setAuthor(bookDTO.getAuthor());
        entity.setIsbn(bookDTO.getIsbn());

        bookRepository.save(entity);
    }
}
