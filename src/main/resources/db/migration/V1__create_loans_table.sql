create table LOANS (
    id serial PRIMARY KEY,
    loaner varchar NOT NULL,
    book_references jsonb NOT NULL,
    loaned_at TIMESTAMP NOT NULL,
    duration INTERVAL NOT NULL
);
