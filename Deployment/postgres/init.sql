-- Crear la tabla loan_types si no existe
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_tables WHERE tablename = 'loan_types') THEN
        CREATE TABLE loan_types (
            id SERIAL PRIMARY KEY,
            type VARCHAR(255) NOT NULL,
            maximum_term INT NOT NULL,
            max_finance DECIMAL(5, 2) NOT NULL,
            min_interest_rate DECIMAL(5, 2) NOT NULL,
            max_interest_rate DECIMAL(5, 2) NOT NULL,
            annual_interest_rate DECIMAL(5, 2) NOT NULL
        );

        -- Poblar la tabla loan_types
        INSERT INTO loan_types (id, type, maximum_term, max_finance, min_interest_rate, max_interest_rate, annual_interest_rate)
        VALUES
        (1, 'Primera Vivienda', 30, 0.80, 3.5, 5.0, 5.0),
        (2, 'Segunda Vivienda', 20, 0.70, 4.0, 6.0, 6.0),
        (3, 'Propiedades Comerciales', 25, 0.60, 5.0, 7.0, 7.0),
        (4, 'Remodelación', 15, 0.50, 4.5, 6.0, 6.0);
    END IF;
END $$;