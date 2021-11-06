INSERT INTO users
    (id, username, email, display_name, is_active, created_at, updated_at)
VALUES ('0', 'folksdev', 'contact@folksdev.com', 'FolksDev', true, current_timestamp, current_timestamp)
ON CONFLICT DO NOTHING;
