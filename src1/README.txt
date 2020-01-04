Apostol Ruxandra Ecaterina
grupa 324-CD 

				ETAPA 2-PROIECT

	I. Readme explicatii etapa 1
	Citirea din fisier se face in clasa Input, iar datele memorate sunt
prelucrate cu ajutorul clasei GameInput. Aceste procedee se realizeaza ca in
scheletul de cod dat la tema 1.
	In clasa HeroOnMap asociez fiecarui erou coloana si linia pe care se afla.
Tot aici prin metoda move se vor face mutarile, daca este cazul, veificandu-se
daca eroul se afla in incapacitate. In caz afirmativ atunci i se va modifica
parametrul ce indica numarul de runde ramase pana se va putea deplasa.
	In clasa HeroesFactory utilizez clasele interne AssetsHitPoints si 
AssetsBonusHP in care sunt salvate constantele ce memoreaza hp-ul fiecarui 
erou, respectiv hp-ul bonus pentru fiecare nivel. Cand creez instante pentru 
fiecare erou in parte ma folosesc de ele. Instantele se creeaza folosindu-ma de
o litera primita ca parametru.
	Clasa Heroes are urmatoarele campuri: id(pentru a nu se lupta un erou cu
el insusi), letter (pentru a-l identifica usor si a-i afisa tipul in output), 
hitPoints, bonusHitPoints, experience points, level, dot1 si index(retine cate 
runde mai trebuie aplicat dot), paralyzed (cate runde nu are voie sa se 
deplaseze). In aceasta clasa se remarca metoda setExperiencePoints, unde pe 
langa modificarile xp-ului si ale nivelului, am grija sa nu redau viata unui 
jucator mort.
	Clasele derivate sunt Knight, Pyromancer, Rogue si Wizard. Celor din urma
le mai apare un camp , nr, in care se verifica daca trebuie aplicat criticalHit.
In fiecare clasa exista o clasa interna Modifiers care retine modificatorii de
rasa, de teren, damage-ul dat de fiecare abilitate si bonusul acesteia,
modificatorii de teren sau alte constante necesare cum ar fi criticalHit, 
numarul  de runde pentru aplicarea dot-ului, etc. Toate clasele au o metoda 
newScore in care se calculeaza damage-ul dat de fiecare abilitate in functie de
modificatorii de rasa si de teren, dupa caz, si de conditiile initiale, dupa 
formulele din enunt. Tot aici se aplica dot, daca este cazul.
	De mentionat este ca pentru a putea calcula damage-ul dat de deflect, 
subclasele Knight, Pryromancer si Rogue suprascriu metoda totalDamage in care
se calculeaza damage-ul dat de acea instanta fara a tine cont de modificatorii
de rasa. In cazul Rogue mai apare o metoda(totalBackstab) care aplica 
criticalHit cand este cazul, prima calculand doar damage-ul dat de Paralysis.
	Interactiunea dintre eroi se realizeaza prin conceptul de DOUBLE DISPATCH.
Toate clasele ce extind Heroes, inclusiv, au o metoda accept care permite 
interactiunea celorlalte obiecte cu ea. De asemenea, au cate o metoda fight ce 
primeste ca parametru pe rand un obiect din toate clasele enumerate anterior, 
si un string s ce reprezinta suprafata de joc pe care va avea loc bataia. 
Toate aceste metode apeleaza metoda newScore din clasa lor, dandu-i ca 
parametrii eroul cu care se va duela, modificatorii de rasa in functie de 
instanta acestuia si suprafata de joc.
	In main, dupa ce citesc datele creez un ArrayList de Map. Pentru fiecare
runda realizez intai mutarile, apoi scad damage-ul dat de dot(daca nu exista 
este 0). Pentru fiecare jucator viu verific daca un altul cu id-ul mai mare 
decat el(ca sa nu aiba loc o batalie de doua ori) se afla pe aceeasi pozitie.
Daca se intampla asta se bat, iar daca vreunul sau amandoi mor, se dau punctele
de experienta, se creste nivelul, respectiv se dau punctele de viata, dupa caz.
	La sfarsit se parcurge din nou ArrayList de Map pentru a se crea un 
string cu datele necesare pentru fiecare erou. Stringul este afisat in fisiere 
prin metoda write din clasa Input.

	II. Readme explicatii etapa 2

	In pachetul angels sunt implementati ingerii cu ajutorul unui factory 
