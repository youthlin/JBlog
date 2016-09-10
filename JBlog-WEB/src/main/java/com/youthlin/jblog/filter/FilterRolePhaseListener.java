package com.youthlin.jblog.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 * Created by lin on 2016-09-04-004.
 * 阶段事件监听
 */
public class FilterRolePhaseListener implements PhaseListener {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void beforePhase(PhaseEvent event) {
        log.trace("Before {}", event.getPhaseId());
    }

    @Override
    public void afterPhase(PhaseEvent event) {
        log.trace("After {}", event.getPhaseId());
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }
}
