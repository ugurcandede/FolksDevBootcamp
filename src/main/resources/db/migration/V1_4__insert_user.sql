INSERT INTO users
    (id, username, email, display_name, is_active)
VALUES ('0', 'folksdev', 'contact@folksdev.com', 'FolksDev', true)
ON CONFLICT DO NOTHING;
