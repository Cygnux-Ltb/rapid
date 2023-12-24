package io.cygnuxltb.console.persistence;

import io.mercury.common.lang.Throws;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.serialization.json.JsonWrapper;
import org.slf4j.Logger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.function.Supplier;

import static io.mercury.common.functional.Functions.exec;
import static io.mercury.common.functional.Functions.execBool;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;

public final class JpaExecutor {

    private static final Logger log = Log4j2LoggerFactory.getLogger(JpaExecutor.class);

    /**
     * @param func Supplier<List<T>>
     * @param type Class<T>
     * @return List<T>
     */
    public static <T> List<T> select(Class<T> type, Supplier<List<T>> func) {
        return exec(
                // SQL execution process
                func,
                // Query result processing
                result -> {
                    if (isEmpty(result))
                        log.warn("query [{}] return 0 row", type.getSimpleName());
                    else if (result.size() > 2)
                        log.info("query [{}] return {} row", type.getSimpleName(), result.size());
                    else
                        log.info("query [{}] return {} row, result -> {}", type.getSimpleName(),
                                result.size(), JsonWrapper.toJson(result));
                    return result;
                },
                // Log for case of failure
                e -> log.error("query [{}], an exception occurred -> {}", type.getSimpleName(),
                        e.getMessage(), e));
    }

    /**
     * @param repository JpaRepository<T, Long>
     * @param entity     T
     * @return boolean
     */
    public static <T> boolean insertOrUpdate(JpaRepository<T, Long> repository, T entity) {
        return execBool(
                // SQL execution process
                () -> {
                    if (entity == null)
                        Throws.illegalArgument("entity");
                    return repository.saveAndFlush(entity);
                },
                // Log for case of success
                o -> {
                    log.info("insert or update [{}] success, entity -> {}",
                            entity.getClass().getSimpleName(), entity);
                    return true;
                },
                // Log for case of failure
                e -> {
                    log.error("insert or update [{}] failure, entity -> {}, exception message -> {}",
                            entity.getClass().getSimpleName(), entity, e.getMessage(), e);
                    return false;
                });
    }


    /**
     * @param repository JpaRepository<T, Long>
     * @param entities   List<T>
     * @param <T>        T
     * @return boolean
     */
    public static <T> boolean insertOrUpdate(JpaRepository<T, Long> repository, List<T> entities) {
        return execBool(
                // SQL execution process
                () -> {
                    if (entities == null)
                        Throws.illegalArgument("entity");
                    return repository.saveAllAndFlush(entities);
                },
                // Log for case of success
                o -> {
                    log.info("insert or update [{}] success, count -> {}",
                            entities.getFirst().getClass().getSimpleName(), entities.size());
                    return true;
                },
                // Log for case of failure
                e -> {
                    log.error("insert or update [{}] failure, entity -> {}, exception message -> {}",
                            entities.getFirst().getClass().getSimpleName(), e.getMessage(), e);
                    return false;
                });
    }

}
