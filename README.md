# spendor - költség és bevételkövető alkalmazás
A pénzügyek, ebben nagyon sok pénz van. Mindenki szeretné tudni, hogy a nehezen megkeresett pénze, hová folyik el, pontosan hol, mire mennyit költ egy adott időszakban. Erre már számos applikáció létezik, azonban ezen webes alkalmazás továbbfejleszthető abba az irányba, hogy a költségeket elemezve tippeket ad a spórolásra, átcsoportosításra, racionalizálásra. A felhasználó tud szűrni a költés összege, helye, időszak és vásárolt termék ill. szolgáltatás alapján, majd később ezekből lehet statisztikákat készíteni. 

Az alkalmazásba az költéseket lehet felvinni hely, idő, összeg és termékek/szolgáltatások listájának megadásával, melyeket utólagosan lehetséges szerkeszteni, kiegészíteni, bővíteni és törölni.
Mindemellett az érkező bevételeket/jóváírásokat is fel lehet vinni az összeg, a küldő és a tárgy megjelölésével.
Az alkalmazás háromrétegű, repository, service és controller réteggel, SPRING BOOT keretrendszerrel, MariaDB adatbázis alkalmazásával. 2 db controllerem van, melyekben 5 db végpont van.
3 db Entitást tartalmaz, mely mögött 3 db tábla van. 
