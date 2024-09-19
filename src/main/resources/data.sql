/* Lägga in data för alla tabeller i databasen */

-- Lägg till Genre
INSERT INTO genre (id, name) VALUES
                                 (1, 'Rock'),
                                 (2, 'Pop'),
                                 (3, 'Metal'),
                                 (4, 'Blues'),
                                 (5, 'Jazz'),
                                 (6, 'Hard Rock'),
                                 (7, 'Heavy Metal'),

                                 (8, 'True Crime'),
                                 (9, 'Komedi'),
                                 (10, 'Dokumentär'),
                                 (11, 'Fiction');

-- Lägg till TypeOfMedia
INSERT INTO type_of_media (id, type) VALUES
                                       (1, 'Musik'),
                                       (2, 'Pod'),
                                       (3, 'Video');

-- Lägg till Artist
INSERT INTO artist (id, name) VALUES
                                  (1, 'Led Zeppelin'),
                                  (2, 'AC/DC'),
                                  (3, 'The Rolling Stones'),
                                  (4, 'Black Sabbath'),
                                  (5, 'Billie Eilish'),
                                  (6, 'Laleh'),

                                  (7, 'Pod-Artist 1'),
                                  (8, 'Pod-Artist 2'),
                                  (9, 'Pod-Artist 3'),
                                  (10, 'Pod-Artist 4'),
                                  (11, 'Pod-Artist 5'),

                                  (12, 'Video Artist 1'),
                                  (13, 'Video Artist 2'),
                                  (14, 'Video Artist 3'),
                                  (15, 'Video Artist 4'),
                                  (16, 'Video Artist 5');

-- Lägg till Album med artist_id
INSERT INTO album (id, title) VALUES
                                             (1, 'Led Zeppelin IV'),
                                             (2, 'Physical Graffiti'),
                                             (3, 'Back in Black'),
                                             (4, 'Highway to Hell'),
                                             (5, 'Sticky Fingers'),
                                             (6, 'Let It Bleed'),
                                             (7, 'Paranoid'),
                                             (8, 'Black Sabbath'),
                                             (9, 'When we all fall asleep, where do we go?'),
                                             (10, 'Kristaller'),

                                             (11, 'pod name 1 - crime'),
                                             (12, 'pod name 2 - komedi'),
                                             (13, 'pod name 3 - dokumentär'),
                                             (14, 'pod name 4 - fiction');

-- Lägg till NumberOnAlbum
INSERT INTO number_on_album (id, number_on_album, album_id) VALUES
                                                            (1, 1, 1), (2, 2, 1),
                                                            (3, 1, 2), (4, 2, 2),
                                                            (5, 1, 3), (6, 2, 3),
                                                            (7, 1, 4), (8, 2, 4),
                                                            (9, 1, 5), (10, 2, 5),
                                                            (11, 1, 6), (12, 2, 6),
                                                            (13, 1, 7), (14, 2, 7), (15, 3, 7), (16, 4, 7),
                                                            (17, 1, 8), (18, 2, 8), (19, 3, 8), (20, 4, 8),
                                                            (21, 1, 9), (22, 2, 9), (23, 3, 9),
                                                            (24, 1, 10), (25, 2, 10), (26, 3, 10), (27, 4, 10),

                                                            (28, 1, 11), (29, 2, 11), (30, 3, 11), (31, 4, 11),(32, 5, 11),
                                                            (33, 1, 12), (34, 2, 12), (35, 3, 12), (36, 4, 12),
                                                            (37, 1, 13), (38, 2, 13), (39, 3, 13), (40, 4, 13),
                                                            (41, 3, 14), (42, 4, 14);

