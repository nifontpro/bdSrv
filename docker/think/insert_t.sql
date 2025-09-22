insert into t(a, b, c)
select s.id,
       chr((32 + random() * 94)::integer),
       random() < 0.01
from generate_series(1, 100000) as s(id)
order by random();