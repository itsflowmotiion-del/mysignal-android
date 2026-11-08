# Privacy- en beveiligingsuitgangspunten

Dit document is een ontwikkelchecklist en geen juridische verklaring van AVG/GDPR-compliance.

- Verzamel alleen gegevens die noodzakelijk zijn voor een duidelijk omschreven doel.
- Gevoels-, prikkel- en welzijnsgegevens kunnen bijzondere persoonsgegevens zijn.
- Bepaal vóór verwerking een geldige rechtsgrond en, waar nodig, een uitzondering voor bijzondere persoonsgegevens.
- Beoordeel of een DPIA verplicht of verstandig is vóór ingebruikname.
- Leg bewaartermijnen, verwijdering, inzage, correctie en dataportabiliteit vast.
- Sluit passende verwerkersovereenkomsten en beoordeel hostinglocatie en doorgiften.
- Gebruik TLS voor alle netwerkverbindingen.
- Hash wachtwoorden met een geschikte, moderne password-hashingfunctie; sla nooit platte wachtwoorden op.
- Gebruik autorisatiecontroles op de server, rate limiting en veilige sessies.
- Bied optionele TOTP-tweestapsverificatie en herstelcodes.
- Bewaar geen geheimen, API-sleutels of persoonsgegevens in Git.
- Maak netwerkfouten zichtbaar; toon nooit een mislukte opslag als succesvol.
- Test beveiliging, toegangscontrole, back-ups, herstel en accountverwijdering vóór release.
- Geen automatische delen met begeleiders of zorgverleners.