-- Lägg till Media
INSERT INTO media (id, title, release_date, type_of_media_id, number_on_album_id, album_id, url) VALUES
                                                                                                     (1, 'Stairway to Heaven', '1971-11-08', 1, 1,1 ,'https://example.com/ledzeppelin/stairway_to_heaven'),
                                                                                                     (2, 'Black Dog', '1971-11-08', 1, 2,1 ,'https://example.com/ledzeppelin/black_dog'),
                                                                                                     (3, 'Kashmir', '1975-02-24', 1, 3, 2 ,'https://example.com/ledzeppelin/kashmir'),
                                                                                                     (4, 'Whole Lotta Love', '1969-11-07', 1, 4,2 ,'https://example.com/ledzeppelin/whole_lotta_love'),
                                                                                                     (5, 'Hells Bells', '1980-04-21', 1, 5,3 ,'https://example.com/acdc/hells_bells'),
                                                                                                     (6, 'You Shook Me All Night Long', '1980-04-21', 1, 6,3 ,'https://example.com/acdc/you_shook_me_all_night_long'),
                                                                                                     (7, 'Highway to Hell', '1979-07-27', 1, 7, 4, 'https://example.com/acdc/highway_to_hell'),
                                                                                                     (8, 'Girls Got Rhythm', '1979-07-27', 1, 8,  4, 'https://example.com/acdc/girls_got_rhythm'),
                                                                                                     (9, 'Brown Sugar', '1971-04-16', 1, 9, 5, 'https://example.com/rollingstones/brown_sugar'),
                                                                                                     (10, 'Wild Horses', '1971-04-16', 1, 10,  5, 'https://example.com/rollingstones/wild_horses'),
                                                                                                     (11, 'Gimme Shelter', '1969-12-05', 1, 11,  6,'https://example.com/rollingstones/gimme_shelter'),
                                                                                                     (12, 'You Can’t Always Get What You Want', '1969-12-05', 1, 12,  6, 'https://example.com/rollingstones/you_cant_always_get_what_you_want'),
                                                                                                     (13, 'War Pigs', '1970-09-18', 1, 13,  7, 'https://example.com/blacksabbath/warpigs'),
                                                                                                     (14, 'Paranoid', '1970-09-18', 1, 14, 7, 'https://example.com/blacksabbath/paranoid'),
                                                                                                     (15, 'Planet Caravan', '1970-09-18', 1, 15,  7, 'https://example.com/blacksabbath/planetcaravan'),
                                                                                                     (16, 'Iron Man', '1970-09-18', 1, 16,  8, 'https://example.com/blacksabbath/ironman'),
                                                                                                     (17, 'Black Sabbath', '1970-01-09', 1, 17,  8, 'https://example.com/blacksabbath/blacksabbath'),
                                                                                                     (18, 'The Wizard', '1970-01-09', 1, 18,  8, 'https://example.com/blacksabbath/thewizard'),
                                                                                                     (19, 'Behind The Wall Of Sleep', '1970-01-09', 1, 19,  8, 'https://example.com/blacksabbath/behindthewallofsleep'),
                                                                                                     (20, 'Evil Woman', '1970-01-09', 1, 20,  8, 'https://example.com/blacksabbath/evilwoman'),
                                                                                                     (21, 'bad guy', '2019-03-29', 1, 21,  9, 'https://example.com/billieeilish/badguy'),
                                                                                                     (22, 'xanny', '2019-03-29', 1, 22,  9, 'https://example.com/billieeilish/xanny'),
                                                                                                     (23, 'you should see me in a crown', '2019-03-29', 1, 23,  9, 'https://example.com/billieeilish/youshouldseemeinacrown'),
                                                                                                     (24, 'Bara Få Vara Mig Själv', '2016-09-16', 1, 24, 10, 'https://example.com/laleh/barafavaramigsjalv'),
                                                                                                     (25, 'Aldrig Bli Som Förr', '2016-09-16', 1, 25,  10, 'https://example.com/laleh/aldrigblisomforr'),
                                                                                                     (26, 'Behåll Ditt Huvud', '2016-09-16', 1, 26,  10, 'https://example.com/laleh/behallditthuvud'),
                                                                                                     (27, 'Kristaller', '2016-09-16', 1, 27,  10, 'https://example.com/laleh/kristaller'),

                                                                                                     (28, 'pod 1', '2024-01-01', 2, 28, 11,'https://example.com/pod1'),
                                                                                                     (29, 'pod 2', '2024-01-01', 2, 29, 11,'https://example.com/pod1'),
                                                                                                     (30, 'pod 3', '2024-01-01', 2, 30, 11,'https://example.com/pod1'),
                                                                                                     (31, 'pod 4', '2024-01-01', 2, 31, 11,'https://example.com/pod1'),
                                                                                                     (32, 'pod 5', '2024-01-01', 2, 32, 11,'https://example.com/pod1'),
                                                                                                     (33, 'pod 6', '2024-01-01', 2, 33, 12,'https://example.com/pod1'),
                                                                                                     (34, 'pod 7', '2024-01-01', 2, 34, 12,'https://example.com/pod1'),
                                                                                                     (35, 'pod 8', '2024-01-01', 2, 35, 12,'https://example.com/pod1'),
                                                                                                     (36, 'pod 9', '2024-01-01', 2, 36, 12,'https://example.com/pod1'),
                                                                                                     (37, 'pod 10', '2024-01-01', 2, 37, 13,'https://example.com/pod1'),
                                                                                                     (38, 'pod 11', '2024-01-01', 2, 38, 13,'https://example.com/pod1'),
                                                                                                     (39, 'pod 12', '2024-01-01', 2, 39, 13,'https://example.com/pod1'),
                                                                                                     (40, 'pod 13', '2024-01-01', 2, 40, 13,'https://example.com/pod1'),
                                                                                                     (41, 'pod 14', '2024-01-01', 2, 41, 14,'https://example.com/pod1'),
                                                                                                     (42, 'pod 15', '2024-01-01', 2, 42, 14,'https://example.com/pod1'),

                                                                                                     (43, 'video 1', '2020-02-20', 3, null, null,'https://example.com/video1'),
                                                                                                     (44, 'video 2', '2020-02-20', 3, null, null,'https://example.com/video2'),
                                                                                                     (45, 'video 3', '2020-02-20', 3, null, null,'https://example.com/video3'),
                                                                                                     (46, 'video 4', '2020-02-20', 3, null, null,'https://example.com/video4'),
                                                                                                     (47, 'video 5', '2020-02-20', 3, null, null,'https://example.com/video5'),
                                                                                                     (48, 'video 6', '2020-02-20', 3, null, null,'https://example.com/video6'),
                                                                                                     (49, 'video 7', '2020-02-20', 3, null, null,'https://example.com/video7'),
                                                                                                     (50, 'video 8', '2020-02-20', 3, null, null,'https://example.com/video8'),
                                                                                                     (51, 'video 9', '2020-02-20', 3, null,  null,'https://example.com/video9'),
                                                                                                     (52, 'video 10', '2020-02-20', 3, null, null,'https://example.com/video10'),
                                                                                                     (53, 'video 11', '2020-02-20', 3, null, null,'https://example.com/video11'),
                                                                                                     (54, 'video 12', '2020-02-20', 3, null, null,'https://example.com/video12'),
                                                                                                     (55, 'video 13', '2020-02-20', 3, null, null,'https://example.com/video13'),
                                                                                                     (56, 'video 14', '2020-02-20', 3, null, null,'https://example.com/video14'),
                                                                                                     (57, 'video 15', '2020-02-20', 3, null, null,'https://example.com/video15');

