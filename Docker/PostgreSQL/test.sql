-- Insertion des livres
INSERT INTO BOOKS (title, author, isbn) VALUES
                                            ('Le Petit Prince', 'Antoine de Saint-Exupéry', '978-0156012195'),
                                            ('1984', 'George Orwell', '978-0451524935'),
                                            ('Les Misérables', 'Victor Hugo', '978-2070409185'),
                                            ('Harry Potter à l école des sorciers', 'J.K. Rowling', '978-2070643028'),
                                            ('Le Comte de Monte-Cristo', 'Alexandre Dumas', '978-2070409550');

-- Insertion des clients
INSERT INTO CUSTOMERS (first_name, last_name) VALUES
    ('Alice', 'Durand'),
    ('Bob', 'Martin'),
    ('Claire', 'Lemoine'),
    ('David', 'Moreau'),
    ('Eva', 'Dubois');

-- Insertion des prêts
INSERT INTO LOANS (loan_date, return_date, idCustomer, idBook) VALUES
    ('2025-06-01', '2025-06-15', 1, 2),  -- Alice emprunte 1984
    ('2025-06-16', '2025-06-19', 2, 2),  -- Bob emprunte 1984
    ('2025-06-05', '2025-06-20', 2, 1),  -- Bob emprunte Le Petit Prince
    ('2025-06-10', NULL, 3, 3),           -- Claire emprunte Les Misérables (pas encore rendu)
    ('2025-06-12', '2025-06-22', 4, 4),  -- David emprunte Harry Potter
    ('2025-06-14', NULL, 5, 5);           -- Eva emprunte Le Comte de Monte-Cristo (pas encore rendu)
