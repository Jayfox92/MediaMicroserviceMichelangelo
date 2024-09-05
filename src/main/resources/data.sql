-- Radera alla tabeller om de redan finns
/*DROP TABLE IF EXISTS Media;
DROP TABLE IF EXISTS NumberOnAlbum;
DROP TABLE IF EXISTS Genre;
DROP TABLE IF EXISTS TypeOfMedia;
DROP TABLE IF EXISTS Album;
DROP TABLE IF EXISTS Artist;

-- Skapa Genre-tabellen
CREATE TABLE Genre (
                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                       name VARCHAR(40) NOT NULL
);

-- Skapa TypeOfMedia-tabellen
CREATE TABLE TypeOfMedia (
                             id BIGINT PRIMARY KEY AUTO_INCREMENT,
                             type VARCHAR(40) NOT NULL
);

-- Skapa Artist-tabellen
CREATE TABLE Artist (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT,
                        name VARCHAR(40) NOT NULL
);

-- Skapa Album-tabellen
CREATE TABLE Album (
                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                       title VARCHAR(40) NOT NULL
);

-- Skapa NumberOnAlbum-tabellen
CREATE TABLE NumberOnAlbum (
                               id BIGINT PRIMARY KEY AUTO_INCREMENT,
                               numberOnAlbum INT NOT NULL,
                               album_id BIGINT,
                               FOREIGN KEY (album_id) REFERENCES Album(id)
);

-- Skapa Media-tabellen
CREATE TABLE Media (
                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                       title VARCHAR(40) NOT NULL,
                       release_date DATE,
                       type_of_media_id BIGINT,
                       number_on_album_id BIGINT,
                       album_id BIGINT,
                       genre_id BIGINT,
                       FOREIGN KEY (type_of_media_id) REFERENCES TypeOfMedia(id),
                       FOREIGN KEY (number_on_album_id) REFERENCES NumberOnAlbum(id),
                       FOREIGN KEY (album_id) REFERENCES Album(id),
                       FOREIGN KEY (genre_id) REFERENCES Genre(id)
);
*/
-- Lägg till Genre
INSERT INTO Type_Of_Media (type) VALUES ('Musik'), ('Pod'), ('Video');

-- Lägg till TypeOfMedia
INSERT INTO Genre (name) VALUES ('Rock'), ('Pop'), ('Metal'), ('Blues'), ('Jazz'), ('Hard Rock');

-- Lägg till Artist
INSERT INTO Artist (name) VALUES ('Led Zeppelin'), ('AC/DC'), ('The Rolling Stones');

-- Lägg till Album
INSERT INTO Album (title) VALUES
                              ('Led Zeppelin IV'),
                              ('Physical Graffiti'),
                              ('Back in Black'),
                              ('Highway to Hell'),
                              ('Sticky Fingers'),
                              ('Let It Bleed');

-- Lägg till NumberOnAlbum
INSERT INTO Number_On_Album (number_On_Album, album_id) VALUES
                                                        (1, 1), (2, 1),
                                                        (1, 2), (2, 2),
                                                        (1, 3), (2, 3),
                                                        (1, 4), (2, 4),
                                                        (1, 5), (2, 5),
                                                        (1, 6), (2, 6);

-- Lägg till Media med Genre
INSERT INTO Media (title, release_date, type_of_media_id, number_on_album_id, album_id, genre_id) VALUES
                                                                                                      -- Led Zeppelin
                                                                                                      ('Stairway to Heaven', '1971-11-08', 1, 1, 1, 1),  -- Rock
                                                                                                      ('Black Dog', '1971-11-08', 1, 2, 1, 1),          -- Rock
                                                                                                      ('Kashmir', '1975-02-24', 1, 3, 2, 1),            -- Rock
                                                                                                      ('Whole Lotta Love', '1969-11-07', 1, 4, 2, 1),    -- Rock
                                                                                                      -- AC/DC
                                                                                                      ('Hells Bells', '1980-04-21', 1, 5, 3, 6),         -- Hard Rock
                                                                                                      ('You Shook Me All Night Long', '1980-04-21', 1, 6, 3, 6), -- Hard Rock
                                                                                                      ('Highway to Hell', '1979-07-27', 1, 7, 4, 6),     -- Hard Rock
                                                                                                      ('Girls Got Rhythm', '1979-07-27', 1, 8, 4, 6),    -- Hard Rock
                                                                                                      -- The Rolling Stones
                                                                                                      ('Brown Sugar', '1971-04-16', 1, 9, 5, 4),         -- Blues
                                                                                                      ('Wild Horses', '1971-04-16', 1, 10, 5, 4),        -- Blues
                                                                                                      ('Gimme Shelter', '1969-12-05', 1, 11, 6, 4),      -- Blues
                                                                                                      ('You Can’t Always Get What You Want', '1969-12-05', 1, 12, 6, 4); -- Blues