pattern AngelsFactory. Aproape toti ingerii modifica hp-ul si modificatorii de
rasa ai unui jucator. Ei sun implementati cu ajutorul clase Angels. Din acest 
motiv au fost tratati separati doar cei care fac ceva diferit in clasele 
derivate LevelUpAngel, Spawner, TheDoomer si XPAngel. Interactiunea ingerilor 
cu jucatorii se realizeaza folosind conceptul de double dispatch. Fiecare clasa
ce implementeaza eroii contine o metoda "acceptAngels", prin care permite 
ingerilor sa interactioneze cu ei. In clasa de baza a ingerilor si derivatele 
ei exista 4 functii "angelPlay", una pentru fiecare tip de jucator. Acestea 
apeleaza la randul lor functia "angelPower" cu parametrii corespunzatori 
eroului in cauza. In clasa Angels, functie adauga sau scade un hp din hp-ul
jucatorului si seteaza valoarea campului angelsModifyer (introdus pentru 
aceasta etapa) cu numarul ce trebuie adaugat la modificatorii de rasa. El poate
fi pozitiv sau negativ, dupa caz. In LevelUpAngel in loc sa fie modificat hp-ul
este schimabt xp-ul pana la nivelul superior. Modificatorii se schimba pe 
acelasi principiu. In TheDoomer jucatorilor li se seteaza hp-ul pe 0, in timp 
ce in Spawner, daca sunt morti, li se seteaza hp-ul in functie de tipul lor. 
XPAngel modifica doar XP-ul jucatorilor.

	In pachetul strategy sunt implementate strategia defensiva si cea ofensiva
ce vor fi abordate de jucatori dupa caz. Ele cresc, respectiv diminueaza viata 
unui jucator in functie de tipul lui si seteaza campul strategy din Heroes (nou
pentru aceasta etapa) cu valoarea ce trebuie scazuta, respectiv diminuata din 
modificatorii de rasa. Fiecarui tip de erou i se introduce o noua functie 
"chooseStrategy" in care va crea in functie de nivelul curent de hp si de 
datele specifice lui din enunt, o instanta a clasei DeffenciveStrategy sau 
OffenciveStrategy si va aplica strategia necesara cu ajutorul functiei 
executeStrategy din clasa AplyStrategy.

	Pentru implementarea Marelui Magician folosesc conceptul de Observer. 
Clasa GreatWizard extinde clasa Observable si contine un camp cu mesajul 
pastrat. Cand acesta se schimba, vor fi notificate instantele clasei 
ObserverGreatWizard, si astfel se va schimba mesajul pastrat de ele, prin 
functia "update".

	Modularizarea codului este asigurata de clasa Play unde se desfasoara
jocul, in mare parte. Aceasta contine urmatoarele metode:
	- beforeTheRound: fiecare erou isi face mutarea din acea runda, primeste 
Dot daca are si isi alege strategia daca este cazul
	- nextLevel: daca unui jucator i s-a modificat nivelul atunci se 
actualizeaza mesajul retinut de gratWizard cu cel corespunzator
	- message: afiseaza unul din mesajele standard pentru situatiile in care 
un jucator este omorat sau inviat de un inger
	- deadPlayer: daca un jucator a fost omorat de altul ii este setat hp-ul
pe 0, se dau punctele de experienta si cresterea in nivel, dupa caz, 
castigatorului. Se actualizeaza mesajul retinut de instanta clasei GreatWizard 
cu cele necesare, apelandu-se inclusiv metoda anterioara. 
	- fight: pentru fiecare jucator viu care se afla pe harta, se verifica 
daca se afla pe aceesi pozitie cu un alt jucator cu aceleasi proprietati. 
Acestia se bat tinand cont de tipul lor, terenul pe care se afla, strategie si
efectele ingerilor. Pentru ca lupta se realizeaza simultan am grija, facand 
niste copii sa nu modific hp-ul jucatorilor decat dupa ce se incheie batalia.
Daca un jucator moare apelez functia deadplayer.
	- angelsActions: pentru fiecare inger care actioneaza in runda curenta 
actualizez mesajul din greatWizard cu cel standard. Verific daca se afla pe 
aceeasi pozitie cu vreun erou. Daca da, atunci se realizeaza interactiunea 
dintre inger si jucator in functie de tipul fiecaruia. Rezultatele acestei 
interactiuni sunt constatate prin actualizarea mesajului din greatWizard cu cel
corespunzator.
	- finalResult: se creaza un string cu datele despre fiecare erou la 
sfarsitul jocului. Acesta devine noul continut al mesajului pastrat de 
greatWizard, deci este afisat in fisierul de iesire.

	In main se va parcurge o singura data fiecare runda a jocului, si se vor
apela metodele clasei Play: beforeTheRound, fight si angelsActions. La sfarsit 
se apeleaza finalResult.

	Observatie: Mi s-a parut ca pastrez codul mai usor de inteles si de
implementat daca pentru constructorul ingerilor de baza las ca parametrii cei
4 modificatori ai rasei si cei 4  modificatori ai hp-ului, desi depasesc astfel
numarul maxim recomandat de parametrii (nu era trecuta la depunctari aceasta 
greseala).

	