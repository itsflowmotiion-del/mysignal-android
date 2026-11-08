# MySignal – productspecificatie

## Doelgroep en ontwerp
Nederlandstalige Android-app voor mensen met LVB en/of autisme. De interface moet rustig, voorspelbaar, volwassen en begrijpelijk zijn. Gebruik korte zinnen, duidelijke pictogrammen, voldoende contrast en weinig visuele drukte.

## Functionele onderdelen
1. Startdashboard.
2. Agenda met pictogrammen, toevoegen/bewerken/verwijderen en blokkeren van overlappende afspraken. Controle moet zowel in de app als op de server gebeuren; ook gelijktijdige verzoeken en herhalingen moeten worden afgevangen.
3. Gevoelsmeter met vijf niveaus, emotie-pictogrammen en persoonlijke tijdlijn.
4. Prikkelmeter met vijf zelfgekozen niveaus en praktische suggesties.
5. Ademhalingsoefeningen met Nederlandse gesproken instructies, animatie, timer, pauze en stop. Geen geforceerde ademstops.
6. Tandenpoetscoach met twee minuten timer, vier blokken van 30 seconden en gesproken aanwijzingen voor buitenkant, binnenkant en kauwvlakken.
7. Dagelijkse routine.
8. Persoonlijke notities en verhaal.
9. Persoonlijke hulpkaart.
10. Profiel, beveiliging, privacy, herinneringen en accountverwijdering.

## Niet-doelen / grenzen
- Geen zorgverlener-login of dashboard.
- Geen toegang op afstand voor begeleiders.
- Geen automatische gegevensdeling of meldingen aan begeleiders.
- Een hoge prikkel- of gevoelsstatus veroorzaakt nooit automatisch een interventie.
- Toon duidelijk wanneer hulp niet automatisch wordt ingeschakeld.

## Agenda-regel
Afspraken mogen niet overlappen. Gebruik intervallen [start, einde); een nieuwe afspraak is ongeldig wanneer start < bestaand einde en einde > bestaand start. Pas dezelfde regel toe op de server en bescherm tegen gelijktijdige boekingen.

## Status
Deze specificatie beschrijft geplande functionaliteit. Alleen functies die aantoonbaar zijn gebouwd en getest mogen als beschikbaar worden aangeduid.
