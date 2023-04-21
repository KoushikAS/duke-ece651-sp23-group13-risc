package edu.duke.ece651.team13.server.service;

import edu.duke.ece651.team13.server.entity.TerritoryViewEntity;
import edu.duke.ece651.team13.server.entity.UnitEntity;
import edu.duke.ece651.team13.server.entity.UnitViewEntity;
import edu.duke.ece651.team13.server.repository.UnitViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UnitViewServiceImpl implements UnitViewService{
    @Autowired
    private final UnitViewRepository repository;

    @Override
    @Transactional
    public UnitViewEntity initUnitView(TerritoryViewEntity territoryView, UnitEntity toDisplay) {
        UnitViewEntity unitView = new UnitViewEntity();
        unitView.setTerritoryView(territoryView);
        territoryView.getUnitsDisplay().add(unitView);
        unitView.setToDisplay(toDisplay);
        unitView.setUnitType(toDisplay.getUnitType());
        unitView.setUnitNum(toDisplay.getUnitNum());
        return repository.save(unitView);
    }

    @Override
    @Transactional
    public UnitViewEntity updateUnitView(UnitViewEntity unitView, UnitEntity unitEntity){
        unitView.setUnitNum(unitEntity.getUnitNum());
        return repository.save(unitView);
    }
}
