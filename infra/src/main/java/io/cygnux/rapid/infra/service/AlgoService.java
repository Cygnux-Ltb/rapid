package io.cygnux.rapid.infra.service;

import io.cygnux.rapid.infra.dto.common.GeneralOption;
import io.cygnux.rapid.infra.persistence.dao.AlgoDao;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.eclipse.collections.api.map.ConcurrentMutableMap;
import org.eclipse.collections.impl.map.mutable.ConcurrentHashMap;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class AlgoService {

    @Resource
    private AlgoDao algoDao;

    private final List<GeneralOption> algoOptions = new CopyOnWriteArrayList<>();

    private final ConcurrentMutableMap<Integer, String> algoNameMap = new ConcurrentHashMap<>();

    private final ConcurrentMutableMap<Integer, String> algoDisplayNameMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        algoDao.findAll().forEach(algo -> {
            algoNameMap.put(algo.getAlgoId(), algo.getAlgoName());
            var displayName = algo.getAlgoName()
                              + "(" + algo.getAlgoId() + "/" + algo.getAlgoType() + ")";
            algoDisplayNameMap.put(algo.getAlgoId(), displayName);
            algoOptions.add(new GeneralOption(algo.getAlgoId(), displayName));
        });
    }

    public List<GeneralOption> getAlgoOptions() {
        return algoOptions;
    }

    public String getAlgoName(int algoId) {
        return algoNameMap.get(algoId);
    }

    public String getAlgoDisplayName(int algoId) {
        return algoDisplayNameMap.get(algoId);
    }

}
