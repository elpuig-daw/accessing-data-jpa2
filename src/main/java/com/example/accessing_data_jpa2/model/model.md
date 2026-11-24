
# Relacions entre les entitats

En aquest package hi ha dues entitats: `Customer` (client) i `Incident` (incidència). La relació entre elles és d'un a molts:

- Un `Customer` pot tenir zero o més `Incident`.
- Cada `Incident` pot estar associada com a màxim a un `Customer`.

```text
Customer 1 ─────── 0..* Incident
```

## Com està definida

La relació es declara als dos costats:

- A `Customer`, `incidencies` està anotada amb `@OneToMany(mappedBy = "customer")`. És el costat invers; `mappedBy` fa referència al camp `customer` de `Incident`.
- A `Incident`, `customer` està anotat amb `@ManyToOne` i `@JoinColumn(name = "customer_id")`. Aquest és el costat propietari de la relació i desa la clau forana `customer_id` a la taula `incidencies`.

Per tant, la relació és bidireccional: des d'un client es poden consultar les incidències i, des d'una incidència, es pot consultar el client. La columna `customer_id` no està marcada com a `nullable = false`, així que l'esquema actual no obliga que tota incidència tingui un client.

## Càrrega i cicle de vida

- Tots dos costats indiquen `fetch = FetchType.LAZY`: l'entitat relacionada es carrega quan es necessita.
- La col·lecció de `Customer` té `cascade = CascadeType.ALL`: les operacions en cascada sobre el client s'apliquen a les seves incidències.
- També té `orphanRemoval = true`: si es treu una incidència de la col·lecció, aquesta incidència s'elimina.

Feu servir `addIncidencia` i `removeIncidencia` de `Customer` per mantenir sincronitzats els dos costats de la relació. Aquests mètodes actualitzen la col·lecció i el camp `customer` de la incidència.