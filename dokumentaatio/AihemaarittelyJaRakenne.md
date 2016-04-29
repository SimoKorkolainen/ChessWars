# Aihemäärittely



**Aihe:** Projektin aiheena on *ChessWars*-strategiapeli, joka perustuu löyhästi shakin säätöihin.
Tajunnan räjäyttävä *ChessWars* vapauttaa perinteisen shakin kahdeksan kertaa kahdeksan ruudukon ikeestä.
Pelissä pelaajat liikuttelevat vuorotellen shakkinappuloita ja yrittävät syödä vastustajan kuninkaan.

Pelaaminen tapahtuu lähtökohtaisesti tietokonetta vastaan. Tietokoneen tekoäly on tarkoitus toteuttaa alpha-beta-karsintaa ja sopivaa heuristiikkafunktiota käyttäen.

**Käyttäjä:** Pejaaja (mahdollisesti pelaajat, mikäli aikaa riittää)

**Pelaajan toiminnot:**

- Pelin pelaaminen tietokonetta vastaan
- Kenttäeditorin käyttö
- Luodun kentän pelaaminen
  * Onnistuu, mikäli kentän luonti onnistui.


## Rakenteen kuvaus

Kohdassa Luokkakaavio on esiteltynä luokkakaavio ohjelman logiikasta. Luokat Battle ja BattleMap ovat toiminnallisuuden tärkeimmät luokat. Ohjelmaa toteutettaessa ajateltiin, että kartta on olemassaoloriippuvainen taistelusta. Kartta on olemassa vain yhtä taistelua varten ja taistelua varten on vain yksi kartta. Ohjelmakoodissa ei ole kuitenkaan mitään rajoitetta, mikä estäisi kartan vaihtamisen kesken taistelun.

Nappuloita varten tehtiin abstrakti yläluokka Piece, joka vähentää koodin toisteisuutta. Alaluokat Rook ja Knight perivät yläluokan Piece. Maastotyyppejä varten tehtiin rajapinta Terrain. Rajapinnassa vaaditaan, että maastoilla on kustannus ja kuva. Maastotyypit Woods ja Mountains toteuttavat rajapinnan Terrain.

Taistelun siirrot toteutettaan kartalla Move-olioden avulla. Move-olio sisältää tiedon siirrettävästä nappulasta ja mahdollisesti syödystä nappulasta. Move-olio tietää myös siirron alku- ja loppukoordinaatit.

## Luokkakaavio



![Luokkakaavio](/dokumentaatio/chesswarsClassDiagram2.png "Luokkakaavio")


## Sekvenssikaaviot

### Tornin lisääminen kartalle editorissa
 
 ![Sekvenssikaavio nappulan lisäämisestä](/dokumentaatio/Adding_selected_piece_to_map_sequence_diagram.png "Sekvenssikaavio nappulan lisäämisestä")

### Siirron pyyntö käyttäjältä

 ![Sekvenssikaavio käyttäjän siirron pyynnöstä](/dokumentaatio/User_doing_a_move_during_the_battle_sequence_diagram.png "Sekvenssikaavio käyttäjän siirron pyynnöstä")
