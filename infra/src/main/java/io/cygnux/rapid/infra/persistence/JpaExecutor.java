package io.cygnux.rapid.infra.persistence;

import io.mercury.common.lang.Validator;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import org.slf4j.Logger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.function.Function;
import java.util.function.Supplier;

import static io.mercury.common.functional.Functions.exec;
import static io.mercury.common.functional.Functions.execBool;
import static io.mercury.common.lang.Throws.nullPointer;
import static io.mercury.serialization.json.JsonWriter.toJson;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;

public final class JpaExecutor {

    private static final Logger log = Log4j2LoggerFactory.getLogger(JpaExecutor.class);

    private JpaExecutor() {
    }

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
                                result.size(), toJson(result));
                    return result;
                },
                // Log for a case of failure
                e -> log.error("query [{}], an exception occurred -> {}", type.getSimpleName(),
                        e.getMessage(), e)
        );
    }


    /**
     * @param repository JR
     * @param queryFunc  Function<J, T>
     * @param <R>        extends JpaRepository<T, Long>
     * @param <T>        T
     * @return boolean
     */
    public static <R extends JpaRepository<T, ?>, T> boolean insertOrUpdate(R repository, Function<R, T> queryFunc)
            throws NullPointerException {
        return execBool(
                // SQL execution process
                () -> {
                    T entity = queryFunc.apply(repository);
                    if (entity == null)
                        nullPointer("function return entity");
                    return repository.saveAndFlush(entity);
                },
                // Log for a case of success
                obj -> {
                    insertSucceedLog(obj);
                    return true;
                },
                // Log for a case of failure
                e -> {
                    log.error("exception message -> {}", e.getMessage(), e);
                    return false;
                }
        );
    }


    /**
     * @param repository JpaRepository<T, ?>
     * @param entity     T
     * @return boolean
     */
    public static <T> boolean insert(JpaRepository<T, ?> repository, T entity)
            throws IllegalArgumentException {
        return execBool(
                // SQL execution process
                () -> {
                    checkEntity(entity);
                    return repository.saveAndFlush(entity);
                },
                // Log for a case of success
                obj -> {
                    insertSucceedLog(obj);
                    return true;
                },
                // Log for a case of failure
                e -> {
                    log.error("insert {} failure, entity -> {}, exception message -> {}",
                            entity.getClass().getSimpleName(), entity, e.getMessage(), e);
                    return false;
                }
        );
    }


    /**
     * @param jpa      JpaRepository<T, ?>
     * @param entities List<T>
     * @param <T>      T
     * @return boolean
     */
    public static <T> boolean insert(JpaRepository<T, ?> jpa, List<T> entities)
            throws IllegalArgumentException {
        return execBool(
                // SQL execution process
                () -> {
                    checkEntity(entities);
                    return jpa.saveAllAndFlush(entities);
                },
                // Log for a case of success
                obj -> {
                    log.info("insert [{}] success, count -> {}",
                            obj.getFirst().getClass().getSimpleName(), obj.size());
                    return true;
                },
                // Log for a case of failure
                e -> {
                    log.error("insert [{}] failure, entity -> {}, exception message -> {}",
                            entities.getFirst().getClass().getSimpleName(), e.getMessage(), e);
                    return false;
                }
        );
    }


    /**
     * @param repository JpaRepository<T, ?>
     * @param entity     T
     * @return boolean
     */
    public static <T> boolean insertIfNotExist(JpaRepository<T, ?> repository, BooleanSupplier notExist, T entity)
            throws IllegalArgumentException {
        return execBool(
                // SQL execution process
                () -> {
                    if (notExist.getAsBoolean()) {
                        checkEntity(entity);
                        return repository.saveAndFlush(entity);
                    }
                    return null;
                },
                // Log for a case of success
                obj -> {
                    if (obj == null)
                        log.warn("insert [{}] failure, entity -> {}, entity already exists",
                                entity.getClass().getSimpleName(), entity);
                    else
                        insertSucceedLog(obj);
                    return true;
                },
                // Log for a case of failure
                e -> {
                    log.error("insert [{}] failure, entity -> {}, exception message -> {}",
                            entity.getClass().getSimpleName(), entity, e.getMessage(), e);
                    return false;
                }
        );
    }

    private static void insertSucceedLog(Object obj) {
        log.info("insert [{}] succeed, entity -> {}", obj.getClass().getSimpleName(), obj);
    }

    private static void insertOrUpdateSucceedLog(Object obj) {
        log.info("insert or update [{}] succeed, entity -> {}", obj.getClass().getSimpleName(), obj);
    }

    private static <E> void checkEntity(E entity) {
        Validator.nonNull(entity, "entity");
    }

}
