-- Run as
-- sudo -u postgres psql -f database.sql
CREATE DATABASE cutelion;
CREATE USER cutelion WITH PASSWORD 'localdev';
GRANT ALL PRIVILEGES ON DATABASE cutelion TO cutelion;