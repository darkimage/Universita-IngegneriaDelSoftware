## Progetto Ingegneria del Software [![relazione](https://img.shields.io/badge/relazione-disponibile%20in%20pdf-brightgreen)](https://github.com/darkimage/Universita-IngegneriaDelSoftware/raw/master/documents/SistemadiVenditaOnline_LucaFaggion_274857.pdf)[ ![installazione](https://img.shields.io/badge/guida_uso_ed_installazione-disponibile%20in%20pdf-brightgreen)](https://github.com/darkimage/Universita-IngegneriaDelSoftware/raw/master/documents/Istruzioni-Uso-e-Installazione-274857-Faggion-Luca.pdf)

Progetto realizzato utilizzando il framework Groovy [Grails](https://grails.org/).

Il principale scopo del progetto e' di ottenere una dimestichezza nello sviluppo di progetti utilizzando il metodo di **iterazione continua di sviluppo e test** tramite la metodologia [AGILE]([https://it.wikipedia.org/wiki/Metodologia_agile](https://it.wikipedia.org/wiki/Metodologia_agile)). 

La **relazione completa** e' disponibile [qui](https://github.com/darkimage/Universita-IngegneriaDelSoftware/raw/master/documents/SistemadiVenditaOnline_LucaFaggion_274857.pdf).

Il **documento d'uso** e' disponibile [qui](https://github.com/darkimage/Universita-IngegneriaDelSoftware/raw/master/documents/Istruzioni-Uso-e-Installazione-274857-Faggion-Luca.pdf)

Il **report dei test** e consultabile [qui](https://darkimage.github.io/Universita-IngegneriaDelSoftware/) (101 Unit e Integration tests, il codice dei test e' definito [qui](https://github.com/darkimage/Universita-IngegneriaDelSoftware/tree/master/src/test/groovy/com/lucafaggion)).

Traccia Progetto
---------------------

> **Un Sistema di vendita online**
> 
> Il negozio espone in un catalogo tutti i prodotti in vendita,
> caratterizzati da un nome, un identificativo univoco, un prezzo di
> listino e il numero di unità di prodotto disponibile.
> 
> Ai dipendenti del negozio è affidata la mansione di inserire nuovi
> prodotti in catalogo, di aggiornare la disponibilità dei prodotti e di
> visualizzare gli ordini dei clienti
> 
> I clienti possono visionare il catalogo ed effettuare, previa
> registrazione, ordini di acquisto. Un ordine, del quale occorre sempre
> tracciare la data di effettuazione, è composto da una o più righe,
> ciascuna facente riferimento al prodotto e alla quantità di esso che
> il cliente intende acquistare.
> 
> Per ogni cliente il sistema deve tener traccia dei dati anagrafici e
> fiscali, e di almeno un indirizzo di recapito.
> 
> Il sistema deve offrire un supporto Web per l’esecuzione delle
> operazioni da parte dei dipendenti e dei clienti.

 Analisi dei Requisiti
-------------------------
Il **Documento dei Requisiti** e' diponibile [qui](https://github.com/darkimage/Universita-IngegneriaDelSoftware/raw/master/documents/SistemadiVenditaOnline_LucaFaggion_274857.pdf).
Nell'analisi dei requisiti viene redatto un documento che comprende una esaustiva descrizione dettagliata delle necessita' del sistema comprendente:

 - **Utenti**
	 > Descrizione delle *principali figure* che usufruiranno dell'applicativo (Clienti, Dipendenti, Amministratori)
	 > nel documento si analizza il **Contesto d'uso** e le **Esigenze** di queste figure per delineare l'iniziale struttura del sistema da realizzare
- **Sistemi Esterni**
	> Sezione che analizza eventuali sistemi esterni che dovranno **interagire** con l'applicativo (in questo caso un **sistema di autorizzazione del circuito per i pagamenti** che e' utilizzato per la **tracciabilita'** degli ordini e di verifica dei dati di pagamento) 
- **Sistema**
	> Il sistema sviluppato sarà una applicazione web che nella parte
	> Negozio esporrà  	tutti i prodotti in vendita e i loro dettagli (nome,
	> identificativo univoco, prezzo e  	unità disponibili) mentre nella
	> parte disponibile solo ai Dipendenti consentirà di  	attuare
	> operazioni di manutenzione. Sarà composto da componenti software 
	> 
	> 
	> 	quali: web server (core del sistema), interfaccia web (pagine del
	> sito), DBMS  	(per storage dei dati);  	e da componenti hardware per
	> l’esecuzione dei servizi software necessari.  	Il sistema dovrà
	> disporre di interfacce web separate per i Clienti (Front End) e i 
	> 	Dipendenti del Negozio (Back End).
- **Riassunto delle funzionalità**
	> Tabella riportante tutte le sigle delle funzionalita' per tipologia di beneficio individuate nel documento.
- **Features del Sistema**
	> In questa sezione vengono descritte una ad una le features individuate.
- **Casi d'uso**
	> In questa sezione vengono descritti i casi d'uso più importanti nell'interazione 
con il sistema e vengono illustrati degli esempi in dettaglio utilizzando diagrammi  [USE CASE UML](https://it.wikipedia.org/wiki/Use_Case_Diagram).
  > 
	>**Esempio:**
  > 
	> ![usecase_diagram](https://github.com/darkimage/Universita-IngegneriaDelSoftware/raw/master/documents/esempio_usecase.png)
- **Dettagli requisiti** 
	> Tabella riassuntiva dei requisiti scoperti per ciascuna feature.
- **Classi di Analisi** 
	> In questa sezione verranno descritte le principali classi
	> dell’applicazione per  semplicità di visualizzazione i vari grafici
	> sono stati divisi in sezioni partendo  dallo strato più basso.
  > 
	> **Esempio:**
  > 
	> ![class_diagram](https://github.com/darkimage/Universita-IngegneriaDelSoftware/raw/master/documents/esempio_class.png)
- **Diagrammi di Sequenza**
	> In questa sezione vengono descritti i principali diagrammi di sequenza
	> relativi  alle features del sistema.
  > 
	> **Esempio:**
  > 
	>	![sequence_diagram](https://github.com/darkimage/Universita-IngegneriaDelSoftware/raw/master/documents/esempio_sequenza.png)
- **Mockups**
	> In Questa sezione verranno mostrati alcuni mockups della progettazione
	> dell’interfaccia grafica dell’applicazione che saranno modellate
	> seguendo le  features FF13 e FF18 (comprende il fatto che
	> l’interfaccia deve essere anche mobile friendly ovvero accessibile da
	> dispositivi mobili) individuate durante  l’analisi dei requisiti
  > 
	> **Esempio:**
  > 
	> ![mockup_example](https://github.com/darkimage/Universita-IngegneriaDelSoftware/raw/master/documents/esempio_mock.png)
<!--stackedit_data:
eyJoaXN0b3J5IjpbLTEwMzQ2OTIxMTAsLTMyNDUyOTUwNl19
-->