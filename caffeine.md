# Getting Started

Как включить Caffeine как L2-кэш Hibernate (Spring Boot 3 / Hibernate 6)
1) Зависимости
   // build.gradle.kts
   dependencies {
   implementation("org.springframework.boot:spring-boot-starter-data-jpa")
   implementation("org.hibernate.orm:hibernate-jcache")        // интеграция Hibernate↔JCache
   implementation("com.github.ben-manes.caffeine:caffeine:3.1.8")
   implementation("com.github.ben-manes.caffeine:jcache:3.1.8") // JCache-провайдер Caffeine
   implementation("javax.cache:cache-api:1.1.1")                // API JCache (стандарт JSR-107)
   }


Модуль hibernate-jcache включает JCacheRegionFactory.

Модуль caffeine-jcache даёт реализацию JCache поверх Caffeine.
docs.jboss.org
+1

2) Настройка Hibernate на JCache + Caffeine

application.yml

spring:
  jpa:
    properties:
      hibernate:
        cache:
          use_second_level_cache: true
          use_query_cache: true         # опционально: кэш результатов запросов
          region:
            factory_class: jcache       # = org.hibernate.cache.jcache.JCacheRegionFactory
        javax:
          cache:
            provider: com.github.benmanes.caffeine.jcache.spi.CaffeineCachingProvider
            uri: classpath:/caffeine-jcache.conf


factory_class: jcache — короткое имя для JCache RegionFactory в Hibernate 5.3+/6.
docs.hazelcast.com

provider — Caffeine JCache провайдер.
Javadoc

3) Правила истечения/размер — через HOCON-конфиг Caffeine JCache

src/main/resources/caffeine-jcache.conf

caffeine.jcache {
  default {
    expiry = "ETERNAL"              # или "AFTER_WRITE"
    maximumSize = 10000
    # Для AFTER_WRITE: expireAfterWrite = 5m
  }

  caches {
    "com.example.model.User" {      # region по умолчанию = FQN класса сущности
      maximumSize = 5000
      expireAfterWrite = 10m
    }
    "hibernate.common.internal.QueryResultsRegion" {
      expireAfterWrite = 2m         # если включили query cache
      maximumSize = 1000
    }
  }
}

Caffeine JCache читает настройки из reference.conf/*.conf. Не создавайте «анонимные» кэши без настроек — по стандарту JCache они будут бессрочными и «by value».
GitHub

4) Отметьте сущности/связи, которые кэшируем
   import jakarta.persistence.Cacheable
   import org.hibernate.annotations.Cache
   import org.hibernate.annotations.CacheConcurrencyStrategy

@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
class User( /* ... */ )

// Для коллекций/связей:
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@OneToMany(mappedBy = "user")
val orders: MutableSet<Order> = mutableSetOf()


Hibernate создаёт отдельный region на класс (или тот, что вы укажете явно). READ_WRITE — безопасная стратегия с версионированием.
Stack Overflow
+1

Важно понимать ограничения

Область действия: кэш — локальный в памяти одного инстанса приложения. Для кластера используйте распределённый кэш (Hazelcast/Redis/Infinispan) или 2-уровневую схему (Caffeine как L1 + Redis как L2 для Spring Cache), но Hibernate L2 с Caffeine сам по себе не реплицируется.
jhipster.tech
+1

Жизненный цикл: кэш очищается при рестарте процесса.

Что кэшируется L2: состояния сущностей по ID и опционально коллекции и query cache; это не подмена базы и не кэш произвольных запросов без явного включения.
Hibernate

Мини-проверка

Запустите приложение, включите DEBUG/TRACE для org.hibernate.cache и org.hibernate.SQL.

Первый find()/загрузка — идёт в БД; повторный в пределах процесса — из L2 (в логах увидите «hit» по региону).
Hibernate

Итог

Да, межсессионный (второй уровень) кэш сущностей с Caffeine в Spring Boot возможен и делается через Hibernate + JCache (Caffeine JCache provider). Если у вас кластер/горизонталь — дополнительно обсудим стратегию: Caffeine как быстрый L2 локально + распределённый кэш для согласованности, либо полностью распределённый провайдер.
GitHub
+2
docs.jboss.org
+2