-- Lägg till data i media_artist-tabellen
INSERT INTO media_artist (media_id, artist_id) VALUES
                                                   (1, 1), (2, 1), (3, 1), (4, 1),
                                                   (5, 2), (6, 2), (7, 2), (8, 2),
                                                   (9, 3), (10, 3), (11, 3), (12, 3),
                                                   (13, 4), (14, 4), (15, 4), (16, 4),
                                                   (17, 4), (18, 4), (19, 4), (20, 4),
                                                   (21, 5), (22, 5), (23, 5), (24, 6),
                                                   (25, 6), (26, 6), (27, 6),

                                                   (28, 7), (29, 7), (30, 7), (31, 7), (32, 7),
                                                   (33, 8), (34, 8), (35, 8), (36, 8),
                                                   (37, 9), (38, 9), (39,9), (40, 9),
                                                   (41, 10), (41, 11), (42, 10), (42, 11),

                                                   (43, 12), (44, 12), (45, 12), (46, 12),
                                                   (47, 13), (48, 13), (49, 13), (50, 13),
                                                   (51, 14), (52, 14), (53, 14), (54, 14),
                                                   (55, 15), (56, 15), (57, 16);



-- Lägg till data i media_genre-tabellen
INSERT INTO media_genre (media_id, genre_id) VALUES
                                                 (1, 1), (2, 1), (3, 1), (4, 1),
                                                 (5, 1), (6, 1), (7, 1), (8, 1),
                                                 (9, 1), (10, 1), (11, 1), (12, 1),
                                                 (13, 7), (14, 7), (15, 7), (16, 7),
                                                 (17, 7), (18, 7), (19, 7), (20, 7),
                                                 (21, 2), (22, 2), (23, 2), (24, 2),
                                                 (25, 2), (26, 2), (27, 2),

                                                 (28, 8), (29, 8), (30, 8), (31, 8), (32, 8),
                                                 (33,9), (34, 9), (35, 9), (36, 9),
                                                 (37, 10), (38, 10), (39,10), (40, 10),
                                                 (41, 11), (42, 11),

                                                 (43, 8), (44, 8), (45, 8), (46, 8),
                                                 (47, 9), (48, 9), (49, 9), (50, 9),
                                                 (51, 10), (52, 10), (53, 10), (54, 10),
                                                 (55, 11), (56, 11), (57, 11);


INSERT INTO artist_albums (artist_id, album_id) VALUES
                                                    (1,1), (1,2),
                                                    (2,3), (2,4),
                                                    (3,5), (3,6),
                                                    (4, 7), (4, 8),
                                                    (5, 9),
                                                    (6, 10),

                                                    (7, 11),
                                                    (8, 12),
                                                    (9, 13),
                                                    (10, 14),
                                                    (11, 14);


