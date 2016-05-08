
# ChessWars pelin käyttöohje

## Käyttöliittymä

Pelin käyttöliittymä koostuu kolmesta osasta.

* Navigointiosassa on mahdollista navigoida pelieditorin ja taistelujen pelaamiseen käytetyn huoneen välillä.
* Pelieditorissa on mahdollista luoda uusi taistelu, jota voi halutessaan pelata.
* Taisteluhuoneessa on mahdollista pelata peliä tekoälyä vastaan tai toista ihmistä vastaan käyttäen samaa käyttöliittymää vuorotellen.


## Pelin säännöt

Pelin säännöt perustuvat perinteiseen shakkiin. Hevonen, kuningas, torni, lähetti ja kuningatar liikkuvat täsmälleen kuin shakissa. Tornin, lähetin ja kuningattaren askelpituus on rajoitettu kuuteen askeleeseen. Hevonen, sotilas ja kuningas voivat liikkua vain yhden askeleen. Siirtojen askelpituutta rajoittavat myös vuoret, joiden läpi vain sotilaat voivat kulkea. Lisäksi metsien läpi kulkeminen pienentää mahdollisten askelien lukumäärää.

![Tornin liikkumismahdollisuudet](/dokumentaatio/tornin_liikkuminen.png "Tornin liikkuminen")

Toisin kuin perinteisessä shakissa sotilaat voivat liikkua yhden askeljeen vaaka- tai pystysuuntaan. Sotilaat voivat myös liikkua vinottain syödessään vastustajan sotilaan. Jos vastustajan sotilasarvoton nappula syö oman sotilaan, ei tämän vastustaja nappulan syöminen ole mahdollista seuraavalla vuorolla omalla sotilaalla, koska sotilaat ovat pelkureita. Tämä sääntö on välttämätön, jottei olisi mahdollista muodostaa voittamattomia sotilaista koostuvia muureja.

![Sotilaan syöminen](/dokumentaatio/sotilaan_syominen_tornilla.png "Sotilaan syöminen")


Jokaisella pelaajalla tulisi olla kuningas pelikentällä. Jos pelaajan kuningas syödään, pelaaja häviää pelin. Peli loppuu, kun enää on jäljellä vain samaan joukkueeseen kuuluvia pelaajia.

## Karttaeditori

Karttaeditorissa on mahdollista tehdä oma taistelu, jota voi pelata tallennettuaan sen. 

![Karttaeditori](/dokumentaatio/karttaeditori.png "Karttaeditori")

Karttaeditorissa on paljon erilaista toiminnallisuutta.

  - Kartan nimen voi kirjoittaa ylimpänä olevaan tekstikenttään. 
  - Kun värin kuningas on lisätty kartaalle, on mahdollista valita pelaako väriä tekoäly vai käyttäjä.
  - Tällöin on myös mahdollista valita numeronapeista joukkuenumerot eri väreille.
  - Kuvattomista värinappuloista on mahdollista valita kartalle asetettavien nappuloiden väri.
  - Kartalle asetettavan nappulan tai maaston voi valita kuvallista nappia painamalla. Karttaa klikattaessa haluttu nappula tai maasto asettuu kartalle, mikäli se on mahdollista.
  - Kartan on mahdollista tyhjentää painamalla Clear-nappia.
  - Kartan voi tallentaa painamalla Save-nappia.
  - Editorin valikkoon voi palata painamalla Editor menu-nappia.
