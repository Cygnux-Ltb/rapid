package io.cygnux.rapid.infra.persistence.dao;

import io.cygnux.rapid.infra.persistence.entity.TradingEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradingDao extends JpaRepository<TradingEventEntity, Integer> {
}
