<!-- para IA. não é README de humano. -->
# SPEC — internationalization

status: v0
sha: `3ded229`
data: 2026-08-28

## Como usar
- Este arquivo é a fonte. Código ≠ spec → **bug de código**. Spec errada → Ricardo muda **este** arquivo, depois o código.
- IDs estáveis (`INV-` `DADOS-` `END-` `NÃO-` `GAP-`). Não apague ID; marque `revogado`.
- "achei bug" → cita INV/END. Se não existir, é GAP, não patch.
- "não estamos salvando X" → olha DADOS. Campo ausente = não é bug.
- "cadastrar campo X" → conflita se quebra INV/NÃO; senão vira GAP e só então código.
- GAP = pergunta aberta. Não trate GAP como regra.

## Papel
MS **interno** (porta `8081`). Mensagens de UI por `locale` + chave. Sem auth HTTP.

## INV
- INV-I18-1: uma linha = `locale` + `keyy` + `message`. A coluna da chave **se chama `keyy`** (não `key`).
- INV-I18-2: leitura na borda é pública (`/firewall/internationalization/v1/i18`). Escrita (`createUpdate`) e `missing` são internas.
- INV-I18-3: chave ausente pode ser registrada em `InternacionalizationMissing` (`locale`,`keyy`) para o autor completar depois.
- INV-I18-4: firewall pode cachear; `deleteCache` na borda exige token.

## NÃO
- NÃO-EXPOSE da escrita
- NÃO-SHUTDOWN
- NÃO-CURSO: não guarda name/sinopse de Course (isso é course MS)

## DADOS
| id | tabela | campos |
|---|---|---|
| DADOS-I18 | Internacionalization | id, locale, keyy, message |
| DADOS-MISS | InternacionalizationMissing | id, locale, keyy |

## END
- `/i18/v1` — get por locale+keyy
- `/i18/v1/createUpdate`
- `/i18/v1/missing`
- health `/i18/v1/healthCheck`

Não existe no MS (só o front chama): `/i18/v1/frontend` e `/internationalization/v1/frontend`. GAP-FRONT-BUNDLE.

## GAP
- GAP-FRONT-BUNDLE: vitrine quer um dump de todas as chaves do locale. Spec: endpoint de bundle na borda (público?) ou o front chama `/i18` chave a chave?
- GAP-WRITE-AUTH: createUpdate interno sem token. Ok enquanto INV-EDGE-1 vale. Se o MS vazar, vira P0.
- GAP-KEYY: VO usa `key`, entidade usa `keyy`. Leitura passa `vo.key` na query (ok). INSERT via ModelMapper **não** copia `key`→`keyy` (fica null). Isso é **bug de código** contra INV-I18-1, não GAP de produto.
