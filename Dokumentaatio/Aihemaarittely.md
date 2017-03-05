
###### Aihe:

Projektin ideana on luoda Space Invaders klassikkopeliä jäljittelevä ohjelma.
Pelin ei toki ole tarkoitus olla suora kopio, vaan lähinnä mekaniikoiltaan alkuperäistä
muistuttava. Lähden toteuttamaan projektia hyvin simppelisti ja lisään siihen
elementtejä taitotasoni, sekä tarjolla olevan ajan mukaan.

Pääideana pelissä on siis ohjata avaruudessa kiitävää alusta ja raivata eteen tulleita vastustajia.

###### Käyttäjät:

* Kuka tahansa pelaamisesta kiinnostunut.

###### Toiminnot:

* Aluksen ohjaaminen
* Ampuminen
![Alt text](https://yuml.me/92dda8da)
![Alt text](/home/jonathan/varjorepo/Dokumentaatio/sekvenssikaavio1.png)
![Alt text]((/home/jonathan/varjorepo/Dokumentaatio/sekvenssikaavio2.png)

###### Ohjeet

* Ohjelman avauduttua, ikkunaa täytyy klikata, jotta voit ohjata alusta.
* Liiku painamalla näppäimiä w, a, s ja d sekä ammu painamalla välilyöntiä.
* Voittaaksesi, sinun tulee tuhota kaikki vastustaja alukset.
* Häviät pelin, mikäli aluksesi terveys tippuu nollaan.
* Törmätessä vastustaja-alukseen terveydestäsi vähennetään 50 ja vastustaja tuhoutuu.
* Vastustaja-aluksen tuhoamiseen vaadittava ohjusten määrä vaihtelee.

###### Rakennekuvaus

* Game-luokka toteuttaa ohjelman main metodin. Se vastaa Player, BackGroung sekä Formation olioiden luonnista pelin käynnistyessä.
Run-metodi luo pelin sykkeen tavoitteena 60Hz taajuus ja jokaisella sykkeellä se ajaa update, sekä repaint metodit.
Update ja repaint metodit tarkkailevat pelin tilaa ja reagoivat joko voitettaessa, taikka hävittäessä.
* Unit-luokka toimii superluokkana sekä Player, että Enemy olioille ja tarjoaa tarpeellisia metodeja olioiden tilan päivittämiseen.
* Player-luokalla luodaan peliin pelaaja, jolle annetaan ilmentymä Movement-luokasta. Pelaaja hyödyntää ampueessaan Projectile-luoka
olioita lisäämällä ne listaan, kun ampumis komento annetaan. Luokassa myös rajoitetaan pelaajan tulituksen tiheys.
* Enemy-luokka luo ilmentymiä vihollis olioista, joilla on oliomuuttujana muunmuassa random-olio. Tämän random-olion tarkoituksena
on luoda satunnaisuutta olion vaakasuuntaiseen liikkeeseen update komennon yhteydessä.
* Formation luokka luo vihollissaattueen asettamalla useita vihollis olioita listaan ja antaen niille sopivan välimatkan toisistaan.
* BackGroung-luokka kaikessa yksinkertaisuudessaan luo taustakuva olion, jolla on pystysuuntainen y-koordinaatti, jota muutetaan
pelin suorituksen aikana.
* Movement-luokka luo näppäintenkuuntelijan, jolle anntetaan parametrina pelaaja olio. Näppäintenkuuntelija muuttaa metodeillaan
pelaajaolion tilaa, kutsumalla sen eri metodeja näppäintä painettaessa, taikka irtipäästettäessä.
* Loader-luokan ainoana tarkoituksena on ladata kuva levyltä.
* SS-luokka ottaa kuvan Loader imentymältä ja rajaa siitä halutun osan.
* Projectile-luokka luo ammus-ilmentymiä, joiden alkusijainti on sama kuin sille asetetun pelaaja-olion senhetkiset koordinaatit.
Tämän jälkeen ammus etenee suoraviivaisesti, kunnes se joko poistuu ruudusta, taikka osuu vastustajaan vahinjoittaen tätä.
