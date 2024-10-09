-- List all stored procedures
--\i manage_stored_procedures.sql
SELECT proname FROM pg_proc WHERE pronamespace = 'public'::regnamespace;

-- Drop all stored procedures
DO $$
DECLARE
    proc RECORD;
BEGIN
    FOR proc IN (SELECT proname FROM pg_proc WHERE pronamespace = 'public'::regnamespace) LOOP
        EXECUTE 'DROP FUNCTION IF EXISTS ' || proc.proname || ' CASCADE';
    END LOOP;
END $$;
