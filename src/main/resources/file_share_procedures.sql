-- Drop existing procedures if they exist
DROP FUNCTION IF EXISTS save_file(VARCHAR, BYTEA, TIMESTAMP);
DROP FUNCTION IF EXISTS retrieve_file(BIGINT);

-- Stored procedure to save a file
CREATE OR REPLACE FUNCTION save_file(p_name VARCHAR, p_data BYTEA, p_expiry TIMESTAMP)
RETURNS BIGINT AS $$
DECLARE
    file_id BIGINT;
BEGIN
    INSERT INTO Files (name, data, expiry_date)
    VALUES (p_name, p_data, p_expiry)
    RETURNING id INTO file_id;
    RETURN file_id;
END;
$$ LANGUAGE plpgsql;

-- Stored procedure to retrieve a file
CREATE OR REPLACE FUNCTION retrieve_file(p_id BIGINT)
RETURNS TABLE (file_name VARCHAR, file_data BYTEA) AS $$
BEGIN
    RETURN QUERY
    SELECT name, data FROM Files WHERE id = p_id AND (expiry_date IS NULL OR expiry_date > CURRENT_TIMESTAMP);
END;
$$ LANGUAGE plpgsql;