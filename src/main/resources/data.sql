/*-- Radera alla tabeller om de redan finns
DROP TABLE IF EXISTS media_artist;
DROP TABLE IF EXISTS Media;
DROP TABLE IF EXISTS NumberOnAlbum;
DROP TABLE IF EXISTS Genre;
DROP TABLE IF EXISTS TypeOfMedia;
DROP TABLE IF EXISTS Album;
DROP TABLE IF EXISTS Artist;*/

/*-- Skapa Genre-tabellen
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

-- Skapa om Album-tabellen med artist_id
CREATE TABLE Album (
                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                       title VARCHAR(40) NOT NULL,
                       artist_id BIGINT,  -- Lägg till artist_id här
                       FOREIGN KEY (artist_id) REFERENCES Artist(id)  -- Lägg till främmande nyckel
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
                       url VARCHAR(100),
                       FOREIGN KEY (type_of_media_id) REFERENCES TypeOfMedia(id),
                       FOREIGN KEY (number_on_album_id) REFERENCES NumberOnAlbum(id),
                       FOREIGN KEY (album_id) REFERENCES Album(id),
                       FOREIGN KEY (genre_id) REFERENCES Genre(id)
);

-- Skapa media_artist-tabellen för ManyToMany-relationen mellan Media och Artist
CREATE TABLE media_artist (
                              media_id BIGINT,
                              artist_id BIGINT,
                              FOREIGN KEY (media_id) REFERENCES Media(id),
                              FOREIGN KEY (artist_id) REFERENCES Artist(id),
                              PRIMARY KEY (media_id, artist_id)
);*/
/*
-- Lägg till Genre
INSERT INTO Genre (name) VALUES ('Rock'), ('Pop'), ('Metal'), ('Blues'), ('Jazz'), ('Hard Rock');

-- Lägg till TypeOfMedia
INSERT INTO TypeOfMedia (type) VALUES ('Musik'), ('Pod'), ('Video');

-- Lägg till Artist
INSERT INTO Artist (name) VALUES ('Led Zeppelin'), ('AC/DC'), ('The Rolling Stones');

-- Lägg till Album med artist_id
INSERT INTO Album (title, artist_id) VALUES
                                         ('Led Zeppelin IV', 1),  -- Led Zeppelin
                                         ('Physical Graffiti', 1),  -- Led Zeppelin
                                         ('Back in Black', 2),  -- AC/DC
                                         ('Highway to Hell', 2),  -- AC/DC
                                         ('Sticky Fingers', 3),  -- The Rolling Stones
                                         ('Let It Bleed', 3);  -- The Rolling Stones


-- Lägg till NumberOnAlbum
INSERT INTO NumberOnAlbum (numberOnAlbum, album_id) VALUES
                                                        (1, 1), (2, 1),
                                                        (1, 2), (2, 2),
                                                        (1, 3), (2, 3),
                                                        (1, 4), (2, 4),
                                                        (1, 5), (2, 5),
                                                        (1, 6), (2, 6);

-- Lägg till Media med Genre
INSERT INTO Media (title, release_date, type_of_media_id, number_on_album_id, album_id, url) VALUES
                                                                                                           -- Led Zeppelin
                                                                                                           ('Stairway to Heaven', '1971-11-08', 1, 1, 1, 'https://example.com/ledzeppelin/stairway_to_heaven'),
                                                                                                           ('Black Dog', '1971-11-08', 1, 2, 1, 'https://example.com/ledzeppelin/black_dog'),
                                                                                                           ('Kashmir', '1975-02-24', 1, 3, 2, 'https://example.com/ledzeppelin/kashmir'),
                                                                                                           ('Whole Lotta Love', '1969-11-07', 1, 4, 2, 'https://example.com/ledzeppelin/whole_lotta_love'),
                                                                                                           -- AC/DC
                                                                                                           ('Hells Bells', '1980-04-21', 1, 5, 3, 'https://example.com/acdc/hells_bells'),
                                                                                                           ('You Shook Me All Night Long', '1980-04-21', 1, 6, 3, 'https://example.com/acdc/you_shook_me_all_night_long'),
                                                                                                           ('Highway to Hell', '1979-07-27', 1, 7, 4, 'https://example.com/acdc/highway_to_hell'),
                                                                                                           ('Girls Got Rhythm', '1979-07-27', 1, 8, 4, 'https://example.com/acdc/girls_got_rhythm'),
                                                                                                           -- The Rolling Stones
                                                                                                           ('Brown Sugar', '1971-04-16', 1, 9, 5, 'https://example.com/rollingstones/brown_sugar'),
                                                                                                           ('Wild Horses', '1971-04-16', 1, 10, 5, 'https://example.com/rollingstones/wild_horses'),
                                                                                                           ('Gimme Shelter', '1969-12-05', 1, 11, 6, 'https://example.com/rollingstones/gimme_shelter'),
                                                                                                           ('You Can’t Always Get What You Want', '1969-12-05', 1, 12, 6, 'https://example.com/rollingstones/you_cant_always_get_what_you_want');

-- Lägg till data i media_artist-tabellen
INSERT INTO media_artist (media_id, artist_id) VALUES
                                                   -- Led Zeppelin
                                                   (1, 1), (2, 1), (3, 1), (4, 1),
                                                   -- AC/DC
                                                   (5, 2), (6, 2), (7, 2), (8, 2),
                                                   -- The Rolling Stones
                                                   (9, 3), (10, 3), (11, 3), (12, 3);
*/
-- Lägg till Genre
INSERT INTO Genre (id, name) VALUES
                                 (1, 'Rock'),
                                 (2, 'Pop'),
                                 (3, 'Metal'),
                                 (4, 'Blues'),
                                 (5, 'Jazz'),
                                 (6, 'Hard Rock');

