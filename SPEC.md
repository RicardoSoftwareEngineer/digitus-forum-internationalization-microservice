<!-- para IA. não é README de humano. -->
# SPEC — internationalization

status: v0.4
sha: `ce4f68e`
data: 2026-08-28

## Como usar
- Este arquivo é a fonte. Código ≠ spec → **bug de código**. Spec errada → Ricardo muda **este** arquivo, depois o código.
- IDs estáveis (`REGRA-` `DADOS-` `CONTRATO-` `NÃO-` `GAP-`). Não apague ID; marque `revogado`.
- "achei bug" → cita REGRA/CONTRATO. Se não existir, é GAP, não patch.
- "não estamos salvando X" → olha DADOS. Campo ausente = não é bug.
- "cadastrar campo X" → conflita se quebra REGRA/NÃO; senão vira GAP e só então código.
- GAP = pergunta aberta. Não trate GAP como regra.

## Papel
MS **interno** (porta `8081`). Mensagens de UI por `locale` + chave. Sem auth HTTP.

## REGRA
- REGRA-I18-1: uma linha = `locale` + `keyy` + `message`. A coluna da chave **se chama `keyy`** (não `key`).
- REGRA-I18-2: leitura na borda é pública (`/firewall/internationalization/v1/i18` e CONTRATO-FRONT-BUNDLE). Escrita (`createUpdate`) e `missing` são internas.
- REGRA-I18-3: chave ausente pode ser registrada em `InternacionalizationMissing` (`locale`,`keyy`) para o autor completar depois.
- REGRA-I18-4: firewall pode cachear; `deleteCache` na borda exige token.
- REGRA-I18-5: **revogado** (2026-08-28). Course vira Training. Ver REGRA-TRAINING-I18-5.
- REGRA-TRAINING-I18-5: idioma da vitrine (`pt_BR` / `en_US`) e textos que mudam com locale passam **por este MS**. Não duplicar Training por idioma (SOLID).

## NÃO
- NÃO-EXPOSE da escrita
- NÃO-SHUTDOWN
- NÃO-CURSO-ROW: **revogado** (2026-08-28). Course vira Training. Ver NÃO-TRAINING-ROW.
- NÃO-TRAINING-ROW: não duplica Training por locale. name/sinopse “de produto” no Training são identidade; variação de idioma = `keyy` aqui.

## DADOS
| id | tabela | campos |
|---|---|---|
| DADOS-I18 | Internacionalization | id, locale, keyy, message |
| DADOS-MISS | InternacionalizationMissing | id, locale, keyy |

## CONTRATO
- `/i18/v1` — get por locale+keyy
- `/i18/v1/createUpdate`
- `/i18/v1/missing`
- CONTRATO-FRONT-BUNDLE `POST /i18/v1/frontend` — lista i18 do locale (body/header). JSON array com `keyy`+`message` (I18Entity). Público no MS (sem auth).
- health `/i18/v1/healthCheck`

## GAP
- GAP-FRONT-BUNDLE: **revogado** (2026-08-28). CONTRATO-FRONT-BUNDLE.
- GAP-WRITE-AUTH: createUpdate interno sem token. Ok enquanto REGRA-EDGE-1 vale. Se o MS vazar, vira P0.
- GAP-KEYY: VO usa `key`, entidade usa `keyy`. Leitura passa `vo.key` na query (ok). INSERT via ModelMapper **não** copia `key`→`keyy` (fica null). Isso é **bug de código** contra REGRA-I18-1, não GAP de produto.
