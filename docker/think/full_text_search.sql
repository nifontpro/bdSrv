select *
from poems
where to_tsvector('russian', content) @@
(phraseto_tsquery('russian', 'были люди в наше время'));

create index content_gin_idx ON poems
using gin(to_tsvector('russian', content));

insert into poems(author, content)
select md5(random()::text), md5(random()::text)
from generate_series(1, 1000000);
-- Быстрее сначала создать большую таблицу, а потом индексировать ее