-- Lägg till TypeOfMedia
INSERT INTO type_of_media (id, type) VALUES
                                       (1, 'Musik'),
                                       (2, 'Pod'),
                                       (3, 'Video');

-- Lägg till Artist
INSERT INTO Artist (id, name) VALUES
                                  (1, 'Led Zeppelin'),
                                  (2, 'AC/DC'),
                                  (3, 'The Rolling Stones');

-- Lägg till Album med artist_id
INSERT INTO Album (id, title, artist_id) VALUES
                                             (1, 'Led Zeppelin IV', 1),
                                             (2, 'Physical Graffiti', 1),
                                             (3, 'Back in Black', 2),
                                             (4, 'Highway to Hell', 2),
                                             (5, 'Sticky Fingers', 3),
                                             (6, 'Let It Bleed', 3);

-- Lägg till NumberOnAlbum
INSERT INTO number_on_album (id, number_on_album, album_id) VALUES
                                                            (1, 1, 1),
                                                            (2, 2, 1),
                                                            (3, 1, 2),
                                                            (4, 2, 2),
                                                            (5, 1, 3),
                                                            (6, 2, 3),
                                                            (7, 1, 4),
                                                            (8, 2, 4),
                                                            (9, 1, 5),
                                                            (10, 2, 5),
                                                            (11, 1, 6),
                                                            (12, 2, 6);

-- Lägg till Media
INSERT INTO Media (id, title, release_date, type_of_media_id, number_on_album_id, album_id, url) VALUES
                                                                                                     (1, 'Stairway to Heaven', '1971-11-08', 1, 1, 1, 'https://example.com/ledzeppelin/stairway_to_heaven'),
                                                                                                     (2, 'Black Dog', '1971-11-08', 1, 2, 1, 'https://example.com/ledzeppelin/black_dog'),
                                                                                                     (3, 'Kashmir', '1975-02-24', 1, 3, 2, 'https://example.com/ledzeppelin/kashmir'),
                                                                                                     (4, 'Whole Lotta Love', '1969-11-07', 1, 4, 2, 'https://example.com/ledzeppelin/whole_lotta_love'),
                                                                                                     (5, 'Hells Bells', '1980-04-21', 1, 5, 3, 'https://example.com/acdc/hells_bells'),
                                                                                                     (6, 'You Shook Me All Night Long', '1980-04-21', 1, 6, 3, 'https://example.com/acdc/you_shook_me_all_night_long'),
                                                                                                     (7, 'Highway to Hell', '1979-07-27', 1, 7, 4, 'https://example.com/acdc/highway_to_hell'),
                                                                                                     (8, 'Girls Got Rhythm', '1979-07-27', 1, 8, 4, 'https://example.com/acdc/girls_got_rhythm'),
                                                                                                     (9, 'Brown Sugar', '1971-04-16', 1, 9, 5, 'https://example.com/rollingstones/brown_sugar'),
                                                                                                     (10, 'Wild Horses', '1971-04-16', 1, 10, 5, 'https://example.com/rollingstones/wild_horses'),
                                                                                                     (11, 'Gimme Shelter', '1969-12-05', 1, 11, 6, 'https://example.com/rollingstones/gimme_shelter'),
                                                                                                     (12, 'You Can’t Always Get What You Want', '1969-12-05', 1, 12, 6, 'https://example.com/rollingstones/you_cant_always_get_what_you_want');

-- Lägg till data i media_artist-tabellen
INSERT INTO media_artist (media_id, artist_id) VALUES
                                                   (1, 1), (2, 1), (3, 1), (4, 1),
                                                   (5, 2), (6, 2), (7, 2), (8, 2),
                                                   (9, 3), (10, 3), (11, 3), (12, 3);

-- Lägg till data i media_genre-tabellen
INSERT INTO media_genre (media_id, genre_id) VALUES
                                                 (1, 1), (2, 1), (3, 1), (4, 1),
                                                 (5, 1), (6, 1), (7, 1), (8, 1),
                                                 (9, 1), (10, 1), (11, 1), (12, 1);