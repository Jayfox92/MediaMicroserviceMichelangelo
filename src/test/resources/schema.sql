-- Skapa tabeller för entiteter

-- Tabell: Album
CREATE TABLE album (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       title VARCHAR(100) NOT NULL
);

-- Tabell: Artist
CREATE TABLE artist (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(40) NOT NULL
);

-- Tabell: Genre
CREATE TABLE genre (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(50) NOT NULL
);

-- Tabell: TypeOfMedia
CREATE TABLE type_of_media (
                               id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               type VARCHAR(50) NOT NULL
);

-- Tabell: NumberOnAlbum
CREATE TABLE number_on_album (
                                 id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                 number_on_album INT NOT NULL,
                                 album_id BIGINT,
                                 FOREIGN KEY (album_id) REFERENCES album(id)
);

-- Tabell: Media
CREATE TABLE media (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       title VARCHAR(40) NOT NULL,
                       release_date DATE,
                       type_of_media_id BIGINT,
                       number_on_album_id BIGINT,
                       album_id BIGINT,
                       url VARCHAR(100) NOT NULL,
                       FOREIGN KEY (type_of_media_id) REFERENCES type_of_media(id),
                       FOREIGN KEY (number_on_album_id) REFERENCES number_on_album(id),
                       FOREIGN KEY (album_id) REFERENCES album(id)
);


-- Skapa tabeller för manytomany relationer mellan entiteter

-- Tabell för relation mellan artist och album
CREATE TABLE artist_albums (
                               artist_id BIGINT,
                               album_id BIGINT,
                               PRIMARY KEY (artist_id, album_id),
                               FOREIGN KEY (artist_id) REFERENCES artist(id),
                               FOREIGN KEY (album_id) REFERENCES album(id)
);

-- Tabell för relation mellan media och genre
CREATE TABLE media_genre (
                             media_id BIGINT,
                             genre_id BIGINT,
                             PRIMARY KEY (media_id, genre_id),
                             FOREIGN KEY (media_id) REFERENCES media(id),
                             FOREIGN KEY (genre_id) REFERENCES genre(id)
);

-- Tabell för relation mellan media och artist
CREATE TABLE media_artist (
                              media_id BIGINT,
                              artist_id BIGINT,
                              PRIMARY KEY (media_id, artist_id),
                              FOREIGN KEY (media_id) REFERENCES media(id),
                              FOREIGN KEY (artist_id) REFERENCES artist(id)
);