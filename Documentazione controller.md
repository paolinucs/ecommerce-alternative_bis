# Paolone E-commerce Backend Docs


## Utenti
Gli utenti del software sono due, differenziati da privilegi di livello diverso:
- Amministratore (´ROLE_ADMIN´)
- Utente standard (´ROLE_USER´)
- Utente God (´ROLE_GOD´)

Le chiamate API che gli utenti saranno in grado di effettuare (tramite frontend) dovranno essere autenticate. Gli endpoint che richiedono l'autenticazione da parte dell'utente, infatti, sono ´/auth/user/**´ e ´/auth/admin/**´.
Sono presenti anche API pubblici.

L'utente ´God´ sarà in grado di:
- Aggiungere, rimuovere e modificare i dettagli relativi agli utenti di tipo Amministratore fatto

L'utente ´Amministratore´ sarà in grado di:
- Aggiungere, rimuovere e modificare i Prodotti ok
- Consultare la lista di prodotti venduti ok
- Consultare la lista di prodotti venduti per data ok
- Consultare la lista di prodotti venduti per id prodotto ok
- Consultare la lista di prodotti venduti per id ordine ok
- Visualizzare i dettagli degli ordini di tutti gli utenti
- Cercare un ordine per id ordine
- Cercare un ordine per data ordine

L'utente ´Standard´ sarà in grado di:
- Effettuare nuovi ordini
- Accedere ai dati relativi ai propri metodi di pagamento
- Visualizzare i dettagli dei propri ordini

## API pubblici
Nella configurazione di sicurezza del software sono presenti anche endpoints che possono essere contattati senza autenticazione.

Senza autenticazione l'utente sarà in grado di:
- Visualizzare tutti i prodotti
- Registrarsi
- Autenticarsi

